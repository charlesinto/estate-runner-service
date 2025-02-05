package com.ireveal.EstateRunner.util;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.util
 * @Date 05/02/2025
 */
public class CombinedAuthorityUtil {
    private static final String PREFIX = "hasAnyAuthority('";
    private static final String SEP = ",";
    private static final String SUFFIX = "')";
    private static final String SUPER_ADMIN = "ESR_SUPER_ADMIN";

    public static final String SA = PREFIX + SUPER_ADMIN + SUFFIX;
}
