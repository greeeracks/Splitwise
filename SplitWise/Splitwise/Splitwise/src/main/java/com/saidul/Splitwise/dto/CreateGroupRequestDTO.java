package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateGroupRequestDTO {
    private String groupName;
    private Integer adminUID;
    private List<Integer> memberIds;
}
