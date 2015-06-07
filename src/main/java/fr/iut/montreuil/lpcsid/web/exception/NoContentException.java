package fr.iut.montreuil.lpcsid.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Mélina on 10/05/2015.
 */

//204 no content
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundException.class);
    public NoContentException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        LOGGER.info(errorCode.getErrMsg());

    }
}
