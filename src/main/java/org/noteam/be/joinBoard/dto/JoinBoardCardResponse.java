package org.noteam.be.joinBoard.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.noteam.be.joinBoard.domain.JoinBoard;
import org.noteam.be.member.domain.Member;
import org.noteam.be.system.util.TimeAgoUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinBoardCardResponse {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String topic;

    @NotNull
    private String teamName;

    @NotNull
    private String projectBio;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private int peopleNumber;

    @NotNull
    private String createdAt;

    @NotNull
    private String updatedAt;


    @NotNull
    private Long memberId;

    @NotNull
    private String memberNickname;

    @NotNull
    private String memberProfileUrl;


    public static JoinBoardCardResponse getResponseFromEntity(JoinBoard joinBoard){
       return JoinBoardCardResponse.builder()
                .id(joinBoard.getId())
                .memberId(joinBoard.getMember().getMemberId())
                .memberNickname(joinBoard.getMember().getNickname())
                .memberProfileUrl( joinBoard.getMember().getProfileImg() == null ? null : joinBoard.getMember().getProfileImg().getImageUrl() )
                .title(joinBoard.getTitle())
                .topic(joinBoard.getTopic())
                .teamName(joinBoard.getTeamName())
                .projectBio(joinBoard.getProjectBio())
                .startDate(joinBoard.getStartDate())
                .endDate(joinBoard.getEndDate())
                .peopleNumber(joinBoard.getPeopleNumber())
                .createdAt(TimeAgoUtil.formatElapsedTime(joinBoard.getCreatedAt()))
                .updatedAt(TimeAgoUtil.formatElapsedTime(joinBoard.getUpdatedAt()))
                .build();
    }


}
