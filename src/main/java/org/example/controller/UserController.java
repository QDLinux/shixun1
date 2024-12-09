package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    public User getUserById(Integer id)
    {
        //接受参数
        //调用模型层（业务逻辑，持久层）
        return userService.getUserById(id);
    }

    @RequestMapping("/addUser")
    public Integer addUser(User user)
    {
        return userService.addUser(user);
    }
    @RequestMapping("/delUserById")
    public Integer delUserById(Integer id)
    {
        return userService.delUserById(id);
    }
    @RequestMapping("/updateUser")
    public Integer updateUser(User user)
    {
        return userService.updateUser(user);
    }
}
