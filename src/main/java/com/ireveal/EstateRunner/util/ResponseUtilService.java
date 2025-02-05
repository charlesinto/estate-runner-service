package com.ireveal.EstateRunner.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ireveal.EstateRunner.apimodel.response.EstateRunnerResponse;
import com.ireveal.EstateRunner.enums.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.util
 * @Date 03/02/2025
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class ResponseUtilService {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    private EstateRunnerResponse buildErrorResponse(String code, String message, HttpServletRequest httpServletRequest) {
        return resolveMessageKeys(EstateRunnerResponse.builder()
                .requestSuccessful(false)
                .responseCode(code)
                .responseMessage(stripPackageNames(message))
                .build(), httpServletRequest);
    }

    public EstateRunnerResponse buildErrorResponse(String message, HttpServletRequest httpServletRequest) {
        return buildErrorResponse("400", message, httpServletRequest);
    }


    public EstateRunnerResponse buildErrorResponse(String message, Throwable e, HttpServletRequest httpServletRequest) {
        log.error(e.getMessage(), e);
        return buildErrorResponse(message, httpServletRequest);
    }

    public EstateRunnerResponse buildErrorResponse(String code, String message, Throwable e, HttpServletRequest httpServletRequest) {
        log.error(e.getMessage(), e);
        if (code == null) {
            return buildErrorResponse(message, httpServletRequest);
        }
        return buildErrorResponse(code, message, httpServletRequest);
    }

    private EstateRunnerResponse resolveMessageKeys(EstateRunnerResponse baseResponse, HttpServletRequest request) {

        if (request == null) {
            return baseResponse;
        }

        final Locale locale = localeResolver.resolveLocale(request);
        baseResponse.setResponseMessage(resolveKey(baseResponse.getResponseMessage(), baseResponse.getArgs(), locale));
        return baseResponse;
    }

    public String resolveKey(String key, Object[] objects, Locale locale) {
        try {
            return messageSource.getMessage(key, objects, locale);
        } catch (Exception ignored) {
            return key;
        }

    }

    public String buildStringResponse(String responseBody, ResponseCode responseCode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(
                    EstateRunnerResponse.builder()
                            .responseCode(responseCode.getCode())
                            .responseMessage(responseCode.getDescription())
                            .requestSuccessful(true)
                            .responseBody(responseBody)
                            .build()
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception appropriately
            // Return an error message or handle the error case here
            return "Error occurred while processing the response";
        }
    }

    private String stripPackageNames(String rawInput) {
        if (StringUtils.isBlank(rawInput)) {
            return "";
        } else if (StringUtils.remove(rawInput, " ").equals(rawInput)) {
            return rawInput;
        } else {
            String pattern = "(\\w+\\.\\w+\\.\\w+)";
            Pattern packageNamePattern = Pattern.compile(pattern);
            Matcher matcher = packageNamePattern.matcher(rawInput);
            return matcher.replaceAll("");
        }
    }
}
