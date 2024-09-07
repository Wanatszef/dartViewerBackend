package com.w4n4.dartViewer.service;

import com.w4n4.dartViewer.model.Friend;
import com.w4n4.dartViewer.model.User;
import com.w4n4.dartViewer.model.UserDto;
import com.w4n4.dartViewer.repository.FriendRepository;
import com.w4n4.dartViewer.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendService
{
    private FriendRepository friendRepository;
    private UserRepository userRepository;

    public FriendService(FriendRepository friendRepository, UserRepository userRepository)
    {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public Friend saveFriend(Friend friend)
    {
        return friendRepository.save(friend);
    }

    public Set<Friend> findFriendsByUser(User user)
    {
        return friendRepository.findByUser(user);
    }

    public Set<Friend> findFriendsByFriend(User friend)
    {
        return friendRepository.findByFriend(friend);
    }

    public Friend acceptFriendRequest(Long id)
    {
        Friend friend = friendRepository.findById(id).orElseThrow(()->new RuntimeException("Friend request not found"));
        friend.setStatus("accepted");
        return friendRepository.save(friend);
    }

    public Friend rejectFriendRequest(Long id)
    {
        Friend friend = friendRepository.findById(id).orElseThrow(()->new RuntimeException("Friend request not found"));
        friend.setStatus("rejected");
        return friendRepository.save(friend);
    }

    public List<Friend> getFriendshipRequests()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<User> tempUser = userRepository.findByUsername(currentUsername);
        User currentUser = null;
        if(tempUser.isPresent())
        {
            currentUser = tempUser.get();
        }
        else
        {
            return null;
        }
        return friendRepository.findFriendshipRequestsById(currentUser.getId());
        //todo sprawdzić czy to wogle działa w pizde jeza
    }

    public Boolean checkFriendship(int id, int friendId)
    {

        return friendRepository.findFriendship(id, friendId).isPresent();

    }

    public List<UserDto> getAllFriends(int userId)
    {
        List<User> friends = userRepository.getFriends(userId);
        return friends.stream().map(user -> new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRating())).collect(Collectors.toList());
    }

}
