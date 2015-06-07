package fr.iut.montreuil.lpcsid.web.exception;

/**
 * Created by Mélina on 15/03/2015.
 */
public enum ErrorCode {
    NO_ENTITY_FOUND(" No entity Found : No entity found"),
    WRONG_ENTITY_INFORMATION("Wrong entity information : Could not create/update entity with wrong informations"),
    UNAUTHORIZED(" Unauthorized : Not authorized to make this action"),
    NO_CONTENT(" No content : Sorry, No content"),
    CONFLICT(" Conflict : The request could not be completed due to a conflict with the current state of the resource"),
    BAD_REQUEST("Bad request :Your browser sent a request that this server could not understand. Size of a request header field exceeds server limit."),
    FORBIDDEN("Forbidden: You don't have permission to access to is date on this server.");

    private String errMsg;

    public String getErrMsg() {

        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private ErrorCode(String message) {
        this.errMsg = message;
    }
}
