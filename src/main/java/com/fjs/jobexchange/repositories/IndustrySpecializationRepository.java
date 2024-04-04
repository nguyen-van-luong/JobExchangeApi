package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.IndustrySpecialization;
import com.fjs.jobexchange.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustrySpecializationRepository extends JpaRepository<IndustrySpecialization, Integer> {
}
