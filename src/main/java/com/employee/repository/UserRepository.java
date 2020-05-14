package com.employee.repository;

import com.employee.entity.User;
import com.employee.entity.enumeration.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    List<User> findAllByUserRole(RoleType userRole);

}
