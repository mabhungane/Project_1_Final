package org.sibo.translator;

import org.sibo.domain.DTOs.Member_transactionsDTO;

import java.util.List;

public interface MemberTransactionsTranslator {
    List<Member_transactionsDTO> getMemberTransactions(String email);
    void deleteMemberTransactions(String email);
    Member_transactionsDTO addNewTransaction(Member_transactionsDTO member_transactionsDTO);
}
