package com.shea.rpc.core.serializer;

import com.shea.rpc.core.spi.SpiLoader;

/**
 * @author : Shea.
 * @since : 2026/5/1 19:05
 */
public class SerializerFactory {


    static {
        SpiLoader.load(Serializer.class);
    }

    private static final Serializer defaultSerializer = new JdkSerializer();

    /**
     * 根据key获取对应的Serializer
     * @param key 序列化器的key
     * @return 对应的Serializer
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
