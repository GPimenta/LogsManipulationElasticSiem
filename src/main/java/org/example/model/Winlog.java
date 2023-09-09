package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Winlog {

    @JsonProperty("event_data")
    private EventData eventData;

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    @Override
    public String toString() {
        return "Winlog{" +
                "eventData=" + eventData +
                '}';
    }
}
