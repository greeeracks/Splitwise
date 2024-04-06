package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.*;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User signUp(String name, String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser != null){
            throw new RegistrationException("Email already in use");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }
    @Override
    public User login(String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new InvalidEmailException("Email not found");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.encode(password);
        if(encoder.matches(password, savedUser.getPassword())){
            return savedUser;
        }else {
            throw new InvalidPasswordException("Incorrect password");
        }
    }

    @Override
    public boolean addFriend(int id, String email) {
        User user = userRepository.findUserById(id);
        User friend = userRepository.findUserByEmail(email);
        if(user == null){
            throw new InvalidUserIdException("Id for user adding friend does not exist");
        } else if (friend == null) {
            throw new InvalidEmailException("Email for friend does not exist");
        }
        if (user.getFriends() != null && user.getFriends().contains(friend)) {
            throw new InvalidFriendRequestException("Friend already exists in user's friend list");
        }
        if (user.getFriends() != null){
            user.getFriends().add(friend);
            userRepository.save(user);
        }
        if(friend.getFriends() != null){
            friend.getFriends().add(user);
            userRepository.save(friend);
        }
        return true;
    }
    public User getUserById(int id){
        return userRepository.findUserById(id);
    }

}
