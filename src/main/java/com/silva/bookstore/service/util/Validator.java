package com.silva.bookstore.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    
    private static final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$"
    );

    private static final Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");

    private static final Pattern contactNoPattern = Pattern.compile("^\\d{10}$");


    private static boolean validate(Pattern pattern, String input) {
        if(input == null || input.trim().isEmpty())
            return false;
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        return validate(emailPattern, email);
    }

    public static boolean validateName(String name) {
        return validate(namePattern, name);
    }

    public static boolean validateContactNo(String contactNo) {
        return validate(contactNoPattern, contactNo);
    }

}
