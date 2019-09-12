package ch.paedubucher.romanNumerals;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeralsTest {

    private static final Map<String, Integer> romanToDecimal = new LinkedHashMap<>();

    static {
        romanToDecimal.put("", 0);
        romanToDecimal.put("I", 1);
        romanToDecimal.put("II", 2);
        romanToDecimal.put("III", 3);
        romanToDecimal.put("IV", 4);
        romanToDecimal.put("V", 5);
        romanToDecimal.put("VI", 6);
        romanToDecimal.put("VII", 7);
        romanToDecimal.put("IIX", 8);
        romanToDecimal.put("IX", 9);
        romanToDecimal.put("X", 10);
        romanToDecimal.put("XXIII", 23);
        romanToDecimal.put("CXXXIX", 139);
        romanToDecimal.put("CCXXCIX", 289);
        romanToDecimal.put("DCDLIX", 959);
        romanToDecimal.put("MXXIII", 1023);
        romanToDecimal.put("MDXCII", 1592);
        romanToDecimal.put("MDCDXLIIX", 1948);
        romanToDecimal.put("MCMLXXXIV", 1984);
    }

    @Test
    public void testRomanToDecimal() {
        for (final String roman : romanToDecimal.keySet()) {
            final int expected = romanToDecimal.get(roman);
            final int got = RomanNumerals.toNumber(roman);
            Assert.assertEquals(
                String.format("%s to decimal: expected %d, got %d", roman, expected, got),
                got, expected
            );
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonRomanCharacters() {
        final String nonRoman = "MDCLXVIk";
        RomanNumerals.toNumber(nonRoman);
    }
}
