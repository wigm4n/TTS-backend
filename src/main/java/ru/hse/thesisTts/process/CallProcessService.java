package ru.hse.thesisTts.process;

import org.springframework.http.ResponseEntity;
import ru.hse.thesisTts.json.CheckJson;
import ru.hse.thesisTts.json.ResponseJson;

public interface CallProcessService {
    CheckJson check() throws Exception;

    ResponseJson getWords(String inputText) throws Exception;

    ResponseEntity<byte[]> getAudio(String inputText) throws Exception;
}
