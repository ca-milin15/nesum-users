package com.nesum.nesum.shared.infrastructure;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StaticConstants {

    public static final String ONLY_TEXT_REGEX = "^[a-zA-Z\\s]*$";
    public static final String ONLY_NUMBER_REGEX = "^[0-9]+$";
    public static final String SPECIAL_CHARACTER_REGEX_HUMAN_NAME = "[0-9$&+,:;=\\?@#|/'<>.^*()%!-]";
    public static final String EMAIL_FORMAT_REGEX = "^[a-z0-9][a-z0-9.-_]+@[a-z]+\\.[a-z]{2,3}+(\\.[a-z]{2,3})?";
    public static final String PASSWORD_REGEX = "^[a-zA-Z]\\w{3,14}$"; // First char must be a letter, then 3-14 chars. Only letters
}
