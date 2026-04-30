package com.shea.rpc.consumer;

import com.shea.rpc.core.config.RpcConfig;
import com.shea.rpc.core.utils.ConfigUtils;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:45
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpcConfig);
    }
}
