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
        public static final String MEMBER_NOT_FOUND_EXCEPTION = "회원을 찾을 수 없습니다.";
        public static final String MEMBER_NOT_FOUND = "대상 멤버가 없습니다.";
        public static final String NICKNAME_ALREADY_EXIST = "변경하려는 닉네임이 중복됩니다.";
        public static final String INVALID_REFRESH_TOKEN_PROVIDED = "변경하려는 닉네임이 중복됩니다.";
        public static final String EMPTY_REFRESH_TOKEN = "변경하려는 닉네임이 중복됩니다.";
        public static final String EXISTING_AUTHENTICATION_IS_NULL = "인가정보가 존재하지 않습니다.";

    }

    public static class ProfileImg {
        public static final String IMAGE_FILE_TOO_LARGE_EXCEPTION = "파일의 크기가 허용치보다 큽니다.";
        public static final String IMAGE_DIMENSION_EXCEEDED_EXCEPTION = "파일의 폭 또는 높이가 초과되었습니다.";
    }

    public static class Kanbanboard {
        public static final String KANBANBOARD_NOT_FOUND_ERROR = "해당하는 보드를 찾을 수 없습니다";
        public static final String KANBANBOARD_CARD_NOT_FOUND_ERROR = "해당하는 카드를 찾을 수 없습니다";
    }





}
