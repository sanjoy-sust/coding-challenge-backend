package com.flask.app.errorhandler;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjoy Kumer Deb
 * @since  06/10/2017.
 */
public class ErrorCodes {
    public interface Feature{
        String UNKNOWN = "Unknown";
    }

    public interface CODE{
        String METHOD_ARG_NOT_VALID = "90211";
        String GENERIC_ERROR = "90200";
    }

    public static final Map<String, String> REASON_MAP = new HashMap<String, String>();

    static {
        REASON_MAP.put(CODE.METHOD_ARG_NOT_VALID,"arg.not.found");

    }
}