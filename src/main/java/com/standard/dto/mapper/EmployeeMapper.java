package com.standard.dto.mapper;

import com.standard.dto.request.EmployeeRequest;
import com.standard.dto.response.EmployeeResponse;
import com.standard.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeRequest employeeRequest);

    EmployeeResponse toResponse(Employee employee);

    List<EmployeeResponse> toResponses(List<Employee> employees);

}
