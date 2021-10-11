package org.sibo.logic;

import org.sibo.domain.DTOs.AccountTypeDTO;

import java.util.List;

public interface AccountTypesService {
    List<AccountTypeDTO> getAllAccountTypes();
    AccountTypeDTO getAccountType(String nmonic);
    AccountTypeDTO deleteAccountType(String nmonic);
    AccountTypeDTO addAccountType(AccountTypeDTO accountTypeDTO);
}
