package com.thrcat;

/**
 * @author scx
 */
public interface UserServiceRpc {


    /**
     * 用户登陆
     *
     * @param user
     * @return
     */
    ResultSet<Boolean> login(User user);
}
