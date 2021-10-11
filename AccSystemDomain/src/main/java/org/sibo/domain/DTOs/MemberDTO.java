package org.sibo.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sibo.domain.persistence.Account_Members;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private String name;
    private String surname;
    private int age;
    private String email;
    private double amount;
    private MemberGoalsDTO memberGoalsDTO;

    public MemberDTO() {
    }

    public MemberDTO(String name, String surname, int age, String email, double amount) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.amount = amount;
    }

    public MemberDTO(Account_Members accountMembers){
        this.name = accountMembers.getName();
        this.surname = accountMembers.getSurname();
        this.age = accountMembers.getAge();
        this.email = accountMembers.getEmail();
        this.amount = accountMembers.getAmount();
        if(null!= accountMembers.getMember_goals()){
            this.memberGoalsDTO = new MemberGoalsDTO(accountMembers.getMember_goals());
        }
    }

    @JsonIgnore
    public Account_Members buildMember(MemberDTO member){
        return new Account_Members(null, getName(), getSurname(), getAge(), getEmail(), getAmount());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public MemberGoalsDTO getMemberGoalsDTO() {
        return memberGoalsDTO;
    }

    public void setMemberGoalsDTO(MemberGoalsDTO memberGoalsDTO) {
        this.memberGoalsDTO = memberGoalsDTO;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                '}';
    }
}
