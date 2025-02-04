package org.noteam.be.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.noteam.be.admin.dto.MemberSearchResponse;
import org.noteam.be.admin.dto.MemberStatusUpdateRequest;
import org.noteam.be.admin.service.AdminMemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/members")
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    // 회원조회 api
    @GetMapping("/search")
    public ResponseEntity<Page<MemberSearchResponse>> searchMembers(
            @RequestParam String keyword,
            @PageableDefault(size = 10) Pageable pageable
        ) {
        log.info("회원검색완료");
        return ResponseEntity.ok(adminMemberService.searchMembers(keyword, pageable));
    }

    // 회원 상태변경 api
    @PutMapping("/{memberId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long memberId,
            @RequestBody MemberStatusUpdateRequest request
        ) {
        adminMemberService.updateMemberStatus(memberId, request.getStatus());
        return ResponseEntity.ok().build();
    }


}