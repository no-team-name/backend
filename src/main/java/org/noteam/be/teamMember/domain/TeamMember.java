package org.noteam.be.teamMember.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.noteam.be.member.domain.Member;
import org.noteam.be.team.domain.Team;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember {

    @Id
    @Column(name = "team_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //멤버 id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //팀 id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    //삭제 여부
    private Boolean deleted = false;

    //작성일
    private LocalDateTime createdAt = LocalDateTime.now();

    //수정일
    private LocalDateTime updatedAt;

    @Builder
    public TeamMember(Member member, Team team) {
        this.member = member;
        this.team = team;
    }


}
