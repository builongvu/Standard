package com.standard.dto.mapper;

import com.standard.dto.request.SkillRequest;
import com.standard.dto.response.SkillResponse;
import com.standard.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillResponse toResponse(Skill skill);

    List<SkillResponse> toResponses(List<Skill> skills);

    Skill toEntity(SkillRequest skillRequest);

}
