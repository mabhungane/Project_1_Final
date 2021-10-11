package org.sibo.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sibo.domain.persistence.Member_Goals;

import java.io.Serializable;

public class MemberGoalsDTO implements Serializable {
    private int spending;
    private int driving;
    private int fitnesshealth;

    public MemberGoalsDTO() {
    }

    public MemberGoalsDTO(Member_Goals member_goals){
        this.driving = member_goals.getDriving();
        this.fitnesshealth = member_goals.getFitnesshealth();
        this.spending = member_goals.getSpending();
    }

    @JsonIgnore
    public Member_Goals buildGoals(MemberDTO member){
        return new Member_Goals(null, getSpending(),getDriving(),getFitnesshealth(),50, member.buildMember(member));
    }

    public MemberGoalsDTO(int spending, int driving, int fitnesshealth) {
        this.spending = spending;
        this.driving = driving;
        this.fitnesshealth = fitnesshealth;
    }

    public int getSpending() {
        return spending;
    }

    public void setSpending(int spending) {
        this.spending = spending;
    }

    public int getDriving() {
        return driving;
    }

    public void setDriving(int driving) {
        this.driving = driving;
    }

    public int getFitnesshealth() {
        return fitnesshealth;
    }

    public void setFitnesshealth(int fitnesshealth) {
        this.fitnesshealth = fitnesshealth;
    }


    @Override
    public String toString() {
        return "MemberGoalsDTO{" +
                "spending=" + spending +
                ", driving=" + driving +
                ", fitnesshealth=" + fitnesshealth +
                '}';
    }
}
