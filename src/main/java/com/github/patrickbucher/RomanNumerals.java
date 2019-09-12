package ch.paedubucher.romanNumerals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanNumerals {

    private static final Map<String, Integer> romanToDecimal = new LinkedHashMap<>();
    private static final Map<Integer, String> decimalToRoman = new LinkedHashMap<>();

    static {
        romanToDecimal.put("I", 1);
        romanToDecimal.put("V", 5);
        romanToDecimal.put("X", 10);
        romanToDecimal.put("L", 50);
        romanToDecimal.put("C", 100);
        romanToDecimal.put("D", 500);
        romanToDecimal.put("M", 1000);

        decimalToRoman.put(1, "I");
        decimalToRoman.put(4, "IV");
        decimalToRoman.put(5, "V");
        decimalToRoman.put(9, "IX");
        decimalToRoman.put(10, "X");
        decimalToRoman.put(40, "XL");
        decimalToRoman.put(50, "L");
        decimalToRoman.put(90, "XC");
        decimalToRoman.put(100, "C");
        decimalToRoman.put(400, "CD");
        decimalToRoman.put(500, "D");
        decimalToRoman.put(900, "CM");
        decimalToRoman.put(1000, "M");
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
            if (!romanToDecimal.containsKey(str)) {
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
            if (romanToDecimal.get(current) > romanToDecimal.get(last)) {
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
        Integer result = romanToDecimal.get(last);
        for (int i = 1; i < items.size(); i++) {
            final String current = items.get(i);
            final Integer currentValue = romanToDecimal.get(current);
            final Integer lastValue = romanToDecimal.get(last);
            if (currentValue > lastValue) {
                result = -result + currentValue;
            } else {
                result += currentValue;
            }
            last = current;
        }
        return result;
    }

    public static String toRoman(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative numbers are not suppoerted");
        }
        String result = "";
        while (number > 0) {
            final int delta = smallerNumeral(number);
            final String roman = decimalToRoman.get(delta);
            result += roman;
            number -= delta;
        }
        return result;
    }

    private static int smallerNumeral(int value) {
        return decimalToRoman.keySet().stream()
            .filter(x -> x <= value)
            .sorted()
            .reduce((first, second) -> second)
            .orElseThrow(() -> new IllegalArgumentException("no numeral <= " + value));
    }
}
