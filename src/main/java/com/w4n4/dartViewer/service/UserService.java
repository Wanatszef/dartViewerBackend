package com.w4n4.dartViewer.service;

import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.model.UserDto;
import com.w4n4.dartViewer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public User findUserByUsername(String username)
    {
        if(userRepository.findByUsername(username).isPresent())
        {
            return userRepository.findByUsername(username).get();
        }
        else
        {
            return null;
        }
    }

    public List<UserDto> getAllUsers()
    {

        List<User> usersList = userRepository.findAll();
        return usersList.stream().map(user -> new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRating())).collect(Collectors.toList());
    }

    public List<UserDto> getAllUsersExceptQuestioner(String username)
    {
        List<User> usersList = userRepository.findAllExceptQuestioner(username);
        return usersList.stream().map(user -> new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRating())).collect(Collectors.toList());
    }

    public User findUserById(int id)
    {
        if(userRepository.findById(id).isPresent())
        {
            return userRepository.findById(id).get();
        }
        else
        {
            return null;
        }
    }

    public List<UserDto> getAllPotentialFriends(int id)
    {
        List<User> usersList = userRepository.findAllNotInFriends(id);
        if(!usersList.isEmpty())
        {
            return usersList.stream().map(user -> new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRating())).collect(Collectors.toList());
        }
        else return null;
    }

}
