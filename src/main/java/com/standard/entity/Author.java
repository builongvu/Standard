package com.standard.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author extends BaseEntity {

    private String name;
    private Date dateOfBirth;
    private String hometown;
    private String description;

}
