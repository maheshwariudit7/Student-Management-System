package com.StudentManagement.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto{

    private Long id;

    @NotEmpty(message = "Student first name should not be empty")
    private String firstName;

    @NotEmpty(message = "Student last name can not be empty")
    private String lastName;

    @NotEmpty(message = "Student email can not be empty")
    @Email
    private String email;
}
