package org.sibo.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Account_Type")
public class AccountType implements Serializable {
    private Long id;
    private String name;
    private String nmonic;
    private LocalDate datecreated;
    private Set<Member_transactions> member_transactionsset;

    public AccountType() {
    }


    public AccountType(Long id, String name, String nmonic, LocalDate datecreated) {
        this.id = id;
        this.name = name;
        this.nmonic = nmonic;
        this.datecreated = datecreated;
    }

    public AccountType(Long id, String name, String nmonic, LocalDate datecreated, Set<Member_transactions> member_transactionsset) {
        this.id = id;
        this.name = name;
        this.nmonic = nmonic;
        this.datecreated = datecreated;
        this.member_transactionsset = member_transactionsset;
    }

    @Id
    @SequenceGenerator(name="MY_SEQ_AccountType",sequenceName = "ACCOUNT_TYPE_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ_AccountType")
    @Column(name = "accounttype_id")
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

    @Column(name = "nmonic",unique = true)
    public String getNmonic() {
        return nmonic;
    }

    public void setNmonic(String nmonic) {
        this.nmonic = nmonic;
    }

    @Column(name = "date_created")
    public LocalDate getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    @OneToMany(targetEntity = Member_transactions.class, fetch = FetchType.LAZY, mappedBy = "accountType",orphanRemoval = true,cascade = CascadeType.PERSIST)
    public Set<Member_transactions> getMember_transactionsset() {
        return member_transactionsset;
    }

    public void setMember_transactionsset(Set<Member_transactions> member_transactionsset) {
        this.member_transactionsset = member_transactionsset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(nmonic, that.nmonic) && Objects.equals(datecreated, that.datecreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nmonic, datecreated);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nmonic='" + nmonic + '\'' +
                ", datecreated=" + datecreated +
                '}';
    }
}
