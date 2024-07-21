package com.w4n4.dartViewer.repository;

import com.w4n4.dartViewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
}
