package com.StudentManagement.sms.controller;

import com.StudentManagement.sms.dto.StudentDto;
import com.StudentManagement.sms.entity.Student;
import com.StudentManagement.sms.entity.User;
import com.StudentManagement.sms.service.StudentService;
import com.StudentManagement.sms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;


    @GetMapping("/students/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    //handler method to handle new student request

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        //student model obj to store student form data

        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    //handler method to save student form submit request
    @PostMapping("/students/studentSave")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "create_student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    //handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId, Model model) {

        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "edit_student";
    }

    //handler method to handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@Valid @PathVariable("studentId") Long studentId,
                                @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
        }

        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    // handle delete student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    //    handler method to handle view student Request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "view_student";

    }


    // handler method to handle new user registration to access the api
    // basically this method will render the user_registration.html template
    @GetMapping("/students/userRegistration")
    public String newUserRegister(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user_registration";

    }

    // handler method to handle user registration form submit
    @PostMapping("/students/saveUser")
    public String registrationSuccessful(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("newUser", user);
            return "user_registration";
        }

        userService.createUser(user);
        return "redirect:/students/registrationSuccess";
    }

    @GetMapping("/students/registrationSuccess")
    public String regSuccess(Model model) {
        return "registration_success";
    }

    //handler method to handle login request
    @GetMapping("/students/login")
    public String login() {
        return "login";
    }

    //handler method to handle search form submission
    @PostMapping("/students/searchUser")
    public String searchUser(@RequestParam("name") String name, Model model) {
        Optional<StudentDto> s = studentService.getStudentByFirstName(name);
        if (s.isEmpty()) {
            model.addAttribute("error", "Student not found");
            return "home";
        }

        System.out.println(s);
        model.addAttribute("student", s.get());
        return "view_student";

    }


}
