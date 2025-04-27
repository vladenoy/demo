package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Transactional
    @Modifying
    @Query(value = """
            UPDATE account
            SET balance = balance * 1.1
            WHERE balance < initial_balance * 2.07
            """,
            nativeQuery = true)
    void increaseBalanceByTenPercent();
}
