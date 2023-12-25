package com.StudentManagement.sms.service;

import com.StudentManagement.sms.dto.StudentDto;
import com.StudentManagement.sms.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> getAllStudents();

    void createStudent(Student student);

    StudentDto getStudentById(Long studentId);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);

    Optional<StudentDto> getStudentByFirstName(String username);
}
