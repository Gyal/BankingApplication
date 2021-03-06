package fr.iut.montreuil.lpcsid.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Mélina on 15/03/2015.
 */

//400
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataIntegrityException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundException.class);
    public DataIntegrityException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        LOGGER.info(errorCode.getErrMsg());

    }
}