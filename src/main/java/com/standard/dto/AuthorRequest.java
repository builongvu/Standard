package com.standard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private Integer id;
    private String name;
    private Date dateOfBirth;
    @NotBlank(message = "hometown can not be blank")
    private String homeTown;
    private String description;

}
