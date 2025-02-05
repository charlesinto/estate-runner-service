package com.ireveal.EstateRunner.util;

import java.util.Random;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.util
 * @Date 05/02/2025
 */
public class RandomDataUtil {
    private static final Random RANDOM;
    static {
        RANDOM = new Random(System.nanoTime());
    }
    public static String generateRandomCode(int length) {
        return generateRandomCharacters(length, "0123456789");
    }

    public static String generateRandomCharacters(int num, String characterSpace) {
        StringBuilder generatedString = new StringBuilder();

        for(int i = 0; i < num; ++i) {
            char letter = characterSpace.charAt(RANDOM.nextInt(characterSpace.length()));
            generatedString.append(letter);
        }

        return generatedString.toString();
    }
}
