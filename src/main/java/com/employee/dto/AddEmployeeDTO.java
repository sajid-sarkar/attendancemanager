package com.employee.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class AddEmployeeDTO {

    public String fullname;
    public String email;
    public String age;
    public String designation;

    @Override
    public String toString() {
        return "AddEmployeeDTO{" + "fullname='" + fullname + '\'' + ", email='" + email + '\'' + ", age='" + age + '\'' + ", designation='"
            + designation + '\'' + '}';
    }
}
