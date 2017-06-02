package com.springapp.wood.util;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Integer> stringDelimToListInt(String inputS) {
        if (inputS == null || inputS.isEmpty()) 
            return null;

        List<Integer> result = new ArrayList<>();
        for (String s : inputS.split(",")) 
            try {
                result.add(Integer.parseInt(s));
            } catch (NumberFormatException ignore) {}

        return result;
    }

    public static List<String> stringDelimToListString(String input) {
        if (input == null || input.isEmpty()) 
            return null;

        List<String> result = new ArrayList<>();
        for (String s : input.split(",")) 
            result.add(s);

        return result;
    }
}
