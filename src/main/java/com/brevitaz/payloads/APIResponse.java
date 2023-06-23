package com.brevitaz.payloads;


public class APIResponse {
    private String message;
    private boolean success;

    public APIResponse(final String message, final boolean success) {
        this.message = message;
        this.success = success;
    }

    public APIResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
