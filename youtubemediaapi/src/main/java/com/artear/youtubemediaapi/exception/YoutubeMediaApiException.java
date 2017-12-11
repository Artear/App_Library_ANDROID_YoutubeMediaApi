package com.artear.youtubemediaapi.exception;

import com.artear.youtubemediaapi.YoutubeErrorType;

public class YoutubeMediaApiException extends Exception {
    private YoutubeErrorType errorType;
    private int code = 0;
    private String reason = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public YoutubeMediaApiException(YoutubeErrorType errorType) {
        super(errorType.name());
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return errorType.name() + "_" + super.getMessage();
    }
}
