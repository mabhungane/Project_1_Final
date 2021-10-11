package org.sibo.webservice.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.persistence.Account_Members;
import org.sibo.domain.service.GeneralResponse;
import org.sibo.logic.AccountMembersService;
import org.sibo.logic.MemberGoalsService;
import org.sibo.logic.MemberTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Members")
public class MembersController {
    private AccountMembersService accountMembersService;
    private MemberGoalsService memberGoalsService;
    private MemberTransactionsService memberTransactionsService;

    @Autowired
    public MembersController(AccountMembersService accountMembersService, MemberGoalsService memberGoalsService, MemberTransactionsService memberTransactionsService) {
        this.accountMembersService = accountMembersService;
        this.memberGoalsService = memberGoalsService;
        this.memberTransactionsService = memberTransactionsService;
    }




    @GetMapping("/all")
    @ApiOperation(value="Gets all the Members added to the db" ,notes="Returns a list of members")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Members returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<List<MemberDTO>>> getAllMembers(){
        List<MemberDTO> accountTypeDTOS = accountMembersService.getAllMembers();
        GeneralResponse<List<MemberDTO>> respons= new GeneralResponse<>(true,accountTypeDTOS);
        return new ResponseEntity<>(respons, HttpStatus.OK);
    }

    @GetMapping("/member/{email}")
    @ApiOperation(value="Gets a Member" ,notes="Returns one member with the specified email")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<MemberDTO>> getMember(
            @ApiParam(value = "Email for the user to search",
            required = true)
            @PathVariable("email") String email){
        MemberDTO member = accountMembersService.getMember(email);
        GeneralResponse<MemberDTO> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/member/{email}")
    @ApiOperation(value="Delete a Member" ,notes="Deletes one member with the specified id")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member deleted"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public void deleteMember(@ApiParam(value = "Email for the user to delete",
            required = true)@PathVariable("email") String email){
        memberGoalsService.deleteMemberGoals(email);
        memberTransactionsService.deleteMemberTransactions(email);
        accountMembersService.deleteMember(email);
    }

    @PutMapping("/member/")
    @ApiOperation(value="Add a new Member" ,notes="Adds one member and creates a new id")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member added"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<MemberDTO>> addMember(
            @ApiParam(value = "Request Body for the new member",
                    required = true)
            @RequestBody MemberDTO memberDTO){
        MemberDTO member = accountMembersService.addMember(memberDTO);

        GeneralResponse<MemberDTO> response = new GeneralResponse<>(true,member);
        memberGoalsService.addGoals(memberDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
