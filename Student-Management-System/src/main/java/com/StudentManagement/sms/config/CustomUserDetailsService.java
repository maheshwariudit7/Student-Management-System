package com.StudentManagement.sms.config;

import com.StudentManagement.sms.entity.User;
import com.StudentManagement.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByEmailOrUsername(username,username);
        if(user!=null){

            // if user is found then we are converting it into spring security provided user

            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                    user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()));
        }
        else{
            throw new UsernameNotFoundException("Invalid Email or Password");
        }
    }
}
