package com.restaurant.foodonline.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@Component
public class RandomUtil {
    private final Random RANDOM = new SecureRandom();
    private final String NUMERIC = "0123456789";
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    public String generateUniqueId(){
        Long unixTime = System.currentTimeMillis();
        String hexStr = generateAlphabetic(7).toUpperCase();
        String unixTimeStr = unixTime.toString();
        String firstHalfUnixTimeStr = unixTimeStr.substring(0, unixTimeStr.length()/2);
        String secondHalfUnixTimeStr = unixTimeStr.substring(unixTimeStr.length()/2);
        String randomStr = firstHalfUnixTimeStr + hexStr + secondHalfUnixTimeStr;
        return randomStr;
    }

    public String generateUUID(){
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public String generateNumeric(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(NUMERIC.charAt(RANDOM.nextInt(NUMERIC.length())));
        }

        return new String(returnValue);
    }

    public String generateAlphabetic(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String generateAlphanumeric(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET_NUMERIC.charAt(RANDOM.nextInt(ALPHABET_NUMERIC.length())));
        }

        return new String(returnValue);
    }

}
