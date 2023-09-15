package com.standard.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.standard.entity.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    long id;
    String name;
    Boolean gender;
    Date dateOfBirth;
    AddressResponse address;
    List<SkillResponse> skills;

}
