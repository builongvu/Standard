package com.standard.service;

import com.standard.dto.request.UnitRequest;
import com.standard.entity.Unit;

import java.util.List;

public interface UnitService {

    List<Unit> getAll();

    Unit getById(Long id);

    Unit create(UnitRequest unitRequest);

    Unit update(long id, UnitRequest unitRequest);

    void delete(Long Id);

    Unit addEmployees(long id, List<Long> employeeIds);

}
