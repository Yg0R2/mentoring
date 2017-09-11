package com.epam.mentoring.chat.message;

import com.google.gson.Gson;

public class ChatMessage {

    private String content;
    private String dateTimeValue;
    private MessageType type;
    private String sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTimeValue() {
        return dateTimeValue;
    }

    public void setDateTimeValue(String dateTimeValue) {
        this.dateTimeValue = dateTimeValue;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(dateTimeValue);
        sb.append(" # ");
        sb.append(sender);
        sb.append(" # ");
        sb.append(content);
        sb.append(" # ");
        sb.append(type.toString());

        return sb.toString();
    }
}
