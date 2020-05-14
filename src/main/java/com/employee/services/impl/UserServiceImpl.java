package com.employee.services.impl;

import com.employee.entity.enumeration.RoleType;
import com.employee.repository.UserRepository;
import com.employee.dto.AddEmployeeDTO;
import com.employee.entity.User;
import com.employee.entity.enumeration.SignupStatus;
import com.employee.services.EmailService;
import com.employee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void createUser(AddEmployeeDTO addEmployeeDTO) {
        User newuser = new User();
        newuser.setFullname(addEmployeeDTO.getFullname());
        newuser.setEmail(addEmployeeDTO.getEmail());
        newuser.setAge(addEmployeeDTO.getAge());
        newuser.setDesignation(addEmployeeDTO.getDesignation());
        newuser.setUsername(addEmployeeDTO.getEmail());
        newuser.setUserRole(RoleType.ROLE_EMPLOYEE);
        newuser.setSignupStatus(SignupStatus.SIGNUP_INCOMPLETE);
        userRepository.save(newuser);
        updatePasswordMail(newuser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findOneByEmail(authentication.getName());
        return user.get();
    }

    @Override
    public void updatePasswordMail(User user) {
        User findUser = userRepository.findOneByEmail(user.getEmail()).get();
        emailService.sendUpdatePasswordEmail(findUser);
    }

}
