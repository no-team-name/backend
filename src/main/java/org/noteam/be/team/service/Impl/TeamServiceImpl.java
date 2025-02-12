package org.noteam.be.team.service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noteam.be.kanbanBoard.dto.KanbanBoardCreateRequest;
import org.noteam.be.kanbanBoard.dto.KanbanBoardTeamResponse;
import org.noteam.be.kanbanBoard.service.KanbanBoardService;
import org.noteam.be.member.service.MemberService;
import org.noteam.be.system.exception.ExceptionMessage;
import org.noteam.be.system.exception.team.TeamNotFoundException;
import org.noteam.be.system.util.SecurityUtil;
import org.noteam.be.team.domain.Team;
import org.noteam.be.team.dto.TeamRegisterRequest;
import org.noteam.be.team.dto.TeamResponse;
import org.noteam.be.team.repository.TeamRepository;
import org.noteam.be.team.service.TeamService;
import org.noteam.be.teamMember.service.TeamMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberService teamMemberService;
    private final MemberService memberService;

    public List<TeamResponse> getTeamsByMemberId(Long memberId) {
        return teamRepository.findTeamsByMemberId(memberId).stream()
                .map(TeamResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // 메서드 기능: 등록Dto를 매개변수로 받아서, 엔티티로 변환후 Team DB에 저장한다
    // 반환: 엔티티를 fromEntity 메서드를통해 응답Dto로 바꾼후 반환한다
    @Transactional
    public TeamResponse createTeamByDto(TeamRegisterRequest dto) {
        Team team = TeamRegisterRequest.toEntity(dto);
        teamMemberService.save(memberService.getByMemberId(SecurityUtil.getCurrentMemberId()), team);
        Team savedTeam = teamRepository.save(team);
        return  TeamResponse.fromEntity(savedTeam);

    }

    // 메서드 기능: id로 해당 team 엔티티를 찾는다
    // 조건: delete false 인것만 찾는다
    // 예외: 존재하지않으면 예외를 던진다
    // 반환: 엔티티를 fromEntity 메서드를통해 응답Dto로 바꾼후 반환한다
    public TeamResponse getTeamByIdWithResponse(Long id) {

        // delete를 filter()를 활용해 false인것만 찾도록 필터링한다
        Team findTeam = teamRepository.findById(id)
                .filter(team -> !team.isDeleted())
                .orElseThrow( () -> new TeamNotFoundException( ExceptionMessage.Team.TEAM_NOT_FOUND_ERROR) );

        return TeamResponse.fromEntity(findTeam);
    }



    // 메서드 기능: id로 해당 team 엔티티를 찾는다
    // 조건: delete false 인것만 찾는다
    // 예외: 존재하지않으면 예외를 던진다
    // 반환: 해당 Team을 반환한다.
    public Team getTeamById(Long id) {

        // delete를 filter()를 활용해 false인것만 찾도록 필터링한다
        return teamRepository.findById(id)
                .filter(team -> !team.isDeleted()).orElseThrow( () -> new TeamNotFoundException( ExceptionMessage.Team.TEAM_NOT_FOUND_ERROR) );

    }



    // 메서드 기능: DB에 존재하는 delete 처리 안된 모든 Team 엔티티를 찾는 메서드다. 나중에 필요할까봐 만들어두었다.
    // 예외: 예외는 없다. 왜냐하면 빈리스트가 반환돼도 정상이기때문이다
    // 반환: 엔티티 자체를 반환한다
    public List<Team> getAllTeam() {

        return teamRepository.findAll().stream()
                .filter(team -> !team.isDeleted()).collect(Collectors.toList());

    }




    // 메서드 기능: 이곳의 내부 메서드를 이용하여 team 엔티티를 찾는다.
    // 그리고 setter를 이용하여 Dto에 저장된 팀명으로 바꾼뒤 다시 저장한다. 등록할때 입력받는 변수가 같아서 그대로 RegisterDto를 사용했다.
    // 예외: 내부메서드에서 예외처리된다
    // 반환: 엔티티를 fromEntity 메서드를통해 응답Dto로 바꾼후 반환한다
    @Transactional
    public TeamResponse updateTeamByDto(Long id, TeamRegisterRequest dto) {

        Team team = getTeamById(id);

        team.setTeamName(dto.getTeamName());

        Team savedTeam = teamRepository.save(team);

        return TeamResponse.fromEntity(savedTeam);

    }



    // 메서드 기능: id로 team 엔티티를 찾아서, setter로 소프트 딜리트 처리한다
    // 예외: 해당 엔티티가 존재하지않으면 예외를 던진다
    @Transactional
    public void deleteTeamById(Long id) {

        Team findTeam = teamRepository.findById(id)
                .orElseThrow( () -> new TeamNotFoundException( ExceptionMessage.Team.TEAM_NOT_FOUND_ERROR) );

        findTeam.delete(true);

        teamRepository.save(findTeam);

    }


    // 메서드 기능: id로 team 엔티티를 찾아서, 반환한다.
    // 예외: X
    // 반환: Team
    @Transactional
    public Optional<Team> findById(Long id) {

        return teamRepository.findById(id);

    }





    // 메서드 기능: db에서 데이터를 전부 다 지우는 메서드

    @Transactional
    public void hardDeleteAll() {
        teamRepository.deleteAll();
    }

    @Override
    public KanbanBoardTeamResponse getTeamForKanbanBoard(Long teamId) {

        Team team = getTeamById(teamId);

        String teamName = team.getTeamName();

        KanbanBoardTeamResponse result = KanbanBoardTeamResponse.builder()
                .teamName(teamName)
                .build();

        return result;
    }


}
