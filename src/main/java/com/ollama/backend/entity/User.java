package com.ollama.backend.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String ssn;
}
