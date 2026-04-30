package com.shea.rpc.core.proxy;

import java.lang.reflect.Proxy;

/**
 * @author : Shea.
 * @since : 2026/4/29 21:31
 */
public class ServiceProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[] { clazz },
                new ServiceProxy()
        );
    }
}
