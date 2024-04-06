package com.saidul.Splitwise.dto;

import com.saidul.Splitwise.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLoginResponseDTO {
    private int id;
    private String name;
    private String email;
    private List<UserFriendResponseDTO> friendList;
    private List<GroupResponseDTO> groups;

    @Override
    public String toString() {
        return "User{" +
                "\n    id=" + id +
                "\n    name='" + name + '\'' +
                "\n    email='" + email + '\'' +
                "\n    friendList=" + (friendList != null && !friendList.isEmpty() ? friendList : "empty") +
                "\n    groups=" + (groups != null && !groups.isEmpty() ? groups : "empty") +
                "\n}";
    }
}
