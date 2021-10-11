package org.sibo.translator.impl;

import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;
import org.sibo.domain.persistence.Account_Members;
import org.sibo.repository.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class MemberTranslatorImpl implements org.sibo.translator.MemberTranslator {

    private MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> allMembers = new ArrayList<>();
        try {
            for (Account_Members e:memberRepository.findAll()){
                allMembers.add(new MemberDTO(e));
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot get all members",e);
        }
        return allMembers;
    }

    @Override
    public MemberDTO getMember(String email) {
        Account_Members account_member;
        try {
            account_member = memberRepository.getMember(email);
        }catch (Exception e){
            throw new RuntimeException("Cannot get the member member with the email",e);
        }
        return new MemberDTO(account_member);
    }

    @Override
    public MemberDTO deleteMember(String email) {
        Account_Members members;
        try {
            members = memberRepository.getMember(email);
            memberRepository.delete(members);
        }catch (Exception e){
            throw new RuntimeException("Cannot delete the member from the db",e);
        }
        return new MemberDTO(members);
    }

    @Override
    public MemberDTO addMember(MemberDTO memberDTO) {
        Account_Members members;
        try {
            members = memberDTO.buildMember(memberDTO);
            memberRepository.save(members);
        }catch (Exception e){
            throw new RuntimeException("Cannot get all members",e);
        }
        return new MemberDTO(members);
    }

    @Override
    public void updatePoints(String email, Member_transactionsDTO member_transactionsDTO) {
        int points = memberRepository.getPoints(email);
        try {
            if(member_transactionsDTO.getTransaction_name().equalsIgnoreCase("add")){
                points+=member_transactionsDTO.getAmount();
                memberRepository.updateMemberPoints(email,points);
            }else if(member_transactionsDTO.getTransaction_name().equalsIgnoreCase("subtract")){
                points-=member_transactionsDTO.getAmount();
                memberRepository.updateMemberPoints(email,points);
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot update the points for the member",e);
        }
    }
}
