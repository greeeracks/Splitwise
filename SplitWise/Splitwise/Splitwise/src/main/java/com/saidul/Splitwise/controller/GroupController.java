package com.saidul.Splitwise.controller;
import com.saidul.Splitwise.Exception.InvalidGroupIdException;
import com.saidul.Splitwise.Exception.InvalidGroupRequestData;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.mapper.EntityDTOMapper;
import com.saidul.Splitwise.service.GroupService;
import com.saidul.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;
    @PostMapping("/creategroup")
    public ResponseEntity createdGroup(@RequestBody CreateGroupRequestDTO groupRequestDTO){
        try{
            validateGroupRequestDTO(groupRequestDTO);
        }catch (InvalidGroupRequestData invalidGroupRequestData){
            return ResponseEntity.badRequest().body("Provided data is insufficient to create group");
        }
        Group savedGroup = groupService.createGroup(groupRequestDTO.getGroupName(),
                groupRequestDTO.getAdminUID(), groupRequestDTO.getMemberIds());
        return ResponseEntity.ok(
                EntityDTOMapper.toGroupDTO(savedGroup)
        );
    }
    @GetMapping("/group/ID={id}")
    public ResponseEntity getGroup(@PathVariable ("id") int groupId){
        validateGroupReqId(groupId);
        try {
            Group savedGroup = groupService.getGroupById(groupId);
            return ResponseEntity.ok(
                   EntityDTOMapper.toGroupDTO(savedGroup)
            );
        }catch (InvalidGroupIdException groupIdException){
            return ResponseEntity.badRequest().body("Group not found");
        }
    }


    @PostMapping("/split/ID={groupId}")
    public ResponseEntity splitGroupExpenses(@PathVariable ("groupId") int groupId){
        validateGroupReqId(groupId);
        groupService.splitExpense(groupId);
        return ResponseEntity.ok(
                "done"
        );
    }

    @GetMapping("/settle/groupID={groupId}")
    public ResponseEntity settleUp(@PathVariable ("groupId") int groupId){
        validateGroupReqId(groupId);
        List<SettlementTransaction> settlementTransactions = groupService.settleUp(groupId);
        return ResponseEntity.ok(
                EntityDTOMapper.toSettlementTransactionResponseDTOList(settlementTransactions)
        );
    }


    private void validateGroupRequestDTO(CreateGroupRequestDTO groupRequestDTO){
        if(groupRequestDTO.getGroupName().isEmpty() || groupRequestDTO.getAdminUID()==null || groupRequestDTO.getMemberIds().isEmpty()){
            throw new InvalidGroupRequestData("Provided data is insufficient to create group");
        }
    }
    private void validateGroupReqId(int id){
        if(id < 0){
            throw new InvalidGroupRequestData("Invalid Group data.");
        }
    }
}
