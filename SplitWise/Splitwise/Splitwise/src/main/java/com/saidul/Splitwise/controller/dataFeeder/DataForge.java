package com.saidul.Splitwise.controller.dataFeeder;

import com.saidul.Splitwise.controller.ExpenseController;
import com.saidul.Splitwise.controller.GroupController;
import com.saidul.Splitwise.controller.UserController;
import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.dto.AddFriendRequestDTO;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataForge {
    /*This class can create all the data in single click from a json file
    data creation flow is
    1.user DTOs
    2.group DTOs
    3.expense DTOs
     */

    @Autowired
    private UserController userController;
    @Autowired
    private GroupController groupController;
    @Autowired
    private ExpenseController expenseController;

    @PostMapping("/forge")
    public ResponseEntity forgeData(@RequestBody DataFeederDTO dataFeederDTO){

        signUp(dataFeederDTO.getSignUpRequestDTOS());
        createGroup(dataFeederDTO.getGroupRequestDTOS());
        createExpense(dataFeederDTO.getExpenseRequestDTOS());
        //friendAdder(dataFeederDTO.getFriendRequestDTOS());
        return ResponseEntity.ok().build();
    }
    private void signUp(List<UserSignUpRequestDTO> signUpRequestDTOS){
        for(UserSignUpRequestDTO signUpRequestDTO : signUpRequestDTOS){
            userController.signUp(signUpRequestDTO);
        }
    }
    private void createGroup(List<CreateGroupRequestDTO> groupRequestDTOS){
        for(CreateGroupRequestDTO groupRequestDTO : groupRequestDTOS){
            groupController.createdGroup(groupRequestDTO);
        }
    }
    private void createExpense(List<AddExpenseRequestDTO> expenses) {
        for(AddExpenseRequestDTO expense : expenses){
            expenseController.addExpense(expense);
        }
    }
//    private void friendAdder(List<AddFriendRequestDTO> addFriendRequestDTOS){
//        for(AddFriendRequestDTO addFriendRequestDTO : addFriendRequestDTOS){
//            userController.addFriend(addFriendRequestDTO);
//        }
//    }
}
