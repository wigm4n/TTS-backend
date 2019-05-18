package ru.hse.thesisTts.process.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hse.thesisTts.json.*;

import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import ru.hse.thesisTts.process.CallProcessService;
import ru.hse.thesisTts.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class CallProcessServiceImpl implements CallProcessService {
    private Utils utils;
    private boolean useDestinations;

    public CallProcessServiceImpl() {
        this.utils = new Utils();
        this.useDestinations = false;
    }

    @Override
    public CheckJson check() throws Exception {
        log.info("In check");

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader;
        URL url;

        if (useDestinations) {
            DestinationConfiguration config = utils.getConfiguration();
            url = new URL(config.getProperty("URL") + "/check");
        } else {
            url = new URL("https://thetistts.cfapps.eu10.hana.ondemand.com/check");
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() >= 400) {
            throw new RuntimeException("HTTP POST Request failed with error code: " + conn.getResponseCode());
        }

        log.info("Trying to get output");
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String output;
        while ((output = reader.readLine()) != null)
            stringBuilder.append(output);

        return utils.jsonStringToObject(stringBuilder.toString(), CheckJson.class);
    }

    @Override
    public ResponseJson getWords(String inputText) throws Exception {
        log.info("In getWords");

        byte[] postDataBytes = utils.prepareData(inputText);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader;
        URL url;

        if (useDestinations) {
            DestinationConfiguration config = utils.getConfiguration();
            url = new URL(config.getProperty("URL") + "/get_words");
        } else {
            url = new URL("https://thetistts.cfapps.eu10.hana.ondemand.com/get_words");
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.getOutputStream().write(postDataBytes);
        log.info("Data sent");

        if (conn.getResponseCode() >= 400) {
            throw new RuntimeException("HTTP POST Request failed with error code: " + conn.getResponseCode());
        }

        log.info("Trying to get output");
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String output;
        while ((output = reader.readLine()) != null)
            stringBuilder.append(output);

        return utils.jsonStringToObject(stringBuilder.toString(), ResponseJson.class);
    }

    @Override
    public ResponseEntity<byte[]> getAudio(String inputText) throws Exception {
        log.info("In getAudio");

        byte[] postDataBytes = utils.prepareData(inputText);
        URL url;

        if (useDestinations) {
            DestinationConfiguration config = utils.getConfiguration();
            url = new URL(config.getProperty("URL") + "/get_audio");
        } else {
            url = new URL("https://thetistts.cfapps.eu10.hana.ondemand.com/get_audio");
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.getOutputStream().write(postDataBytes);
        log.info("Data sent");

        if (conn.getResponseCode() >= 400) {
            throw new RuntimeException("HTTP POST Request failed with error code: " + conn.getResponseCode());
        }
        log.info("Trying to get output");

        InputStream is = conn.getInputStream();
        HttpHeaders headers = new HttpHeaders();
        byte[] media = IOUtils.toByteArray(is);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
}
