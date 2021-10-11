package org.sibo.translator;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.MemberGoalsDTO;

import java.util.List;

public interface MemberGoalsTranslator {
    List<MemberGoalsDTO> getAllMemberGoals();
    MemberGoalsDTO getMemberGoals(String email);
    MemberGoalsDTO updateGoals(String email, MemberGoalsDTO memberGoalsDTO);
    void deleteMemberGoals(String email);
    void addGoals(MemberDTO memberGoalsDTO);
}
