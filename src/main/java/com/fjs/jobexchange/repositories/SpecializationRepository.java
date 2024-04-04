package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Specialization;
import com.fjs.jobexchange.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
}
