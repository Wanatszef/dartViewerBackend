package com.w4n4.dartViewer.repository;


import com.w4n4.dartViewer.model.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameMatch, Long>
{
}
