package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository  extends JpaRepository<Industry, Integer> {
}
