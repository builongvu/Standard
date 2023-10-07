package com.standard.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.standard.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorSearchResponse extends BaseDto {

    List<AuthorResponse> content;

}
