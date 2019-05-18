package ru.hse.thesisTts.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class TextJson {
    @JsonProperty("text")
    @Getter
    @Setter
    private String text;
}
