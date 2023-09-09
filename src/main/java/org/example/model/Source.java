package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
    @JsonProperty("ip")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Source{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
