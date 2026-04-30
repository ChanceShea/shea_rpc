package com.shea.rpc.core.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.shea.rpc.core.model.RpcRequest;
import com.shea.rpc.core.model.RpcResponse;
import com.shea.rpc.core.serializer.JdkSerializer;
import com.shea.rpc.core.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : Shea.
 * @since : 2026/4/29 21:12
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Serializer serializer = new JdkSerializer();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .build();
        try {
            byte[] bytes = serializer.serialize(rpcRequest);

            try (HttpResponse response = HttpRequest.post("http://localhost:8080")
                    .body(bytes)
                    .execute()) {
                byte[] result = response.bodyBytes();
                RpcResponse deserialize = serializer.deserialize(result, RpcResponse.class);
                return deserialize.getData();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
