package com.w4n4.dartViewer.repository;

import com.w4n4.dartViewer.model.GameInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInviteRepository extends JpaRepository<GameInvitation, Long>
{

}
