package com.employee.controller;

import com.employee.entity.User;
import com.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/dashboard")
    public String dashboard(Model model) {
        User user = userService.getCurrentUser();
        System.out.println("user name" + user.getEmail());
        model.addAttribute("user", user);
        return "user/dashboard";
    }

}
