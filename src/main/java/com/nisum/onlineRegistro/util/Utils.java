package com.nisum.onlineRegistro.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static Pattern VALID_EMAIL_ADDRESS_REGEX;
    private static Pattern VALID_PASSWORD_REGEX;

    public static boolean validateEmail(String email) {
        VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        //at least 1 digit, 1 uppercase, 1 lowercase, no space
        VALID_PASSWORD_REGEX = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    public static String generateCsrfToken() {
        String csrfToken="";
        try {
            byte[] random = new byte[64];
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(random);
            csrfToken = Base64.getEncoder().encodeToString(random).replace('+', '_');

        } catch (Exception e) {
            e.printStackTrace();
        }
        return csrfToken;
    }


}
