package org.sibo.webservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sibo.domain.DTOs.AccountTypeDTO;
import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.MemberGoalsDTO;
import org.sibo.domain.DTOs.Member_transactionsDTO;
import org.sibo.domain.persistence.Account_Members;
import org.sibo.domain.service.GeneralResponse;
import org.sibo.logic.AccountMembersService;
import org.sibo.logic.AccountTypesService;
import org.sibo.logic.MemberTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Members/Transactions")
public class MemberTransactionsController {
    private MemberTransactionsService memberTransactionsService;
    private AccountMembersService accountMembersService;
    private AccountTypesService accountTypesService;

    @Autowired

    public MemberTransactionsController(MemberTransactionsService memberTransactionsService, AccountMembersService accountMembersService, AccountTypesService accountTypesService) {
        this.memberTransactionsService = memberTransactionsService;
        this.accountMembersService = accountMembersService;
        this.accountTypesService = accountTypesService;
    }


    @GetMapping("/member/{email}")
    @ApiOperation(value="Gets a Member transactions" ,notes="Returns a list of the transactions made by the member with the specified email")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member transactions returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<List<Member_transactionsDTO>>> getMemberTransactions(
            @ApiParam(value = "Email for the user to search the transactions",
                    required = true)
            @PathVariable("email") String email){
        List<Member_transactionsDTO> memberTransactions = memberTransactionsService.getMemberTransactions(email);
        GeneralResponse<List<Member_transactionsDTO>> response = new GeneralResponse<>(true,memberTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/add/")
    @ApiOperation(value="Add a new transaction" ,notes="Adds one transaction for the member")
    @ApiResponses(value={
            @ApiResponse(code=200,message="transaction added"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<Member_transactionsDTO>> addTransaction(
            @ApiParam(value = "Email for the member",
                    required = true)
            @RequestParam String email,
            @ApiParam(value="name of transaction", required = true) @RequestParam String name,
            @ApiParam(value="Amount", required = true) @RequestParam int amount,
            @ApiParam(value="Account Type",example = "miles",required = true)@RequestParam String actype,
            @ApiParam(value = "Type of transaction", example = "add or subtract", required = true)@RequestParam String type){
        MemberDTO memberDTO = accountMembersService.getMember(email);
        AccountTypeDTO accountTypeDTO = accountTypesService.getAccountType(actype);

        Member_transactionsDTO member = new Member_transactionsDTO(name,amount,accountTypeDTO,memberDTO);
        Member_transactionsDTO addNewTransaction = memberTransactionsService.addNewTransaction(member);
        accountMembersService.updatePoints(addNewTransaction.getMember().getEmail(), addNewTransaction);
        GeneralResponse<Member_transactionsDTO> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
