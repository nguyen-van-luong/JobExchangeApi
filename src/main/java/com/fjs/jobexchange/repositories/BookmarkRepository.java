package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    Optional<Bookmark> findByStudentIdAndJobId(Student studentId, int jobId);
}
