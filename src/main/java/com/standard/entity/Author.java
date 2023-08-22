package com.standard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date dateOfBirth;
    private String homeTown;
    private String description;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
