package org.sibo.webservice.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.persistence.AccountType;
import org.sibo.domain.service.GeneralResponse;
import org.sibo.logic.AccountTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Account-Types")
public class AccountTypeController {
    private AccountTypesService accountTypesService;

    @Autowired
    public AccountTypeController(AccountTypesService accountTypesService) {
        this.accountTypesService = accountTypesService;
    }

    @GetMapping("/all")
    @ApiOperation(value="Gets all the account types added to the db" ,notes="Returns a list of account types")
    @ApiResponses(value={
            @ApiResponse(code=200,message="account types returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<List<AccountTypeDTO>>> getAllAccountTypes(){
        List<AccountTypeDTO> accountTypeDTOS = accountTypesService.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDTO>> respons= new GeneralResponse<>(true,accountTypeDTOS);
        return new ResponseEntity<>(respons, HttpStatus.OK);
    }

    @PutMapping("/account-type/")
    @ApiOperation(value="Add a new account type" ,notes="Adds one acccount type and creates a new id for it")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Account type added"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<AccountTypeDTO>> addAccountType(
            @ApiParam(value = "Request Body for the new account type",
                    required = true)
            @RequestBody AccountTypeDTO accountTypeDTO){
        AccountTypeDTO member = accountTypesService.addAccountType(accountTypeDTO);
        GeneralResponse<AccountTypeDTO> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accounttype/{nmonic}")
    @ApiOperation(value="Gets a Member" ,notes="Returns an account type with a specified email")
    @ApiResponses(value={
            @ApiResponse(code=200,message="account type returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<AccountTypeDTO>> getAccountType(
            @ApiParam(value = "monic of the account type to search",
                    required = true)
            @PathVariable("nmonic") String nmonic){
        AccountTypeDTO accountType = accountTypesService.getAccountType(nmonic);
        GeneralResponse<AccountTypeDTO> response = new GeneralResponse<>(true,accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/account-type/{nmonic}")
    @ApiOperation(value="Delete aan account type" ,notes="Deletes one account type with the specified nmonic")
    @ApiResponses(value={
            @ApiResponse(code=200,message="account type deleted"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public void deleteAccountType(@ApiParam(value = "Nmonic for the account type to delete",
            required = true)@PathVariable("nmonic") String nmonic){
        accountTypesService.deleteAccountType(nmonic);
       /* accountTypesService.deleteMemberTransactions(email);
        accountMembersService.deleteMember(email);*/
    }
}
