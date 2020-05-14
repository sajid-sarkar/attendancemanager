package com.employee.services;

import com.employee.dto.AddEmployeeDTO;
import com.employee.entity.User;

public interface UserService {

    void createUser(AddEmployeeDTO addEmployeeDTO);

    User getCurrentUser();

    void updatePasswordMail(User user);

}
