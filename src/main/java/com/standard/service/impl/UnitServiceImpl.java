package com.standard.service.impl;

import com.standard.aop.TimeMonitor;
import com.standard.constant.ErrorEnum;
import com.standard.dto.request.UnitRequest;
import com.standard.entity.Employee;
import com.standard.entity.Unit;
import com.standard.exception.ApplicationException;
import com.standard.dto.mapper.UnitMapper;
import com.standard.repository.UnitRepository;
import com.standard.service.EmployeeService;
import com.standard.service.UnitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final EmployeeService employeeService;

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getById(Long id) {
        Optional<Unit> optionalUnit = unitRepository.findById(id);
        return optionalUnit.orElseThrow(() -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Unit", "id", id.toString()));
    }

    @Override
    public Unit create(UnitRequest unitRequest) {
        Unit unit = UnitMapper.INSTANCE.toEntity(unitRequest);
        return unitRepository.save(unit);
    }

    @Override
    public Unit update(long id, UnitRequest unitRequest) {
        Unit unit = UnitMapper.INSTANCE.toEntity(unitRequest);
        unit.setId(id);
        return unitRepository.save(unit);
    }

    @Override
    public void delete(Long id) {
        Unit unit = getById(id);
        unitRepository.delete(unit);
    }

    @Transactional
    @Override
    public Unit addEmployees(long id, List<Long> employeeIds) {
        Unit unit = getById(id);

        employeeIds.forEach(employeeId -> {
            Employee employee = employeeService.getById(employeeId);
            employee.setUnit(unit);
            unit.getEmployees().add(employee);
        });

        return unitRepository.save(unit);
    }


}
