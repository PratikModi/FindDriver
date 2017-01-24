package com.gojeck.response;

/**
 * Created by Pratik on 23-01-2017.
 */
public class ApiResponse {
    private int statusCode;
    private String message;

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
