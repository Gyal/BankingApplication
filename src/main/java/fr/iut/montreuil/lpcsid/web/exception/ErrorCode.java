package fr.iut.montreuil.lpcsid.web.exception;

/**
 * Created by Mélina on 15/03/2015.
 */
public enum ErrorCode {
    NO_ENTITY_FOUND("No entity found"),
    WRONG_ENTITY_INFORMATION("Could not create/update entity with wrong informations"),
    UNAUTHORIZED("notAUTHORIZED to make this action");

    private String message;

    private ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
