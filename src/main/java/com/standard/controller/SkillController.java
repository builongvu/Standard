package com.standard.controller;

import com.standard.dto.mapper.SkillMapper;
import com.standard.dto.request.SkillRequest;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.response.SkillResponse;
import com.standard.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<SkillResponse> skillResponses = SkillMapper.INSTANCE.toResponses(skillService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skillResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable long id) {
        SkillResponse skillResponse = SkillMapper.INSTANCE.toResponse(skillService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skillResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid SkillRequest skillRequest) {
        SkillResponse skillResponse = SkillMapper.INSTANCE.toResponse(skillService.create(skillRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(skillResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid SkillRequest skillRequest) {
        SkillResponse skillResponse = SkillMapper.INSTANCE.toResponse(skillService.update(id, skillRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skillResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        skillService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
