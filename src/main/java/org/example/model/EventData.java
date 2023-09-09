package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventData {

    @JsonProperty("LogonType")
    private String logonType;

    public String getLogonType() {
        return logonType;
    }

    public void setLogonType(String logonType) {
        this.logonType = logonType;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "logonType='" + logonType + '\'' +
                '}';
    }
}
