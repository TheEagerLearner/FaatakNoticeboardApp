package com.example.faatak_androidapp_saurabhmulgaonkar.Pojo;

public class Single {

    private Integer noticeId;
    private String eventType;
    private String title;
    private String message;
    private Integer societyId;
    private Long createdDate;
    private Long expiaryDate;
    private String receiverType;
    private String senderType;
    private Integer senderId;

    public Single(Integer noticeId, String eventType, String title, String message, Integer societyId, Long createdDate, Long expiaryDate, String receiverType, String senderType, Integer senderId, Object documentPath, String senderName) {
        this.noticeId = noticeId;
        this.eventType = eventType;
        this.title = title;
        this.message = message;
        this.societyId = societyId;
        this.createdDate = createdDate;
        this.expiaryDate = expiaryDate;
        this.receiverType = receiverType;
        this.senderType = senderType;
        this.senderId = senderId;
        this.documentPath = documentPath;
        this.senderName = senderName;
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Integer getSocietyId() {
        return societyId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Long getExpiaryDate() {
        return expiaryDate;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public String getSenderType() {
        return senderType;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Object getDocumentPath() {
        return documentPath;
    }

    public String getSenderName() {
        return senderName;
    }

    private Object documentPath;
    private String senderName;

}
