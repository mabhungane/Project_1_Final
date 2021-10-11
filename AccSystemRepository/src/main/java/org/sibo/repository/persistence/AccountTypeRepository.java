package org.sibo.repository.persistence;

import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.domain.persistence.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {
    @Query(value = "select * from account_type where nmonic=:nmonic", nativeQuery = true )
    AccountType getByNmonic(@Param("nmonic") String nmonic);


    @Transactional
    @Modifying
    @Query(value = "delete from account_type where nmonic=:nmonic",nativeQuery = true)
    AccountType deleteByNmonic(@Param("nmonic") String nmonic);
}
