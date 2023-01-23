package com.isa.repository;

import com.isa.domain.model.CenterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CenterAccountRepository extends JpaRepository<CenterAccount, Long> {

    @Query("""
            SELECT centerAccount FROM CenterAccount centerAccount
            WHERE centerAccount.name = :searchFilter
            """)
    List<CenterAccount> findByName(@Param("searchFilter") String searchFilter);
}
