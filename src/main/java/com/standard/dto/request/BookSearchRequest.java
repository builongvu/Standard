package com.standard.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.standard.dto.BaseDto;
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
public class BookSearchRequest extends BaseDto {

    Long id;
    String name;
    Date publicationDate;
    Long authorId;

}
