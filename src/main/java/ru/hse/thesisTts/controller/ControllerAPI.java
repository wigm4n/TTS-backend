package ru.hse.thesisTts.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.thesisTts.process.CallProcessService;
import ru.hse.thesisTts.json.RequestBodyJson;
import ru.hse.thesisTts.json.ResponseStatusJson;

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
            return new ResponseEntity<>(callProcessService.check(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseStatusJson(STATUS_ERROR), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/get_words", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> getWords(@RequestBody RequestBodyJson requestBodyJson) {
        try {
            return new ResponseEntity<>(callProcessService.getWords(requestBodyJson.getInput()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseStatusJson(STATUS_ERROR), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/get_audio", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<byte[]> getAudio(@RequestBody RequestBodyJson requestBodyJson) {
        try {
            return callProcessService.getAudio(requestBodyJson.getInput());
        } catch (Exception ex) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}