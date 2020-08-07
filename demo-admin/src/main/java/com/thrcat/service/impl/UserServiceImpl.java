package com.thrcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thrcat.User;
import com.thrcat.mapper.UserMapper;
import com.thrcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        return userMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return login(user);
    }

    @Override
    public User findUser(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }
}
