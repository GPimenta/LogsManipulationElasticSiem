package org.example.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogEntry {
    @JsonProperty("winlog")
    private Winlog winlog;
    @JsonProperty("event")
    private Event event;
    @JsonProperty("@timestamp")
    private String timestamp;
    @JsonProperty("user")
    private User user;
    @JsonProperty("host")
    private Host host;
    @JsonProperty("source")
    private Source source;

    public Winlog getWinlog() {
        return winlog;
    }

    public void setWinlog(Winlog winlog) {
        this.winlog = winlog;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "{" +
                "winlog=" + winlog +
                ", event=" + event +
                ", @timestamp='" + timestamp + '\'' +
                ", user=" + user +
                ", host=" + host +
                ", source=" + source +
                '}';
    }
}
