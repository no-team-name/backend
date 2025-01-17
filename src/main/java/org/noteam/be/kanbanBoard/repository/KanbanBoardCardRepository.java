package org.noteam.be.kanbanBoard.repository;

import org.noteam.be.kanbanBoard.domain.KanbanBoardCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanBoardCardRepository extends JpaRepository<KanbanBoardCard, Long> {
}
