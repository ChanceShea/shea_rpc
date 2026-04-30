package com.shea.rpc.provider;

import com.shea.rpc.common.model.User;
import com.shea.rpc.common.service.UserService;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:40
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
