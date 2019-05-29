package ru.hse.thesisTts.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.thesisTts.process.CallProcessService;
import ru.hse.thesisTts.model.RequestBodyJson;
import ru.hse.thesisTts.model.ResponseStatusJson;

@RestController
@Slf4j
public class ControllerAPI {

    private static final String STATUS_ERROR = "ERROR";

    private final CallProcessService callProcessService;

    @Autowired
    public ControllerAPI(CallProcessService callProcessService) {
        this.callProcessService = callProcessService;
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> check() {
        try {
            //throw new Exception();
            return new ResponseEntity<>(callProcessService.check(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseStatusJson(STATUS_ERROR, "service is unavailable"),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/get_words", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> getWords(@RequestBody RequestBodyJson requestBodyJson) {
        try {
            return new ResponseEntity<>(callProcessService.getWords(requestBodyJson.getInput()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseStatusJson(STATUS_ERROR, "something goes wrong"),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/get_audio", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> getAudio(@RequestBody RequestBodyJson requestBodyJson) {
        return callProcessService.getAudio(requestBodyJson.getInput());
    }
}