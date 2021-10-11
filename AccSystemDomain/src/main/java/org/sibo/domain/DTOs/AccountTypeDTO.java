package org.sibo.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.sibo.domain.persistence.AccountType;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountTypeDTO implements Serializable {
    private String name;
    private String nmonic;
    private LocalDate datecreated;

    public AccountTypeDTO() {
    }

    public AccountTypeDTO(String name, String nmonic, LocalDate datecreated) {
        this.name = name;
        this.nmonic = nmonic;
        this.datecreated = datecreated;
    }

    public AccountTypeDTO(AccountType accountType){
        this.name = accountType.getName();
        this.nmonic = accountType.getNmonic();
        this.datecreated = accountType.getDatecreated();
    }

    @JsonIgnore
    public AccountType buildAccountType(AccountTypeDTO accountTypeDTO){
        return new AccountType(null,this.getName(), this.getNmonic(), this.getDatecreated());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNmonic() {
        return nmonic;
    }

    public void setNmonic(String nmonic) {
        this.nmonic = nmonic;
    }

    public LocalDate getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    @Override
    public String toString() {
        return "AccountTypeDTO{" +
                "name='" + name + '\'' +
                ", nmonic='" + nmonic + '\'' +
                ", datecreated=" + datecreated +
                '}';
    }
}
