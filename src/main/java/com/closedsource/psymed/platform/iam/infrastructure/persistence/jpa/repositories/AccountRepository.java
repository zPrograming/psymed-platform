package com.closedsource.psymed.platform.iam.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUserName(String username);
    Optional<Account> findByUserName(String username);
}
