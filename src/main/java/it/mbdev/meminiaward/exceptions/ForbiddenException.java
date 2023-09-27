package it.mbdev.meminiaward.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User not authenticated or Authorized")
public class ForbiddenException extends RuntimeException {
}
