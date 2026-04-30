package com.shea.rpc.core.config;

import lombok.Data;

/**
 * RPC配置类
 * @author : Shea.
 * @since : 2026/4/29 22:17
 */
@Data
public class RpcConfig {

    private String name = "shea-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;
}
