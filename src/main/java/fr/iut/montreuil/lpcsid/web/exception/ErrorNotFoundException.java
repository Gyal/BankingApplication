package fr.iut.montreuil.lpcsid.web.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Mélina on 15/03/2015.
 */
@ResponseStatus(NOT_FOUND)
public class ErrorNotFoundException extends RuntimeException {

    public ErrorNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}