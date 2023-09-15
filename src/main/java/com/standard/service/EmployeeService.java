package com.standard.service;

import com.standard.dto.request.EmployeeRequest;
import com.standard.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Long id);

    Employee create(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    void delete(long id);

    Employee addSkills(long id, List<Long> skillIds);

}
