package org.noteam.be.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.noteam.be.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissueAccessToken(
            @RequestHeader("Refresh-Token") String refreshTokenValue
    ) {
        try {
            String newAccessToken = authService.reissueAccessToken(refreshTokenValue);
            return ResponseEntity.ok(newAccessToken);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            Authentication authentication,
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("Refresh-Token") String refreshTokenValue
    ) {
        try {
            log.debug("Authentication in controller: {}", authentication);
            // Access Token 추출
            String accessToken = bearerToken.substring(7);

            authService.logout(accessToken, refreshTokenValue, authentication);
            return ResponseEntity.ok("로그아웃 완료");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

//    아래의 코드가 원본. service 레이어로 AuthService 로 구성하여 가져다가 사용하는 형태로 변경함.
//    // Access Token 만료 -> Refresh Token 으로 Access Token 재발급
//    @PostMapping("/reissue")
//    public ResponseEntity<?> reissueAccessToken(
//            @RequestHeader("Refresh-Token") String refreshTokenValue
//    ) {
//        // Refresh Token 검증 및 토큰 존재 여부 확인
//        if (!jwtTokenProvider.validate(refreshTokenValue)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("올바르지 않은 리프레쉬 토큰");
//        }
//
//        // DB에서 토큰 조회 및 새 Access Token 발급
//        return tokenRepository.findValidRefTokenByToken(refreshTokenValue)
//                .map(token -> {
//                    TokenBody tokenBody = jwtTokenProvider.parseJwt(refreshTokenValue);
//                    String newAccessToken = jwtTokenProvider.issueAccessToken(
//                            tokenBody.getMemberId(),
//                            tokenBody.getRole()
//                    );
//                    return ResponseEntity.ok(newAccessToken);
//                })
//                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("리프레쉬토큰이 없거나 만료됨."));
//    }
//
//    // 로그아웃 -> Access/Refresh Token 블랙리스트 등록
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(
//            Authentication authentication,
//            @RequestHeader("Authorization") String bearerToken,
//            @RequestHeader("Refresh-Token") String refreshTokenValue
//    ) {
//        return Optional.ofNullable(authentication)
//                .map(auth -> {
//                    String accessToken = bearerToken.substring(7);
//
//                    // AccessToken과 RefreshToken을 블랙리스트에 등록
//                    tokenRepository.appendBlackList(
//                            RefreshToken.builder()
//                                    .refreshToken(accessToken)
//                                    .build()
//                    );
//
//                    tokenRepository.appendBlackList(
//                            RefreshToken.builder()
//                                    .refreshToken(refreshTokenValue)
//                                    .build()
//                    );
//
//                    return ResponseEntity.ok("로그아웃 완료.");
//                })
//                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("권한 없음!"));
//    }
