package com.shea.rpc.provider;

import com.shea.rpc.common.service.UserService;
import com.shea.rpc.core.RpcApplication;
import com.shea.rpc.core.registry.LocalRegistry;
import com.shea.rpc.core.server.HttpServer;
import com.shea.rpc.core.server.VertxHttpServer;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:42
 */
public class ProviderExample {
    public static void main(String[] args) {
        RpcApplication.init();

        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
