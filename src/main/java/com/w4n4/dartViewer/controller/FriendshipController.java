package com.w4n4.dartViewer.controller;

import com.w4n4.dartViewer.model.Friend;
import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.model.UserDto;
import com.w4n4.dartViewer.service.FriendService;
import com.w4n4.dartViewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.w4n4.dartViewer.config.FrontEndSettings.FRONT_URL;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController
{
    private FriendService friendService;
    private UserService userService;

    @Autowired
    public FriendshipController(FriendService friendService, UserService userService)
    {
        this.friendService = friendService;
        this.userService = userService;
    }


    @PostMapping("/add")
    public Friend addFriend(@RequestBody int id)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Friend friend = new Friend();
        User userFriend = userService.findUserById(id);
        User user = userService.findUserByUsername(authentication.getName());
        if(userFriend != null && user!=null)
        {
            if(!friendService.checkFriendship(userFriend.getId(), user.getId())) {

                friend.setUser(user);
                friend.setFriend(userFriend);
                return friendService.saveFriend(friend);
            }
            else return null;
        }

       else return null;
    }

    @PostMapping("/accept/{id}")
    public Friend acceptFriendRequest(@PathVariable Long id)
    {
        return friendService.acceptFriendRequest(id);
    }

    @PostMapping("/reject/{id}")
    public Friend rejectFriendRequest(@PathVariable Long id)
    {
        return friendService.rejectFriendRequest(id);
    }



    @GetMapping("/getAllPotentialFriends")
    public List<UserDto> getAllPotentialFriends()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
        {
            User user = userService.findUserByUsername(authentication.getName());
            return userService.getAllPotentialFriends(user.getId());
        }
        else
        {
            return null;
        }
    }


    @GetMapping("/getAll")
    public List<UserDto> getAllFriends()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
        {
            User user = userService.findUserByUsername(authentication.getName());
            return friendService.getAllFriends(user.getId());
        }
        else
        {
            return null;
        }

    }
    
}
