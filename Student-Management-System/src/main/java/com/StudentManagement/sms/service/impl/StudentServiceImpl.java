package com.StudentManagement.sms.service.impl;

import com.StudentManagement.sms.dto.StudentDto;
import com.StudentManagement.sms.entity.Student;
import com.StudentManagement.sms.mapper.StudentMapper;
import com.StudentManagement.sms.repository.StudentRepository;
import com.StudentManagement.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {

        List<Student> students=studentRepository.findAll();
        List<StudentDto> studentDtos=
                students.stream().map(student-> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
        return studentDtos;

    }

    @Override
    public void createStudent(Student student) {
//        Student student=StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student=studentRepository.findById(studentId).get();
        StudentDto studentDto=StudentMapper.mapToStudentDto(student);
        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.mapToStudent(studentDto));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Optional<StudentDto> getStudentByFirstName(String firstname) {
        Student s = studentRepository.findByFirstName(firstname);

        if(s!=null) {
            StudentDto sdto = StudentMapper.mapToStudentDto(s);

            return Optional.of(sdto);
        }else{
            return Optional.empty();
        }
    }




}
