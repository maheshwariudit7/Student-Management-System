package com.StudentManagement.sms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "First Name can not be empty")
    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @NotEmpty(message = "Username can not be empty")
    @Column(nullable = false,unique = true)
    private String username;

    @NotEmpty(message = "Email can not be empty")
    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @NotEmpty(message = "Password can not be empty")
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="users_roles",joinColumns = @JoinColumn (name = "user_id",referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "roleId"))
    private Set<role> roles;
}
