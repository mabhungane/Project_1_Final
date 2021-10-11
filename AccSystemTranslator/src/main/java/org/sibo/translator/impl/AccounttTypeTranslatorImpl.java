package org.sibo.translator.impl;

import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.domain.persistence.AccountType;
import org.sibo.repository.persistence.AccountTypeRepository;
import org.sibo.translator.AccountTypeTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class AccounttTypeTranslatorImpl implements AccountTypeTranslator {
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccounttTypeTranslatorImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDTO> getAllAccountTypes() {
        List<AccountTypeDTO> accountTypeDTOS = new ArrayList<>();
        try {
            for (AccountType e: accountTypeRepository.findAll()){
                accountTypeDTOS.add(new AccountTypeDTO(e));
            }
        }catch (Exception e){
            throw new RuntimeException("Error cannot get all account types,e");
        }
        return accountTypeDTOS;
    }

    @Override
    public AccountTypeDTO getAccountType(String nmonic) {
        return new AccountTypeDTO(accountTypeRepository.getByNmonic(nmonic));
    }

    @Override
    public AccountTypeDTO deleteAccountType(String nmonic) {
        return new AccountTypeDTO(accountTypeRepository.deleteByNmonic(nmonic));
    }

    @Override
    public AccountTypeDTO addAccountType(AccountTypeDTO accountTypeDTO) {
        AccountType accountType;
        try {
            accountType = accountTypeDTO.buildAccountType(accountTypeDTO);
            accountTypeRepository.save(accountType);
        }catch (Exception e){
            throw new RuntimeException("Error cannot save the account type,e");
        }
        return new AccountTypeDTO(accountType);
    }
}
