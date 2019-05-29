package ru.hse.thesisTts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatusJson {
    @Getter
    @Setter
    @JsonProperty("status")
    private String status;

    @Getter
    @Setter
    @JsonProperty("description")
    private String description;
}
