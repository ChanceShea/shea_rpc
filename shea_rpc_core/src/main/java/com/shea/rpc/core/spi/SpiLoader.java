package com.shea.rpc.core.spi;

import cn.hutool.core.io.resource.ResourceUtil;
import com.shea.rpc.core.serializer.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SPI加载器
 * @author : Shea.
 * @since : 2026/5/1 19:11
 */
@Slf4j
public class SpiLoader {

    private static Map<String,Map<String,Class<?>>> loaderMap = new ConcurrentHashMap<>();

    private static Map<String,Object> instanceCache = new ConcurrentHashMap<>();

    private static final String RPC_SYSTEM_SPI_DIR = "META-INF/rpc/system/";

    private static final String RPC_CUSTOM_SPI_DIR = "META-INF/rpc/custom/";

    private static final String[] SCAN_DIRS = new String[]{RPC_SYSTEM_SPI_DIR, RPC_CUSTOM_SPI_DIR};

    private static final List<Class<?>> LOAD_CLASS_LIST = Arrays.asList(Serializer.class);

    /**
     * 加载所有SPI类
     */
    public static void loadAll() {
        log.info("加载所有SPI");
        for (Class<?> clazz : LOAD_CLASS_LIST) {
            load(clazz);
        }
    }

    public static <T> T getInstance(Class<T> clazz,String key) {
        String className = clazz.getName();
        Map<String,Class<?>> keyClassMap = loaderMap.get(className);
        if (keyClassMap == null) {
            throw new RuntimeException(String.format("SpiLoader 未加载 %s 类型",className));
        }
        if (!keyClassMap.containsKey(key)) {
            throw new RuntimeException(String.format("SpiLoader 的 %s 不存在 key = %s 的类型",className,key));
        }
        Class<?> implClass = keyClassMap.get(key);
        String implClassName = implClass.getName();
        if (!instanceCache.containsKey(implClassName)) {
            try {
                instanceCache.put(implClassName,implClass.newInstance());
            }catch (Exception e) {
                throw new RuntimeException(String.format("SpiLoader 创建 %s 类型失败",implClassName),e);
            }
        }
        return (T) instanceCache.get(implClassName);
    }

    public static Map<String,Class<?>> load(Class<?> loadClass) {
        log.info("加载类型为{}的SPI",loadClass.getName());
        Map<String,Class<?>> keyClassMap = new HashMap<>();
        for (String scanDir : SCAN_DIRS) {
            List<URL> resources = ResourceUtil.getResources(scanDir + loadClass.getName());
            for (URL resource : resources) {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(resource.openStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        String[] strArray = line.split("=");
                        if (strArray.length > 1) {
                            String key = strArray[0];
                            String className = strArray[1];
                            keyClassMap.put(key, Class.forName(className));
                        }
                    }
                }catch (Exception e) {
                    log.error("加载SPI类失败",e);
                }
            }
        }
        loaderMap.put(loadClass.getName(),keyClassMap);
        return keyClassMap;
    }
}
