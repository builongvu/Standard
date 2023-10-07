package com.standard.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.standard.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSearchResponse extends BaseDto {

    List<BookResponse> content;

}
