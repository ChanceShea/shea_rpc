package com.shea.rpc.core;

import com.shea.rpc.core.config.RpcConfig;
import com.shea.rpc.core.constant.RpcConstant;
import com.shea.rpc.core.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC应用类
 * @author : Shea.
 * @since : 2026/4/29 22:29
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig rpcConfig) {
        RpcApplication.rpcConfig = rpcConfig;
        log.info("rpc init,config={}", rpcConfig.toString());
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            log.error("rpc init error", e);
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
