package com.silva.bookstore.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validate(Pattern pattern, String input) {
        if(input == null || input.trim().isEmpty())
            return false;
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
