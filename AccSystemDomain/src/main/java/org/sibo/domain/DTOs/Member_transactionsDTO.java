package org.sibo.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sibo.domain.persistence.Member_transactions;

import java.io.Serializable;

public class Member_transactionsDTO implements Serializable {
    private String transaction_name;
    private double amount;
    private AccountTypeDTO accountType;
    private MemberDTO member;

    public Member_transactionsDTO() {
    }

    public Member_transactionsDTO(String transaction_name, double amount, AccountTypeDTO accountType, MemberDTO member) {
        this.transaction_name = transaction_name;
        this.amount = amount;
        this.accountType = accountType;
        this.member = member;
    }

    public Member_transactionsDTO(Member_transactions member_transactions){
        this.transaction_name = member_transactions.getTransaction_name();
        this.amount = member_transactions.getAmount();
        if(null !=  member_transactions.getAccountType()){
            this.accountType = new AccountTypeDTO(member_transactions.getAccountType());
        }

        if(null!= member_transactions.getMember()){
            this.member = new MemberDTO(member_transactions.getMember());
        }

    }

    @JsonIgnore
    public Member_transactions buildMemberTransaction(AccountTypeDTO accountType, MemberDTO member){
        return new Member_transactions(null, this.getTransaction_name(), this.getAmount(), accountType.buildAccountType(accountType), member.buildMember(member));

    }

    public String getTransaction_name() {
        return transaction_name;
    }

    public void setTransaction_name(String transaction_name) {
        this.transaction_name = transaction_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AccountTypeDTO getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDTO accountType) {
        this.accountType = accountType;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Member_transactionsDTO{" +
                "transaction_name='" + transaction_name + '\'' +
                ", amount=" + amount +
                ", accountType=" + accountType +
                ", member=" + member +
                '}';
    }
}
