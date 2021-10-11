package org.sibo.logic;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;

import java.util.List;

public interface AccountMembersService {
    List<MemberDTO> getAllMembers();
    MemberDTO getMember(String email);
    MemberDTO deleteMember(String email);
    MemberDTO addMember(MemberDTO memberDTO);
    void updatePoints(String email, Member_transactionsDTO member_transactionsDTO);
}
