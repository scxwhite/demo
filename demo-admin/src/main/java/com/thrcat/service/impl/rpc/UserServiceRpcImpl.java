package com.thrcat.service.impl.rpc;

import com.thrcat.ResultSet;
import com.thrcat.User;
import com.thrcat.UserServiceRpc;
import com.thrcat.service.UserService;
import com.thrcat.logs.ErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/05
 */

@Service
public class UserServiceRpcImpl implements UserServiceRpc {


    @Autowired
    private UserService userService;

    @Override
    public ResultSet<Boolean> login(User user) {
        try {
            return ResultSet.ofSuccess(userService.login(user));
        } catch (Exception e) {
            ErrorLog.error("用户登陆异常", e);
            return ResultSet.ofFailed();
        }

    }
}
