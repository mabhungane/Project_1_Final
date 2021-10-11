package org.sibo.logic.flows;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;
import org.sibo.logic.AccountMembersService;
import org.sibo.translator.MemberGoalsTranslator;
import org.sibo.translator.MemberTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMemverServiceImpl implements AccountMembersService {
    private MemberTranslator memberTranslator;
    private MemberGoalsTranslator memberGoalsTranslator;


    @Autowired
    public AccountMemverServiceImpl(MemberTranslator memberTranslator, MemberGoalsTranslator memberGoalsTranslator) {
        this.memberTranslator = memberTranslator;
        this.memberGoalsTranslator = memberGoalsTranslator;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberTranslator.getAllMembers();
    }

    @Override
    public MemberDTO getMember(String email) {
        return memberTranslator.getMember(email);
    }

    @Override
    public MemberDTO deleteMember(String email) {
        return memberTranslator.deleteMember(email);
    }

    @Override
    public MemberDTO addMember(MemberDTO memberDTO) {
        MemberDTO memberDTO1 = memberTranslator.addMember(memberDTO);
        memberGoalsTranslator.addGoals(memberDTO);
        return memberDTO1;
    }

    @Override
    public void updatePoints(String email, Member_transactionsDTO member_transactionsDTO) {
        memberTranslator.updatePoints(email, member_transactionsDTO);
    }
}
