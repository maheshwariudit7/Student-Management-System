package com.StudentManagement.sms.repository;

import com.StudentManagement.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByFirstName(String firstname);
}
