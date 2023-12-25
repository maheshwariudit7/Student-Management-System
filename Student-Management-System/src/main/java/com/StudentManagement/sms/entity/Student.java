package com.StudentManagement.sms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Student first name should not be empty")
    private String firstName;

    @NotEmpty(message = "Student last name should not be empty")
    private String lastName;

    @Email
    @Column(unique = true,nullable = false)
    @NotEmpty(message = "Student email should not be empty")
    private String email;
}
