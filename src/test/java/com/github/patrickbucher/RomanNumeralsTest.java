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
        romanToDecimal.put("VIII", 8);
        romanToDecimal.put("IX", 9);
        romanToDecimal.put("X", 10);
        romanToDecimal.put("XXIII", 23);
        romanToDecimal.put("XCII", 92);
        romanToDecimal.put("CXXXIX", 139);
        romanToDecimal.put("CCLXXXIX", 289);
        romanToDecimal.put("CMLIX", 959);
        romanToDecimal.put("MXXIII", 1023);
        romanToDecimal.put("MDXCII", 1592);
        romanToDecimal.put("MCMXLVIII", 1948);
        romanToDecimal.put("MCMLXXXIV", 1984);
        romanToDecimal.put("MMXIX", 2019);
    }

    @Test
    public void testRomanToDecimal() {
        for (final String roman : romanToDecimal.keySet()) {
            final int decimal = romanToDecimal.get(roman);
            final int got = RomanNumerals.toNumber(roman);
            Assert.assertEquals(String.format("%s to decimal", roman), decimal, got);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonRomanCharacters() {
        final String nonRoman = "MDCLXVIk";
        RomanNumerals.toNumber(nonRoman);
    }

    @Test
    public void testDecimalToRoman() {
        for (final String roman : romanToDecimal.keySet()) {
            final int decimal = romanToDecimal.get(roman);
            final String got = RomanNumerals.toRoman(decimal);
            Assert.assertEquals(String.format("%d to roman", decimal), roman, got);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInput() {
        final int negativeNumber = -123;
        RomanNumerals.toRoman(negativeNumber);
    }
}
