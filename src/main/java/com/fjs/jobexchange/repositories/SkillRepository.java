package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Job;
import com.fjs.jobexchange.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository  extends JpaRepository<Skill, Integer> {
}
