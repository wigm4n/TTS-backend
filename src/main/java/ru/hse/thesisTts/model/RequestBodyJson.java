package ru.hse.thesisTts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class RequestBodyJson {
    @Getter
    @Setter
    @JsonProperty("input")
    private String input;
}
