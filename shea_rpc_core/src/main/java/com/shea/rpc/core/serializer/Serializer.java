package com.shea.rpc.core.serializer;

import java.io.IOException;

/**
 * @author : Shea.
 * @since : 2026/4/29 20:57
 */
public interface Serializer {

    <T> byte[] serialize(T obj) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException;
}
