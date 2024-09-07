package com.w4n4.dartViewer.repository;

import com.w4n4.dartViewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username != :username")
    List<User> findAllExceptQuestioner(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.id <> :userId AND u.id NOT IN " +
            "(SELECT f.friend.id FROM Friend f WHERE f.user.id = :userId) AND u.id NOT IN " +
            "(SELECT f.user.id FROM Friend f WHERE f.friend.id = :userId)")
    List<User> findAllNotInFriends(@Param("userId") Integer userId);

    /*
    @Query("select User.id, User.email, User.password, User.role, User.rating from User " +
            "join Friend on User.id = Friend.fiend.id or User.id = Friend.user.id " +
            "where Friend.friend.id = :userId or Friend.id = :userId and User.id != :userId and Friend.status = 'accepted'")
    List<User> getFriends(@Param("userId") Integer userId);
     */

    @Query("SELECT u FROM User u JOIN Friend f ON u.id = f.friend.id OR u.id = f.user.id " +
            "WHERE (f.friend.id = :userId OR f.user.id = :userId) AND u.id != :userId AND f.status = 'accepted'")
    List<User> getFriends(@Param("userId") Integer userId);

}
