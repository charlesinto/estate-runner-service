package com.ireveal.EstateRunner.enums;

import lombok.Getter;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.enums
 * @Date 04/02/2025
 */
public enum ResponseCode {
    SUCCESS("00");


    @Getter
    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.name().replace('_', '.').toLowerCase();
    }
}
