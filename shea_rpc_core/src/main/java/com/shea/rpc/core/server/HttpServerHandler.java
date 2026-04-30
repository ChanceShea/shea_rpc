package com.shea.rpc.core.server;

import com.shea.rpc.core.model.RpcRequest;
import com.shea.rpc.core.model.RpcResponse;
import com.shea.rpc.core.registry.LocalRegistry;
import com.shea.rpc.core.serializer.JdkSerializer;
import com.shea.rpc.core.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.lang.reflect.Method;

/**
 * @author : Shea.
 * @since : 2026/4/29 21:03
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        final Serializer serializer = new JdkSerializer();

        System.out.println("Received request: " + request.method() + " " + request.uri());
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request,rpcResponse,serializer);
                return;
            }
            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getParameters());
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse response = request.response()
                .putHeader("content-type", "application/json");
        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            response.end(Buffer.buffer(serialized));
        }catch (Exception e) {
            e.printStackTrace();
            response.end(Buffer.buffer());
        }
    }
}
