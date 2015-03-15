package fr.iut.montreuil.lpcsid.web.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Mélina on 15/03/2015.
 */
@ResponseStatus(BAD_REQUEST)
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}