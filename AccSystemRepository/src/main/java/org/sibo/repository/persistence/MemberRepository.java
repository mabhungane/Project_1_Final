package org.sibo.repository.persistence;

import org.sibo.domain.persistence.Account_Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.reflect.Member;

@Repository
public interface MemberRepository extends JpaRepository<Account_Members,Long> {

    @Query(value = "select * from members where email=:email",nativeQuery = true)
    Account_Members getMember(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update members set points=:amount where email=:email",nativeQuery = true)
    Account_Members updateMemberPoints(@Param("email") String email, @Param("amount") int amount);

    @Query(value = "select points from members where email=:email",nativeQuery = true)
    int getPoints(@Param("email") String email);

}
