package com.fjs.jobexchange.repositories;

import com.fjs.jobexchange.models.Authentication;
import com.fjs.jobexchange.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {
    Optional<Authentication> findByUser(User user);
    void deleteByUser(User user);
}
