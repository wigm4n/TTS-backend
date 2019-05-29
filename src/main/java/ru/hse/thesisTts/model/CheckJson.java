package ru.hse.thesisTts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CheckJson {
    @Getter
    @Setter
    @JsonProperty("status")
    private String status;
}
