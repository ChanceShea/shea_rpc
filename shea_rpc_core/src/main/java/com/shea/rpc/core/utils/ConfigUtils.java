package com.shea.rpc.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 * @author : Shea.
 * @since : 2026/4/29 22:18
 */
public class ConfigUtils {

    public static <X> X loadConfig(Class<X> clazz,String prefix) {
        return loadConfig(clazz, prefix,"");
    }

    public static <X> X loadConfig(Class<X> clazz,String prefix,String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(clazz,prefix);
    }
}
