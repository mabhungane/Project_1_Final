package org.sibo.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "MemberGoals")
public class Member_Goals implements Serializable {
    private Long id;
    private int spending;
    private int driving;
    private int fitnesshealth;
    private int goal;
    private Account_Members accountMembers;

    public Member_Goals() {
    }

    public Member_Goals(Long id, int spending, int driving, int fitnesshealth, int goal, Account_Members accountMembers) {
        this.id = id;
        this.spending = spending;
        this.driving = driving;
        this.fitnesshealth = fitnesshealth;
        this.goal = goal;
        this.accountMembers = accountMembers;
    }

    @Id
    @SequenceGenerator(name="MY_SEQ_Goals",sequenceName = "MEMBER_GOALS_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_Goals")
    @Column(name = "goal_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_spending")
    public int getSpending() {
        return spending;
    }

    public void setSpending(int spending) {
        this.spending = spending;
    }

    @Column(name = "user_driving")
    public int getDriving() {
        return driving;
    }

    public void setDriving(int driving) {
        this.driving = driving;
    }

    @Column(name = "user_fitnesshealth")
    public int getFitnesshealth() {
        return fitnesshealth;
    }

    public void setFitnesshealth(int fitnesshealth) {
        this.fitnesshealth = fitnesshealth;
    }

    @Column(name = "user_goaltomeet")
    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Person_ID")
    public Account_Members getMember() {
        return accountMembers;
    }

    public void setMember(Account_Members accountMembers) {
        this.accountMembers = accountMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member_Goals that = (Member_Goals) o;
        return id == that.id && spending == that.spending && driving == that.driving && fitnesshealth == that.fitnesshealth && goal == that.goal && Objects.equals(accountMembers, that.accountMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spending, driving, fitnesshealth, goal, accountMembers);
    }

    @Override
    public String toString() {
        return "Member_Goals{" +
                "id=" + id +
                ", spending=" + spending +
                ", driving=" + driving +
                ", fitnesshealth=" + fitnesshealth +
                ", goal=" + goal +
                ", member=" + accountMembers +
                '}';
    }
}
