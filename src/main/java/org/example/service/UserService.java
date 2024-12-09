package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
    public Integer addUser(User user)
    {
        //验证账号是否唯一
        return userMapper.addUser(user);
    }
    public Integer delUserById(Integer id)
    {
        //验证账号是否唯一
        return userMapper.delUserById(id);
    }
    public Integer updateUser(User user)
    {
        return userMapper.updateUser(user);
    }
}
