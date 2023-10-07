package com.standard.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.standard.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorSearchRequest extends BaseDto {

    Long id;
    String name;
    Date dateOfBirth;
    String hometown;
    String description;

}
