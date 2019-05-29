package ru.hse.thesisTts.process;

import org.springframework.http.ResponseEntity;
import ru.hse.thesisTts.model.CheckJson;
import ru.hse.thesisTts.model.ResponseJson;

public interface CallProcessService {
    CheckJson check() throws Exception;

    ResponseJson getWords(String inputText) throws Exception;

    ResponseEntity<?> getAudio(String inputText);
}
