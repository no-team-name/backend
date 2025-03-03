package org.noteam.be.kanbanBoard.service;

import org.noteam.be.kanbanBoard.domain.KanbanBoard;
import org.noteam.be.kanbanBoard.dto.*;

import java.util.List;

public interface KanbanBoardService{


    List<KanbanBoard> getAllTeamBoards(Long teamId);

    List<KanbanBoard> getKanbanBoardList(Long id);

    List<KanbanBoard> getBoardList(Long teamId);

    KanbanBoard getKanbanBoardbyTeamIdAndTitle(KanbanBoardLookupRequest request );

    KanbanBoard getKanbanBoardByTeamIdAndBoardId(KanbanBoardSecondLookupRequest request);

    KanbanBoard getKanbanBoardbyBoardId(Long boardId);

    KanbanBoardCreateResponse createBoard(KanbanBoardCreateRequest request);

    KanbanBoardMessageResponse deleteBoard(Long boardId);

    KanbanBoardMessageResponse updateBoard(KanbanBoardUpdateRequest request);

    KanbanBoardAndCardResponse changeBoardPriority(KanbanBoardSwitchRequest request);

    KanbanBoardAndCardResponse findByTeamId(Long teamId);

    KanbanBoard getKanbanBoardById(Long currentBoardId);
}
