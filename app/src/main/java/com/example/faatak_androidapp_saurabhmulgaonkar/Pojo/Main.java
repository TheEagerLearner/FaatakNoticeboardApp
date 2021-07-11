package com.example.faatak_androidapp_saurabhmulgaonkar.Pojo;

import java.util.List;

public class Main {
    private Boolean status;
    private Integer code;
    private String message;
    private Object detailedMessage;
    private List<Notice> data = null;

    public Main(Boolean status, Integer code, String message, Object detailedMessage, List<Notice> data) {
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

    public List<Notice> getData() {
        return data;
    }
}
