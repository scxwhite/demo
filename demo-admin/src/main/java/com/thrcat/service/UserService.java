package com.thrcat.service;

import com.thrcat.User;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/05
 */
public interface UserService {

    /**
     * 用户登陆校验
     *
     * @param user 用户
     * @return true:成功，false失败
     */
    boolean login(User user);

    boolean login(String username,String password);

    User findUser(String username,String password);

}
