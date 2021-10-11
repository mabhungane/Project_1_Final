package org.sibo.webservice.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sibo.domain.DTOs.MemberDTO;
import org.sibo.domain.DTOs.MemberGoalsDTO;
import org.sibo.domain.service.GeneralResponse;
import org.sibo.logic.MemberGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Members/Goals")
public class MemberGoalsController {
    private MemberGoalsService memberGoalsService;

    @Autowired
    public MemberGoalsController(MemberGoalsService memberGoalsService) {
        this.memberGoalsService = memberGoalsService;
    }

    @GetMapping("/all")
    @ApiOperation(value="Gets all the goals added to the db" ,notes="Returns a list of members")
    @ApiResponses(value={
            @ApiResponse(code=200,message="goals returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<List<MemberGoalsDTO>>> getAllGoals(){
        List<MemberGoalsDTO> accountTypeDTOS = memberGoalsService.getAllMemberGoals();
        GeneralResponse<List<MemberGoalsDTO>> respons= new GeneralResponse<>(true,accountTypeDTOS);
        return new ResponseEntity<>(respons, HttpStatus.OK);
    }

    @GetMapping("/member/{email}")
    @ApiOperation(value="Gets a Member goals" ,notes="Returns one member with the specified email")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member goals returned"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<MemberGoalsDTO>> getMemberGoals(
            @ApiParam(value = "Email for the user to search the goals",
                    required = true)
            @PathVariable("email") String email){
        MemberGoalsDTO member = memberGoalsService.getMemberGoals(email);
        GeneralResponse<MemberGoalsDTO> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/member/{email}")
    @ApiOperation(value="Update the goals for a member" ,notes="updates a member")
    @ApiResponses(value={
            @ApiResponse(code=200,message="Member goals updated"),
            @ApiResponse(code=400,message="Bad request"),
            @ApiResponse(code=404,message="Not found"),
            @ApiResponse(code=500,message="Internal Server error")
    })
    public ResponseEntity<GeneralResponse<MemberGoalsDTO>> updateGoalsMember(
            @ApiParam(value = "Request Body for the new member",
                    required = true)
            @PathVariable("email") String email,
            @RequestBody MemberGoalsDTO memberDTO){
        MemberGoalsDTO member = memberGoalsService.updateGoals(email,memberDTO);
        GeneralResponse<MemberGoalsDTO> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
