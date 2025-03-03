package org.noteam.be.system.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.noteam.be.system.response.item.Message;
import org.noteam.be.system.response.item.Status;

import javax.net.ssl.SSLEngineResult;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    GET_USER_PROFILE_SUCCESS(Status.OK, Message.READ_USER),

    // team
    POST_TEAM_SUCCESS(Status.OK, Message.SAVE_TEAM),
    GET_TEAM_SUCCESS(Status.OK, Message.READ_TEAM),
    UPDATE_TEAM_SUCCESS(Status.OK, Message.UPDATE_TEAM),
    DELETE_TEAM_SUCCESS(Status.OK, Message.DELETE_TEAM),


    // joinBoard
    POST_JOIN_BOARD_SUCCESS(Status.OK, Message.SAVE_JOIN_BOARD),
    GET_JOIN_BOARD_SUCCESS(Status.OK, Message.READ_JOIN_BOARD),
    UPDATE_JOIN_BOARD_SUCCESS(Status.OK, Message.UPDATE_JOIN_BOARD),
    DELETE_JOIN_BOARD_SUCCESS(Status.OK, Message.DELETE_JOIN_BOARD),
    UPDATE_JOIN_BOARD_FAIL(Status.FORBIDDEN, Message.UPDATE_JOIN_BOARD_FAIL),

    // serach
    SEARCH_JOIN_BOARD_SUCCESS(Status.OK, Message.READ_SEARCH_JOIN_BOARD),


    // image
    POST_CANVAS_IMAGE_SUCCESS(Status.OK, Message.SAVE_CANVAS_IMAGE),
    DELETE_CANVAS_IMAGE_SUCCESS(Status.OK, Message.DELETE_CANVAS_IMAGE),
    POST_NOTE_IMAGE_SUCCESS(Status.OK, Message.SAVE_NOTE_IMAGE),
    DELETE_NOTE_IMAGE_SUCCESS(Status.OK, Message.DELETE_NOTE_IMAGE),

    // comment
    POST_COMMENT_SUCCESS(Status.OK, Message.SAVE_COMMENT),
    GET_COMMENT_SUCCESS(Status.OK, Message.READ_COMMENT),
    UPDATE_COMMENT_SUCCESS(Status.OK, Message.UPDATE_COMMENT),
    DELETE_COMMENT_SUCCESS(Status.OK, Message.DELETE_COMMENT),
    UPDATE_COMMENT_FAIL(Status.FORBIDDEN, Message.UPDATE_COMMENT_FAIL),

    ;



    private int httpStatus;
    private String message;

}
