package vn.hust.hedspi.ezsport.application.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
public enum ErrorCode {
    UNAUTHENTICATED(1001,"Unauthenticated"),
    USER_NOT_FOUND(1002,"User not found !"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(9998,"Invalid key"),
    FIELD_NAME_INVALID(1003,"Field name invalid"),
    EMAIL_INVALID(1004,"Email invalid"),
    PASSWORD_INVALID(1005,"Password at least 6 character"),
    AUTHORIZATION_DENIED(9997,"Authorization denied"),
    FIELD_NOT_FOUND(1006,"Field not found!")
    ;

    ErrorCode(int code,String message){
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
}
