package ru.hse.thesisTts.utils;

import com.google.gson.Gson;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import lombok.extern.slf4j.Slf4j;
import ru.hse.thesisTts.json.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.nio.charset.StandardCharsets;


@Slf4j
public class Utils {
    public byte[] prepareData(String text) {
        RequestBodyJson requestBodyJson = new RequestBodyJson(text);
        return new Gson().toJson(requestBodyJson).getBytes(StandardCharsets.UTF_8);
    }

    public <T> T jsonStringToObject(String byteArray, Class<T> type) {
        return new Gson().fromJson(byteArray, type);
    }

    public DestinationConfiguration getConfiguration() {
        Context ctx;
        try {
            ctx = new InitialContext();
            final ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx
                    .lookup("java:comp/env/connectivityConfiguration");
            return configuration.getConfiguration("python_cf");
        } catch (NamingException e) {
            log.error("NamingException. Can't get ConnectivityConfiguration for CallPython. Error message: {}",
                    e.getMessage());
            return null;
        }
    }
}
