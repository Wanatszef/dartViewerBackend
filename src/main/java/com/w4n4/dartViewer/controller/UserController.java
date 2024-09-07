package com.w4n4.dartViewer.controller;
import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.model.UserDto;
import com.w4n4.dartViewer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.w4n4.dartViewer.config.FrontEndSettings.FRONT_URL;

@RestController
@RequestMapping("api/user")
public class UserController
{
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    
    @GetMapping("/get/{id}")
    public UserDto getUser(@PathVariable int id)
    {
        User user;
        if(userRepository.findById(id).isPresent())
        {
          user = userRepository.findById(id).get();
          return new UserDto(user);
        }
        else return null;
    }
}