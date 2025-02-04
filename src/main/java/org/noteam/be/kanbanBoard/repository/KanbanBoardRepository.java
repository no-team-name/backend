package org.noteam.be.kanbanBoard.repository;

import org.noteam.be.kanbanBoard.domain.KanbanBoard;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Long> {

    List<KanbanBoard> findByTeamId(Long teamId , Sort sort);

    KanbanBoard findByTeamIdAndTitle(Long teamId, String title);

    KanbanBoard findByTeamIdAndId(Long teamId, long id);

    KanbanBoard findById(long id);



}
