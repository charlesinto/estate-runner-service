package com.ireveal.EstateRunner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.entity
 * @Date 03/02/2025
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstateRunnerResponse {
    protected boolean requestSuccessful;
    protected String responseMessage;
    protected String responseCode;
    private Object responseBody;
    @JsonIgnore
    private String[] args;
}
