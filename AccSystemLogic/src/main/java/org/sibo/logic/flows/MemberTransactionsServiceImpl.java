package org.sibo.logic.flows;

import org.sibo.domain.DTOs.Member_transactionsDTO;
import org.sibo.logic.MemberTransactionsService;
import org.sibo.translator.MemberTransactionsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberTransactionsServiceImpl implements MemberTransactionsService {
    private MemberTransactionsTranslator memberTransactionsTranslator;

    @Autowired
    public MemberTransactionsServiceImpl(MemberTransactionsTranslator memberTransactionsTranslator) {
        this.memberTransactionsTranslator = memberTransactionsTranslator;
    }

    @Override
    public List<Member_transactionsDTO> getMemberTransactions(String email) {
        return memberTransactionsTranslator.getMemberTransactions(email);
    }

    @Override
    public void deleteMemberTransactions(String email) {
        memberTransactionsTranslator.deleteMemberTransactions(email);
    }

    @Override
    public Member_transactionsDTO addNewTransaction(Member_transactionsDTO member_transactionsDTO) {
        return memberTransactionsTranslator.addNewTransaction(member_transactionsDTO);
    }
}
