package com.StudentManagement.sms.service.impl;

import com.StudentManagement.sms.entity.User;
import com.StudentManagement.sms.repository.UserRepository;
import com.StudentManagement.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }
    @Override
    public void createUser(User user) {

        User user1=new User();

        user1.setUserId(user.getUserId());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());


        userRepository.save(user1);
    }
}
