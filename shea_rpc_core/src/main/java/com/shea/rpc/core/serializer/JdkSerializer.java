package com.shea.rpc.core.serializer;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;

import java.nio.charset.StandardCharsets;

/**
 * @author : Shea.
 * @since : 2026/4/29 20:58
 */
public class JdkSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            return new byte[0];
        }
        return JSONUtil.toJsonStr(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String json = new String(bytes, StandardCharsets.UTF_8);
        return JSONObject.parseObject(json, clazz, JSONReader.Feature.SupportClassForName);
    }
}
