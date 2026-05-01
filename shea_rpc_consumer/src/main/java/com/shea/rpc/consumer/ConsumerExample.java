package com.shea.rpc.consumer;

import com.shea.rpc.common.model.User;
import com.shea.rpc.common.service.UserService;
import com.shea.rpc.core.config.RpcConfig;
import com.shea.rpc.core.proxy.ServiceProxyFactory;
import com.shea.rpc.core.utils.ConfigUtils;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:45
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
        UserService proxy = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("Shea");
        User newUser = proxy.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        int number = proxy.getNumber();
        System.out.println(number);
    }
}
