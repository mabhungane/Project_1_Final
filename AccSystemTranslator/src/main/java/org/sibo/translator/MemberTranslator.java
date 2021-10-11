package org.sibo.translator;

import javassist.compiler.ast.Member;
import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;

import java.util.List;

public interface MemberTranslator {
    List<MemberDTO> getAllMembers();
    MemberDTO getMember(String email);
    MemberDTO deleteMember(String email);
    MemberDTO addMember(MemberDTO memberDTO);
    void updatePoints(String email, Member_transactionsDTO transactionsDTO);
}
