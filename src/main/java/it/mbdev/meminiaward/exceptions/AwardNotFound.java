package it.mbdev.meminiaward.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Award not found")
public class AwardNotFound extends RuntimeException {
}
