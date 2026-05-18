package com.paymoni.simulator.config;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CommonUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private CommonUtil() {
        // Utility class
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String currentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    public static Long currentTimeInMillis() {
        return System.currentTimeMillis();
    }

    public static Long calculateTimeTaken(Long startTimeInMillis) {
        return System.currentTimeMillis() - startTimeInMillis;
    }
    
    public static String base64UrlEncode(String value) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String base64UrlDecode(String value) {
        byte[] decoded = Base64.getUrlDecoder().decode(value);
        return new String(decoded, StandardCharsets.UTF_8);
    }

	public static <T> String maskForLogging(T logData) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new Jdk8Module());
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
			return objectMapper.writeValueAsString(logData);
		} catch (JsonProcessingException e) {
			log.error("Error while maskForLogging {}", e);
		}
		return "";
	}
}