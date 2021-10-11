package org.sibo.translator.impl;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.MemberGoalsDTO;
import org.sibo.domain.persistence.Member_Goals;
import org.sibo.repository.persistence.MemberGoalsRepo;
import org.sibo.translator.MemberGoalsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class MemberGoalsTranslatorImpl implements MemberGoalsTranslator {
    private MemberGoalsRepo memberGoalsRepo;

    @Autowired
    public MemberGoalsTranslatorImpl(MemberGoalsRepo memberGoalsRepo) {
        this.memberGoalsRepo = memberGoalsRepo;
    }

    @Override
    public List<MemberGoalsDTO> getAllMemberGoals() {
        List<MemberGoalsDTO> allGoals = new ArrayList<>();
        try {
            for (Member_Goals e:memberGoalsRepo.findAll()){
                allGoals.add(new MemberGoalsDTO(e));
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot get all goals",e);
        }
        return allGoals;
    }

    @Override
    public MemberGoalsDTO getMemberGoals(String email) {
        Member_Goals member_goals;
        try {
            member_goals = memberGoalsRepo.getGoalsForMember(email);
        }catch (Exception e){
            throw new RuntimeException("cannot get goals for the member",e);
        }
        return new MemberGoalsDTO(member_goals);
    }

    @Override
    public MemberGoalsDTO updateGoals(String email, MemberGoalsDTO memberGoalsDTO) {
        try {
            memberGoalsRepo.updateGoals(email,memberGoalsDTO.getDriving(),
            memberGoalsDTO.getFitnesshealth(),
            memberGoalsDTO.getSpending());
        }catch (Exception e){
            throw new RuntimeException("cannot update goals for the member",e);
        }
        return null;
    }

    @Override
    public void deleteMemberGoals(String email) {
        try {
            memberGoalsRepo.deleteByUserEmail(email);
        }catch (Exception e){
            throw new RuntimeException("cannot delete goals for the member",e);
        }
    }

    @Override
    public void addGoals(MemberDTO memberGoalsDTO) {
        Member_Goals member_goals;
        MemberGoalsDTO memberGoalsDTO1 = memberGoalsDTO.getMemberGoalsDTO();
        try {
            member_goals = memberGoalsDTO1.buildGoals(memberGoalsDTO);
            memberGoalsRepo.save(member_goals);
        }catch (Exception e){
            throw new RuntimeException("cannot add new goals for the member",e);
        }
    }
}
