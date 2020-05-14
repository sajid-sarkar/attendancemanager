package com.employee.controller;

import com.employee.dto.AddEmployeeDTO;
import com.employee.entity.User;
import com.employee.entity.enumeration.RoleType;
import com.employee.repository.UserRepository;
import com.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/admin/dashboard")
    public String dashboard(Model model) {
        User user = userService.getCurrentUser();
        System.out.println("user name" + user.getEmail());
        List<User> users = userRepository.findAllByUserRole(RoleType.ROLE_EMPLOYEE);
        model.addAttribute("user", user);
        model.addAttribute("userList", users);
        return "admin/dashboard";
    }

    @PostMapping(value = "/admin/addemployee")
    public String saveEmployee(@ModelAttribute(name = "addEmployeeDTO") AddEmployeeDTO addEmployeeDTO, Model model) {
        System.out.println(addEmployeeDTO);
        userService.createUser(addEmployeeDTO);
        List<User> users = userRepository.findAllByUserRole(RoleType.ROLE_EMPLOYEE);
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("userList", users);
        return "admin/dashboard";
    }

}
