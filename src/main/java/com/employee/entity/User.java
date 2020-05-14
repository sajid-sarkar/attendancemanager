package com.employee.entity;

import com.employee.entity.enumeration.RoleType;
import com.employee.entity.enumeration.SignupStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "fullname")
    private String fullname;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @NotNull
    @Column(name = "age")
    private String age;

    @NotNull
    @Column(name = "designation")
    private String designation;

    @NotNull
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private RoleType userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_status")
    private SignupStatus signupStatus;

}
