package com.saidul.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "splitwise_user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<User> friends;
    @ManyToMany
    //members will be added by only the admin aka the group creator, subsequently group members profile will show the
    //list of groups they are part of
    private List<Group> groups;
}
