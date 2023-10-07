package com.standard.controller;

import com.standard.dto.mapper.UnitMapper;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.response.UnitResponse;
import com.standard.entity.Unit;
import com.standard.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/standard")
public class StandardController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<Unit> unitResponses = unitService.getAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(unitResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
