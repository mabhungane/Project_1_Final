package org.sibo.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Members")
public class Account_Members implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private double amount;
    private Member_Goals member_goals;
    private Set<Member_transactions> member_transactions;


    public Account_Members() {
    }

    public Account_Members(Long id, String name, String surname, int age, String email, double amount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.amount = amount;
    }

    @Id
    @SequenceGenerator(name="MY_SEQ_PERSON",sequenceName = "PERSON_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_PERSON")
    @Column(name="Person_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "points")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToOne(targetEntity = Member_Goals.class, fetch = FetchType.LAZY,mappedBy = "member",orphanRemoval = true,cascade = CascadeType.PERSIST )
    public Member_Goals getMember_goals() {
        return member_goals;
    }

    public void setMember_goals(Member_Goals member_goals) {
        this.member_goals = member_goals;
    }

    @OneToMany(targetEntity = Member_transactions.class,fetch = FetchType.LAZY, mappedBy = "member",orphanRemoval = true,cascade = CascadeType.PERSIST)
    public Set<Member_transactions> getMember_transactions() {
        return member_transactions;
    }

    public void setMember_transactions(Set<Member_transactions> member_transactions) {
        this.member_transactions = member_transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account_Members accountMembers = (Account_Members) o;
        return age == accountMembers.age && Double.compare(accountMembers.amount, amount) == 0 && Objects.equals(id, accountMembers.id) && Objects.equals(name, accountMembers.name) && Objects.equals(surname, accountMembers.surname) && Objects.equals(email, accountMembers.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, amount);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                '}';
    }
}
