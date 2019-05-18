package ru.hse.thesisTts.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ByteJson {
    @JsonProperty("text")
    @Getter
    @Setter
    private byte[] text;
}
