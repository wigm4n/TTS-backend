package ru.hse.thesisTts.process;

import org.springframework.http.ResponseEntity;
import ru.hse.thesisTts.model.CheckJson;

public interface CallProcessService {
    CheckJson check() throws Exception;

    ResponseEntity<?> getAudio(String inputText);
}
