package org.noteam.be.joinBoard.service;

import org.noteam.be.joinBoard.domain.JoinBoard;
import org.noteam.be.joinBoard.dto.JoinBoardCardResponse;
import org.noteam.be.joinBoard.dto.JoinBoardRegisterRequest;
import org.noteam.be.joinBoard.dto.JoinBoardResponse;
import org.noteam.be.joinBoard.dto.JoinBoardUpdateRequest;
import org.noteam.be.member.domain.Member;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JoinBoardService {


    JoinBoardResponse createJoinBoardByDto(JoinBoardRegisterRequest dto, Member member);

    JoinBoard getJoinBoardEntityById(Long id);

    JoinBoard getJoinBoardEntityByIdWithNoDeleted(Long id);

    JoinBoardResponse getJoinBoardById(Long id);

    List<JoinBoardResponse> getAllJoinBoard();

    List<JoinBoardCardResponse> getAllJoinBoardCard();

    JoinBoardResponse updateJoinBoardById(Long id, JoinBoardUpdateRequest dto);

    void deleteJoinBoardById(Long id);

    Page<JoinBoardCardResponse> getAllJoinBoardCardByPage(int page);

    Member findMemberById(Long currentMemberId);

    Page<JoinBoardCardResponse> getAllJoinBoardCardByTitle(int page);

}