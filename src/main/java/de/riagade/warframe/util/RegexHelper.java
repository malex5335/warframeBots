package de.riagade.warframe.util;

import java.util.regex.Pattern;

public class RegexHelper {

    public static boolean containsExactSubstring(String newText, String key) {
        String regex = key.replace("/", ".");
        regex = "\\b" + (regex.startsWith(".") ? regex.substring(1) : regex) + "\\b$";
        Pattern pattern = Pattern.compile(regex);
        for(String line : newText.split("\n")){
            if(pattern.matcher(line).find()){
                return true;
            }
        }
        return false;
    }
}
