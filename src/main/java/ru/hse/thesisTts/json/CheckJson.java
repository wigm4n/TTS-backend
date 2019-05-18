package ru.hse.thesisTts.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CheckJson {
    @Getter
    @Setter
    @JsonProperty("status")
    private String status;
}
