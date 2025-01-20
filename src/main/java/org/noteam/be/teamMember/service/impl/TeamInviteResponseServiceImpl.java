package org.noteam.be.teamMember.service.impl;

import lombok.RequiredArgsConstructor;
import org.noteam.be.member.domain.Member;
import org.noteam.be.member.repository.MemberRepository;
import org.noteam.be.system.exception.FindNotTeamException;
import org.noteam.be.team.domain.Team;
import org.noteam.be.teamMember.service.TeamInviteResponseService;
import org.noteam.be.teamMember.repository.TeamMemberRepository;

import org.noteam.be.teamMember.domain.TeamMember;

import org.noteam.be.teamMember.dto.InviteMemberResponse;

import org.noteam.be.system.exception.ExceptionMessage;
import org.noteam.be.team.service.TeamService;
import org.noteam.be.teamMember.service.TeamMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TeamInviteResponseServiceImpl implements TeamInviteResponseService {

    private final TeamService teamService;
    private final MemberRepository memberRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberService teamMemberService;


    @Override
    public InviteMemberResponse AcceptTeamInvite(Long teamId, Long memberId) {

        Team team = teamService.getTeamById(teamId);

        List<TeamMember> teamMembers = team.getTeamMembers();

        Member member = memberRepository.findById(memberId).get();

        // 기존 멤버아이디가 조회 안되는지 확인하고 조회가 알될경우 save실행
        List<TeamMember> findMember = teamMemberRepository.findByMember(member);

        if (findMember.isEmpty()) {

            if (teamMembers.size() <= 3 ) {
                teamMemberService.save(member,team);
                return InviteMemberResponse.builder()
                        .message("Success Add Team Member")
                        .result(true)
                        .build();
            }

            return InviteMemberResponse.builder()
                    .message("Faild Add Team Member")
                    .result(false)
                    .build();

        }
        return InviteMemberResponse.builder()
                    .message("Already Invited Team Member")
                    .result(false)
                    .build();

    }

}
