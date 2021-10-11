package org.sibo.translator;

import org.sibo.domain.DTOs.AccountTypeDTO;

import java.util.List;

public interface AccountTypeTranslator {
    List<AccountTypeDTO> getAllAccountTypes();
    AccountTypeDTO getAccountType(String nmonic);
    AccountTypeDTO deleteAccountType(String nmonic);
    AccountTypeDTO addAccountType(AccountTypeDTO accountTypeDTO);
}
