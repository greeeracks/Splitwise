package com.saidul.Splitwise.controller.dataFeeder;

import com.saidul.Splitwise.dto.AddExpenseRequestDTO;
import com.saidul.Splitwise.dto.AddFriendRequestDTO;
import com.saidul.Splitwise.dto.CreateGroupRequestDTO;
import com.saidul.Splitwise.dto.UserSignUpRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataFeederDTO {
    //user
    private List<UserSignUpRequestDTO> signUpRequestDTOS;
    //group
    private List<CreateGroupRequestDTO> groupRequestDTOS;
    //expense
    private List<AddExpenseRequestDTO> expenseRequestDTOS;
    //addFriend
    //private List<AddFriendRequestDTO> friendRequestDTOS;
}
