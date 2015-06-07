package fr.iut.montreuil.lpcsid.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mélina on 15/03/2015.
 */

//404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundException.class);
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        LOGGER.info(errorCode.getErrMsg());
    }
}