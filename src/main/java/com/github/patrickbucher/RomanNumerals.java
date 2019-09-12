package ch.paedubucher.romanNumerals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanNumerals {

    private static final Map<String, Integer> romanValues = new LinkedHashMap<>();

    static {
        romanValues.put("I", 1);
        romanValues.put("V", 5);
        romanValues.put("X", 10);
        romanValues.put("L", 50);
        romanValues.put("C", 100);
        romanValues.put("D", 500);
        romanValues.put("M", 1000);
    }

    public static int toNumber(final String roman) {
        if (roman == null || roman.trim().equals("")) {
            return 0;
        }
        checkOnlyRomanCharacters(roman.toUpperCase());
        final List<String> tokens = tokenize(roman.toUpperCase());
        int result = 0;
        for (final String token : tokens) {
            result += evaluate(token);
        }
        return result;
    }

    private static void checkOnlyRomanCharacters(final String upperRoman) {
        upperRoman.chars().mapToObj(i -> (char) i).forEach(c -> {
            final String str = String.valueOf(c);
            if (!romanValues.containsKey(str)) {
                throw new IllegalArgumentException("'" + str + "' is no roman numeral");
            }
        });
    }

    private static List<String> tokenize(final String upperRoman) {
        final List<String> tokens = new ArrayList<>();
        final char[] chars = new StringBuilder(upperRoman).reverse().toString().toCharArray();
        StringBuilder token = new StringBuilder();
        String last = String.valueOf(chars[0]);
        token.append(last);
        for (int i = 1; i < chars.length; i++) {
            final String current = String.valueOf(chars[i]);
            // MCMLXXXIV -> M|CM|L|XXX|IV
            if (romanValues.get(current) > romanValues.get(last)) {
                tokens.add(token.reverse().toString());
                token = new StringBuilder(current);
            } else {
                token.append(current);
            }
            last = current;
        }
        tokens.add(token.reverse().toString());
        return tokens;
    }

    private static Integer evaluate(final String token) {
        final List<String> items =
            token.chars().mapToObj(i -> String.valueOf((char) i)).collect(Collectors.toList());
        String last = items.get(0);
        Integer result = romanValues.get(last);
        for (int i = 1; i < items.size(); i++) {
            final String current = items.get(i);
            final Integer currentValue = romanValues.get(current);
            final Integer lastValue = romanValues.get(last);
            if (currentValue > lastValue) {
                result = -result + currentValue;
            } else {
                result += currentValue;
            }
            last = current;
        }
        return result;
    }

    public static String toRoman(final int number) {
        // FIXME not implemented yet
        throw new NoSuchMethodError("not implemented yet");
    }
}
