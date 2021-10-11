package org.sibo.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "Member_transactions")
public class Member_transactions implements Serializable {
    private Long id;
    private String transaction_name;
    private double amount;
    private AccountType accountType;
    private Account_Members accountMembers;

    public Member_transactions() {
    }

    public Member_transactions(Long id, String transaction_name, double amount, AccountType accountType, Account_Members accountMembers) {
        this.id = id;
        this.transaction_name = transaction_name;
        this.amount = amount;
        this.accountType = accountType;
        this.accountMembers = accountMembers;
    }

    @Id
    @SequenceGenerator(name="MY_SEQ_Transactions",sequenceName = "MEMBER_TRANSACTIONS_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_Transactions")
    @Column(name = "transaction_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accounttype_id")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
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
        Member_transactions that = (Member_transactions) o;
        return Double.compare(that.amount, amount) == 0 && Objects.equals(id, that.id) && Objects.equals(transaction_name, that.transaction_name) && Objects.equals(accountType, that.accountType) && Objects.equals(accountMembers, that.accountMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transaction_name, amount, accountType, accountMembers);
    }

    @Override
    public String toString() {
        return "Member_transactions{" +
                "id=" + id +
                ", transaction_name='" + transaction_name + '\'' +
                ", amount=" + amount +
                ", accountType=" + accountType +
                ", member=" + accountMembers +
                '}';
    }
}
