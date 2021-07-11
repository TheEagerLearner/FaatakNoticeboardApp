package com.example.faatak_androidapp_saurabhmulgaonkar.Pojo;

public class SingleNot {

    private Boolean status;

    private Integer code;

    private String message;

    private Object detailedMessage;
    private Single data;
    public SingleNot(Boolean status, Integer code, String message, Object detailedMessage, Single data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetailedMessage() {
        return detailedMessage;
    }

    public Single getData() {
        return data;
    }
}
