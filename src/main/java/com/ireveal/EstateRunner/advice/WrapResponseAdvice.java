package com.ireveal.EstateRunner.advice;

import com.ireveal.EstateRunner.annotations.IgnoreWrapResponse;
import com.ireveal.EstateRunner.annotations.WrapResponse;
import com.ireveal.EstateRunner.apimodel.response.EstateRunnerResponse;
import com.ireveal.EstateRunner.enums.ResponseCode;
import com.ireveal.EstateRunner.util.ResponseUtilService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.advice
 * @Date 03/02/2025
 */

@ControllerAdvice(annotations = WrapResponse.class)
public class WrapResponseAdvice extends GlobalControllerAdvice implements ResponseBodyAdvice<Object> {

    public WrapResponseAdvice(ResponseUtilService responseUtilService) {
        super(responseUtilService);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return (returnType.getMethod().getDeclaredAnnotation(IgnoreWrapResponse.class) == null);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return responseUtilService.buildStringResponse((String) body, ResponseCode.SUCCESS);
        }
        return EstateRunnerResponse
                .builder()
                .requestSuccessful(true)
                .responseBody(body)
                .responseMessage(ResponseCode.SUCCESS.getDescription())
                .responseCode(ResponseCode.SUCCESS.getCode())
                .build();
    }
}
