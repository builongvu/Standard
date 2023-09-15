package com.standard.dto.mapper;

import com.standard.dto.request.AddressRequest;
import com.standard.dto.response.AddressResponse;
import com.standard.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toEntity(AddressRequest addressRequest);

    Address toEntity(AddressResponse addressResponse);

    AddressResponse toResponse(Address address);

    List<AddressResponse> toResponses(List<Address> addresses);

}
