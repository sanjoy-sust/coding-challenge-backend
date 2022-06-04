package com.flask.app.errorhandler;


import lombok.Getter;

/**
 * @author Sanjoy Kumer Deb
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String code;
    private String feature;
    private String reason;

    public ResourceNotFoundException(String feature, String code, String reason) {
        super(reason);
        this.reason=reason;
        this.feature = feature;
        this.code = code;
    }
}