package org.noteam.be.chat.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.noteam.be.chat.dto.MessageChunkRequest;
import org.noteam.be.chat.dto.MessageRequest;
import org.noteam.be.chat.dto.MessageResponse;
import org.noteam.be.chat.service.MessageService;
import org.noteam.be.system.exception.ExceptionMessage;
import org.noteam.be.system.exception.chat.InvalidTeamIdException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChattingController {

    private final MessageService messageService;

    @MessageMapping("/room/{teamId}") // 메세지가 발행될 경로, 반드시 타야할 컨트롤러(/publish 프리픽스 달리면)될 때 이쪽으로 온다.
    @SendTo("/topic/room.{teamId}") // 브로드캐스트해줄 경로.(이거는 레빗엠큐의 경로)
    public MessageResponse sendToRoom(@DestinationVariable Long teamId, MessageRequest msgReq) {

        if(!msgReq.getTeamId().equals(teamId)) {
            throw new InvalidTeamIdException(ExceptionMessage.Chat.CHATROOM_DIFFERENT_EXCEPTION);
        }

        return messageService.sendMessage(msgReq); //@SendTo의 경로로 리턴함.
    }

    //이전의 대화기록을 불러온다. (더블클릭을 하거나/ 창이 켜졌을 경우)
    @ResponseBody
    @PostMapping("/api/chat/Messages")
    public Page<MessageResponse> chunkMessages(MessageChunkRequest msgChunk) {

        return messageService.requestChunkMessages(msgChunk);
    }

}
