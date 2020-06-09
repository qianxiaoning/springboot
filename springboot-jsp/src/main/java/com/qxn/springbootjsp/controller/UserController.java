package com.qxn.springbootjsp.controller;

import com.qxn.springbootjsp.pojo.User;
import com.qxn.springbootjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/sync")
    public String sync(Model model) {
        List<User> userList = userService.findUsers();
        model.addAttribute("userList", userList);
        return "sync";
    }
    @GetMapping("/async")
    public String async() {
        return "async";
    }
    @GetMapping("/findUsersByAsync")
    @ResponseBody
    public List<User> findUsersByAsync() {
        List<User> userList = userService.findUsers();
        return userList;
    }
}
