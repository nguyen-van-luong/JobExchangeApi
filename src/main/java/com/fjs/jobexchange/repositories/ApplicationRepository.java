package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Application;
import com.fjs.jobexchange.models.Bookmark;
import com.fjs.jobexchange.models.CV;
import com.fjs.jobexchange.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findByCvIdAndJobId(Integer cv_id, Integer job_id);

    @Query("SELECT a FROM Application a WHERE a.job.id = :job_id AND a.cv.student.id = :student_id")
    List<Application> findByJobIdAndStudentId(@Param("job_id") Integer job_id, @Param("student_id") Integer student_id);

    @Query("SELECT a FROM Application a WHERE a.job.employer.id = :employerId AND (:title IS NULL OR a.job.title LIKE %:title%) AND (:status IS NULL OR a.status = :status)")
    Page<Application> findByJobEmployerIdAndJobTitleAndStatus(@Param("employerId") int employerId,@Param("title") String title,@Param("status") String status, Pageable pageable);
}
