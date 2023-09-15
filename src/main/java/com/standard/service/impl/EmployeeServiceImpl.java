package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.request.EmployeeRequest;
import com.standard.entity.Address;
import com.standard.entity.Employee;
import com.standard.entity.Skill;
import com.standard.exception.ApplicationException;
import com.standard.dto.mapper.EmployeeMapper;
import com.standard.repository.EmployeeRepository;
import com.standard.service.AddressService;
import com.standard.service.EmployeeService;
import com.standard.service.SkillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final SkillService skillService;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElseThrow(() -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Employee", "id", id));
    }

    @Transactional
    @Override
    public Employee create(EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeRequest);

        if (employeeRequest.getAddressId() == null) {
            throw new ApplicationException(ErrorEnum.NULL_REQUEST_DATA, "addressId");
        }
        Address address = addressService.getById(employeeRequest.getAddressId());
        employee.setAddress(address);

        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeRequest);
        Address address = addressService.getById(employeeRequest.getAddressId());

        employee.setId(id);
        employee.setAddress(address);

        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
    }

    @Transactional
    @Override
    public Employee addSkills(long id, List<Long> skillIds) {
        Employee employee = getById(id);

        skillIds.forEach(skillId -> {
            Skill skill = skillService.getById(skillId);
            employee.getSkills().add(skill);
        });

        return employeeRepository.save(employee);
    }


}
