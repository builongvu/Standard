package com.standard.controller;

import com.standard.dto.mapper.UnitMapper;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.request.UnitRequest;
import com.standard.dto.response.UnitResponse;
import com.standard.service.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<UnitResponse> unitResponses = UnitMapper.INSTANCE.toResponses(unitService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(unitResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        UnitResponse unitResponse = UnitMapper.INSTANCE.toResponse(unitService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(unitResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid UnitRequest unitRequest) {
        UnitResponse savedUnitResponse = UnitMapper.INSTANCE.toResponse(unitService.create(unitRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(savedUnitResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid UnitRequest unitRequest) {
        UnitResponse updatedUnitResponse = UnitMapper.INSTANCE.toResponse(unitService.update(id, unitRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(updatedUnitResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        unitService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addEmployees(@PathVariable long id, @RequestBody List<Long> employeeIds) {
        UnitResponse unitResponse = UnitMapper.INSTANCE.toResponse(unitService.addEmployees(id, employeeIds));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(unitResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
