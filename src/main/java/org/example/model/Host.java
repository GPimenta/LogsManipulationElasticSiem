package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Host {
    @JsonProperty("name")
    private String name;
}
