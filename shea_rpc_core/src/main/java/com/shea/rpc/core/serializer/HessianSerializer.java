package com.shea.rpc.core.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hessian序列化器
 * @author : Shea.
 * @since : 2026/5/1 19:03
 */
public class HessianSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(baos);
        hessianOutput.writeObject(obj);
        hessianOutput.flush();
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        HessianInput hessianInput = new HessianInput(new ByteArrayInputStream(bytes));
        return (T) hessianInput.readObject();
    }
}
