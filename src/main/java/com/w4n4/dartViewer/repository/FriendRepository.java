package com.w4n4.dartViewer.repository;

import com.w4n4.dartViewer.model.Friend;
import com.w4n4.dartViewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FriendRepository extends JpaRepository<Friend, Long>
{
    Set<Friend> findByUser(User user);
    Set<Friend> findByFriend(User friend);

    @Query("SELECT f FROM Friend f WHERE f.user.id = :userID AND f.status = 'pending'")
    List<Friend> findFriendshipRequestsById(@Param("userID") Integer userID);


    @Query("SELECT f FROM Friend f " +
            "WHERE (f.user.id = :userId AND f.friend.id = :friendId) " +
            "OR (f.user.id = :friendId AND f.friend.id = :userId)")
    Optional<Friend> findFriendship(@Param("userId") int userId, @Param("friendId") int friendId);
}
