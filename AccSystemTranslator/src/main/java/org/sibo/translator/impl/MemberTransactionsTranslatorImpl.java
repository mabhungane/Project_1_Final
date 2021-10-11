package org.sibo.translator.impl;

import org.sibo.domain.DTOs.MemberGoalsDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;
import org.sibo.domain.persistence.Member_Goals;
import org.sibo.domain.persistence.Member_transactions;
import org.sibo.repository.persistence.MemberTransactionsRepository;
import org.sibo.translator.MemberTransactionsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class MemberTransactionsTranslatorImpl  implements MemberTransactionsTranslator {
    private MemberTransactionsRepository memberTransactionsRepository;


    @Autowired
    public MemberTransactionsTranslatorImpl(MemberTransactionsRepository memberTransactionsRepository) {
        this.memberTransactionsRepository = memberTransactionsRepository;
    }

    @Override
    public List<Member_transactionsDTO> getMemberTransactions(String email) {
        List<Member_transactionsDTO> alltransactions = new ArrayList<>();
        try {
            for (Member_transactions e:memberTransactionsRepository.getMemberTransactions(email)){
                alltransactions.add(new Member_transactionsDTO(e));
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot get all member transactions",e);
        }
        return alltransactions;
    }

    @Override
    public void deleteMemberTransactions(String email) {
        try {
            memberTransactionsRepository.deleteByEmail(email);
        }catch (Exception e){
            throw new RuntimeException("Cannot delete member transactions",e);
        }
    }

    @Override
    public Member_transactionsDTO addNewTransaction(Member_transactionsDTO member_transactionsDTO) {
        Member_transactions member_transactions;
        try {
            member_transactions = member_transactionsDTO.buildMemberTransaction(
                    member_transactionsDTO.getAccountType(),
                    member_transactionsDTO.getMember());

            memberTransactionsRepository.save(member_transactions);
        }catch (Exception e){
            throw new RuntimeException("Cannot add a new member transactions",e);
        }
        return new Member_transactionsDTO(member_transactions);
    }
}
