package com.elysiaptr.handler;

import com.elysiaptr.config.GlobalConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ConfigLoader {

    private static final String CONFIG_FILE_PATH = "/etc/qchatbot/config.json";

    public static GlobalConfig loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            log.info("Loading config from {} successfully", CONFIG_FILE_PATH);
            return objectMapper.readValue(new File(CONFIG_FILE_PATH), GlobalConfig.class);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
