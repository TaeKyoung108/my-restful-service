package kr.co.joneconsulting.myRestfulService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotCorrespondException extends RuntimeException{
    public UserNotCorrespondException(String message) {
        super(message);
    }
}
