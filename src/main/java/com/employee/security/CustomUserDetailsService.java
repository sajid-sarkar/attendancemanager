package com.employee.security;

import com.employee.entity.enumeration.RoleType;
import com.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Primary
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.employee.entity.User user = userRepository
            .findOneByEmail(email)
            .orElseThrow(() -> new BadCredentialsException("login.incorrect_credentials"));

        RoleType role = user.getUserRole();
        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        UserDetails userDetails = new User(user.getEmail(), user.getPassword(), authorities);

        return userDetails;
    }
}
