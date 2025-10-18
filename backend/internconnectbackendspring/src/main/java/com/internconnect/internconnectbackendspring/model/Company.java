package com.internconnect.internconnectbackendspring.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
    @Column(name = "companyName", nullable = false, length = 255)
    private String companyName;
    @Column(name = "industry", nullable = false, length = 255)
    private String industry;
    @Column(name = "contactPerson", nullable = false, length = 255)
    private String contactPerson;
    @Column(name = "contactPhone", nullable = false, length = 255)
    private String contactPhone;
}
