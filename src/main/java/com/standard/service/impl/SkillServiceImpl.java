package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.mapper.SkillMapper;
import com.standard.dto.request.SkillRequest;
import com.standard.entity.Skill;
import com.standard.exception.ApplicationException;
import com.standard.repository.SkillRepository;
import com.standard.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getById(long id) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        return optionalSkill.orElseThrow(() ->
                new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Skill", "id", String.valueOf(id)));
    }

    @Override
    public Skill create(SkillRequest skillRequest) {
        Skill skill = SkillMapper.INSTANCE.toEntity(skillRequest);
        return skillRepository.save(skill);
    }

    @Override
    public Skill update(long id, SkillRequest skillRequest) {
        Skill skill = SkillMapper.INSTANCE.toEntity(skillRequest);
        skill.setId(id);
        return skillRepository.save(skill);
    }

    @Override
    public void delete(long id) {
        Skill skill = getById(id);
        skillRepository.delete(skill);
    }

}
