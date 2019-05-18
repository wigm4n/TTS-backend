package ru.hse.thesisTts.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class FakeRequestBodyJson {
    @Getter
    @Setter
    @JsonProperty("title")
    private String title;

    @Getter
    @Setter
    @JsonProperty("title")
    private String body;
}
