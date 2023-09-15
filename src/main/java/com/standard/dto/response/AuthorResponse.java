package com.standard.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse {

    long id;
    String name;
    Date dateOfBirth;
    String hometown;
    String description;

}
