package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.FORBIDDEN, reason = "Insufficient authority to accept friend request"
)
public class InsufficientAuthorityToAcceptFriendRequestException extends RuntimeException{
}
