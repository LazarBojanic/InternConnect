package com.internconnect.internconnectbackendspring.model;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
    @Column(name = "firstName", nullable = false, length = 255)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 255)
    private String lastName;
    @Column(name = "school", nullable = false, length = 255)
    private String school;
    @Column(name = "yearOfStudy", nullable = false, length = 255)
    private String yearOfStudy;
}
