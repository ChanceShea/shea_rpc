package com.shea.rpc.core.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Shea.
 * @since : 2026/4/29 20:55
 */
public class LocalRegistry {

    private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();

    public static void register(String serviceName,Class<?> implClass) {
        map.put(serviceName,implClass);
    }

    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
