package com.standard.dto.mapper;

import com.standard.dto.request.UnitRequest;
import com.standard.dto.response.UnitResponse;
import com.standard.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UnitMapper {

    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    Unit toEntity(UnitRequest unitRequest);

    UnitResponse toResponse(Unit unit);

    List<UnitResponse> toResponses(List<Unit> units);

}
