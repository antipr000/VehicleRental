package org.example.VehicleRental.branch;

import org.junit.Assert;
import org.junit.Test;

public class HikePriceAt80Test {
    @Test
    public void shouldHikePrice_returnsTrueForGreaterThan80Percentage() {
        HikePriceAt80 hikePriceAt80 = new HikePriceAt80();
        boolean response = hikePriceAt80.shouldHikePrice(5, 6);
        Assert.assertTrue(response);
    }

    @Test
    public void shouldHikePrice_returnsFalseForLessThan80Percentage() {
        HikePriceAt80 hikePriceAt80 = new HikePriceAt80();
        boolean response = hikePriceAt80.shouldHikePrice(3, 4);
        Assert.assertFalse(response);
    }
}
