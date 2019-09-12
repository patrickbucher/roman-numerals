package ch.paedubucher.romanNumerals;

import org.junit.Assert;
import org.junit.Test;

public class RomanNumeralsTest {

    @Test
    public void testEmptyStringToNumber() {
        final String roman = "";
        final int number = RomanNumerals.toNumber(roman);
        Assert.assertEquals(0, number);
    }

    @Test
    public void testNonRomanCharacters() {
        final String nonRoman = "MDCLXVIk";
        try {
            RomanNumerals.toNumber(nonRoman);
            Assert.fail(nonRoman + " must cause an exception");
        } catch (final IllegalArgumentException ex) {
        }
    }

    @Test
    public void test1984() {
        final String roman = "MCMLXXXIV";
        final int number = RomanNumerals.toNumber(roman);
        Assert.assertEquals(1984, number);
    }
}
