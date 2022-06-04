package com.flask.app.core.repository;

import com.flask.app.core.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Integer> {
    Optional<ApplicantEntity> findByWorkEmailAddress(String email);
}
