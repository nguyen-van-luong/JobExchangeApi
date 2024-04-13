package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository  extends JpaRepository<CV, Integer> {

    @Query("SELECT p FROM CV p JOIN p.industrySpecializations u JOIN p.province t WHERE (p.fullname LIKE %:searchContent% OR p.description LIKE %:searchContent% OR p.workingForm LIKE %:searchContent%) AND (:industry IS NULL OR :industry IN (SELECT u.industry.name FROM p.industrySpecializations u)) AND (:province IS NULL OR t.name = :province) AND p.isPrivate = false")
    Page<CV> findByProvinceAndIndustryAndMore(@Param("searchContent") String searchContent, @Param("industry") String industry, @Param("province") String province,
                                               Pageable pageable);
}
