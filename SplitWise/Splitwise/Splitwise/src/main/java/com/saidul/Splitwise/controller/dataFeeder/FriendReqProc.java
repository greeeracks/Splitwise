package com.saidul.Splitwise.controller.dataFeeder;

import com.saidul.Splitwise.controller.UserController;
import com.saidul.Splitwise.dto.AddFriendRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendReqProc {
    @Autowired
    private UserController userController;
    @PostMapping("/addfriendlist")
    public ResponseEntity addFrndInBulk (@RequestBody BatchFrndReqDTO batchFrndReqDTO){
        for(AddFriendRequestDTO addFriendRequestDTO : batchFrndReqDTO.getAddFriendRequestDTOS()){
            userController.addFriend(addFriendRequestDTO);
        }
        return ResponseEntity.ok().build();
    }
}
