package org.sibo.repository.persistence;

import org.sibo.domain.persistence.Member_Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MemberGoalsRepo extends JpaRepository<Member_Goals,Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from MemberGoals join Members on Member_Goals.Person_ID=Members.person_id where Members.email=:email",nativeQuery = true)
    Member_Goals getGoalsForMember(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update membergoals set user_spending=:spending,user_driving=:driving,user_fitnesshealth=:hf" +
            "where person_id in( " +
            "select * from members where Members.email=:email)",nativeQuery = true)
    void updateGoals(@Param("email") String email,@Param("driving") int driving,@Param("hf") int fitnesshealth,@Param("spending") int spending);


    @Modifying
    @Transactional
    @Query(value = "delete from Member_Goals join Members on Member_Goals.Person_ID=Members.person_id where Members.email=:email",nativeQuery = true)
    void deleteByUserEmail(String email);

}
