package com.saidul.Splitwise.controller.dataFeeder;

import com.saidul.Splitwise.dto.AddFriendRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchFrndReqDTO {
    private List<AddFriendRequestDTO> addFriendRequestDTOS;
}
