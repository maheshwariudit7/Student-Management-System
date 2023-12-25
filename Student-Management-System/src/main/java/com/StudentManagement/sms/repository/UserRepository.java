package com.StudentManagement.sms.repository;

import com.StudentManagement.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailOrUsername(String email,String username);
}
