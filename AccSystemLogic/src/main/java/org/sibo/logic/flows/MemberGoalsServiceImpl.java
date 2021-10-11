package org.sibo.logic.flows;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.MemberGoalsDTO;
import org.sibo.logic.MemberGoalsService;
import org.sibo.translator.MemberGoalsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberGoalsServiceImpl implements MemberGoalsService {
    private MemberGoalsTranslator memberGoalsTranslator;

    @Autowired
    public MemberGoalsServiceImpl(MemberGoalsTranslator memberGoalsTranslator) {
        this.memberGoalsTranslator = memberGoalsTranslator;
    }

    @Override
    public List<MemberGoalsDTO> getAllMemberGoals() {
        return memberGoalsTranslator.getAllMemberGoals();
    }

    @Override
    public MemberGoalsDTO getMemberGoals(String email) {
        return memberGoalsTranslator.getMemberGoals(email);
    }

    @Override
    public MemberGoalsDTO updateGoals(String email, MemberGoalsDTO memberGoalsDTO) {
        return memberGoalsTranslator.updateGoals(email,memberGoalsDTO);
    }

    @Override
    public void deleteMemberGoals(String email) {
        memberGoalsTranslator.deleteMemberGoals(email);
    }

    @Override
    public void addGoals(MemberDTO memberGoalsDTO) {
        memberGoalsTranslator.addGoals(memberGoalsDTO);
    }
}
