package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("SELECT p FROM Job p JOIN p.industrySpecializations u JOIN p.addresses t WHERE (p.title LIKE %:searchContent% OR p.requirement LIKE %:searchContent% OR p.workingForm LIKE %:searchContent%) AND (:industry IS NULL OR :industry IN (SELECT u.industry.name FROM p.industrySpecializations u)) AND (:province IS NULL OR :province IN (SELECT t.province.name FROM p.addresses t)) AND p.isPrivate = false")
    Page<Job> findByProvinceAndIndustryAndMore(@Param("searchContent") String searchContent, @Param("industry") String industry, @Param("province") String province,
                                               Pageable pageable);


}
