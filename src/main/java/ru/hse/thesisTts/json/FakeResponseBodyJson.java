package ru.hse.thesisTts.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class FakeResponseBodyJson {
    @Getter
    @Setter
    @JsonProperty("title")
    private String title;

    @Getter
    @Setter
    @JsonProperty("body")
    private String body;

    @Getter
    @Setter
    @JsonProperty("id")
    private Integer id;
}
