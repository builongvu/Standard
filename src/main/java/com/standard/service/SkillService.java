package com.standard.service;

import com.standard.dto.request.SkillRequest;
import com.standard.entity.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAll();

    Skill getById(long id);

    Skill create(SkillRequest skillRequest);

    Skill update(long id, SkillRequest skillRequest);

    void delete(long id);

}
