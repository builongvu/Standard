package com.standard.controller;

import com.standard.dto.mapper.AddressMapper;
import com.standard.dto.request.AddressRequest;
import com.standard.dto.response.AddressResponse;
import com.standard.dto.response.ApiResponse;
import com.standard.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<AddressResponse> addressResponses = AddressMapper.INSTANCE.toResponses(addressService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(addressResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        AddressResponse addressResponse = AddressMapper.INSTANCE.toResponse(addressService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(addressResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid AddressRequest addressRequest) {
        AddressResponse savedAddressResponse = AddressMapper.INSTANCE.toResponse(addressService.create(addressRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(savedAddressResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid AddressRequest addressRequest) {
        AddressResponse updatedAddressResponse = AddressMapper.INSTANCE.toResponse(addressService.update(id, addressRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(updatedAddressResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        addressService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
