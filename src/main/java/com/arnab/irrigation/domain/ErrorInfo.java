package com.arnab.irrigation.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private int code;

    private String errorMessage;

    private String reason;

    public int getCode() {
            return code;
    }

    public ErrorInfo setCode(int code) {
            this.code = code;
            return this;
    }

    public String getErrorMessage() {
            return errorMessage;
    }

    public ErrorInfo setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
    }

    public String getReason() {
            return reason;
    }

    public void setReason(String reason) {
            this.reason = reason;
    }

    @Override
    public String toString() {
            return "ErrorInfo [code=" + code + ", errorMessage=" + errorMessage + ", reason=" + reason
                            + "]";
    }	
}
