package org.sibo.logic.flows;

import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.logic.AccountTypesService;
import org.sibo.translator.AccountTypeTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountTypesServiceImpl implements AccountTypesService {
    private AccountTypeTranslator accountTypeTranslator;

    @Autowired
    public AccountTypesServiceImpl(AccountTypeTranslator accountTypeTranslator) {
        this.accountTypeTranslator = accountTypeTranslator;
    }

    @Override
    public List<AccountTypeDTO> getAllAccountTypes() {
        return accountTypeTranslator.getAllAccountTypes();
    }

    @Override
    public AccountTypeDTO getAccountType(String nmonic) {
        return accountTypeTranslator.getAccountType(nmonic);
    }

    @Override
    public AccountTypeDTO deleteAccountType(String nmonic) {
        return accountTypeTranslator.deleteAccountType(nmonic);
    }

    @Override
    public AccountTypeDTO addAccountType(AccountTypeDTO accountTypeDTO) {
        return accountTypeTranslator.addAccountType(accountTypeDTO);
    }
}
