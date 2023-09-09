package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    @JsonProperty("type")
    private String type;
    @JsonProperty("original")
    private String original;
    @JsonProperty("outcome")
    private String outcome;
    @JsonProperty("category")
    private String category;
    @JsonProperty("action")
    private String action;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", original='" + original + '\'' +
                ", outcome='" + outcome + '\'' +
                ", category='" + category + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
