package com.shea.rpc.common.service;

import com.shea.rpc.common.model.User;

/**
 * @author : Shea.
 * @since : 2026/4/29 19:26
 */
public interface UserService {

    User getUser(User user);

    default int getNumber() {
        return 1;
    }
}
