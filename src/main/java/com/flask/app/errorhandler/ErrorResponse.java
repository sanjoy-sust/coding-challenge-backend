package com.flask.app.errorhandler;

import lombok.Data;

/**
 * The {@code ErrorResponse} class represents response for any Exception.
 * <p>
 * Feature identifies which feature Exception Occured.
 Code uniquely defined exception
 * <p>
 * @author Sanjoy Kumer Deb
 */
@Data
public class ErrorResponse {
    String feature;
    String code;
    String message;
}
