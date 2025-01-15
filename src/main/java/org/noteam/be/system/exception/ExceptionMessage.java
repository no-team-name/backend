package org.noteam.be.system.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {


    public static final String EMAIL_SENDING_ERROR = "이메일 전송 도중 오류가 생겼습니다";
    public static final String EMPTY_TEAM = "팀이 존재하지 않습니다.";

    public static final String TEAM_NOT_FOUND_ERROR = "해당하는 팀을 찾을 수 없습니다";


    public static class MemberAuth {

        public static final String KAKAO_PROFILE_NOT_PROVIDED = "카카오 계정의 프로필 정보를 가져올 수 없습니다.";
        public static final String UNSUPPORTED_PROVIDER_EXCEPTION = "지원하지 않는 소셜 서비스 입니다.";

    }


}
