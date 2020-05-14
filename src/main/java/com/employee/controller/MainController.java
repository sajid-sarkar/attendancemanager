package com.employee.controller;

import com.employee.entity.User;
import com.employee.entity.enumeration.RoleType;
import com.employee.entity.enumeration.SignupStatus;
import com.employee.repository.UserRepository;
import com.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = { "/", "/login" })
    public String login() {
        return "login";
    }

    @GetMapping(value = "/loginsuccess")
    public String loginSuccess() {
        User user = userService.getCurrentUser();
        if (user.getUserRole() == RoleType.ROLE_HR) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @GetMapping(value = "/update-password/{id}")
    public String updatePassword(@PathVariable(name = "id") Integer userId, Model model) {
        model.addAttribute("id", userId);
        return "passwordUpdate";
    }

    @PostMapping(value = "/savepassword/{id}")
    public String savePassword(@RequestParam String password, @PathVariable Long id) {
        User user = userRepository.getOne(id);
        user.setPassword(passwordEncoder.encode(password));
        user.setSignupStatus(SignupStatus.SIGNUP_COMPLETE);
        userRepository.save(user);
        return "redirect:/login?signUp=true";
    }

    @RequestMapping(value = "/accessdenied")
    public String error() {
        return "error/403";
    }
}
