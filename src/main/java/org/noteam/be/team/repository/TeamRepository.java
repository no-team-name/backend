package org.noteam.be.team.repository;

import org.noteam.be.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Modifying
    @Query(value = "ALTER TABLE team AUTO_INCREMENT = 1", nativeQuery = true)
    public void idResetForBeforeEach();

}
