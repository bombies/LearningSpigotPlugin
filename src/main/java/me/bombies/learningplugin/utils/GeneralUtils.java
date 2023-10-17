package me.bombies.learningplugin.utils;

import java.util.regex.Pattern;

public class GeneralUtils {

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Remove the trailing space, if any
        if (!result.isEmpty())
            result.setLength(result.length() - 1);
        return result.toString();
    }
}
