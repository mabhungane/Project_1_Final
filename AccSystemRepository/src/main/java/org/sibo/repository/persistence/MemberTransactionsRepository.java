package org.sibo.repository.persistence;

import org.sibo.domain.persistence.Member_transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MemberTransactionsRepository extends JpaRepository<Member_transactions,Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from Member_transactions join Account_Type on Member_transactions.accountType=AccountType.id" +
            "join Members on Member_transactions.Person_ID=Members.Person_ID where Members.email=:email",nativeQuery = true)
    List<Member_transactions> getMemberTransactions(String email);


    @Modifying
    @Transactional
    @Query(value = "delete from Member_transactions join Account_Type on Member_transactions.accountType=AccountType.id" +
            "join Members on Member_transactions.Person_ID=Members.Person_ID where Members.email=:email",nativeQuery = true)
    void deleteByEmail(String email);
}
