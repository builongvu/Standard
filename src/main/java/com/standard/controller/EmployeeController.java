package com.standard.controller;

import com.standard.dto.mapper.EmployeeMapper;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.request.EmployeeRequest;
import com.standard.dto.response.EmployeeResponse;
import com.standard.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<EmployeeResponse> employeeResponses = EmployeeMapper.INSTANCE.toResponses(employeeService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(employeeResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.toResponse(employeeService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(employeeResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeResponse savedEmployeeResponse = EmployeeMapper.INSTANCE.toResponse(employeeService.create(employeeRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(savedEmployeeResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeResponse updatedEmployeeResponse = EmployeeMapper.INSTANCE.toResponse(employeeService.update(id, employeeRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(updatedEmployeeResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        employeeService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addSkills(@PathVariable long id, @RequestBody List<Long> skillIds) {
        EmployeeResponse employeeResponse = EmployeeMapper.INSTANCE.toResponse(employeeService.addSkills(id, skillIds));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(employeeResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
