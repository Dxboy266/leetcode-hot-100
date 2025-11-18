package com.leetcode.dp;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnesAndZeroesTest {

    private final OnesAndZeroes solution = new OnesAndZeroes();

    @Test
    public void testExample1() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        int expected = 4;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }

    @Test
    public void testExample2() {
        String[] strs = {"10", "0", "1"};
        int m = 1, n = 1;
        int expected = 2;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }

    @Test
    public void testEdgeCase1() {
        String[] strs = {"10", "0001"};
        int m = 1, n = 1;
        int expected = 1;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }

    @Test
    public void testEdgeCase2() {
        String[] strs = {"10", "0001"};
        int m = 0, n = 0;
        int expected = 0;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }

    @Test
    public void testAllZeros() {
        String[] strs = {"0", "00", "000"};
        int m = 3, n = 0;
        int expected = 2;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }

    @Test
    public void testAllOnes() {
        String[] strs = {"1", "11", "111"};
        int m = 0, n = 3;
        int expected = 2;
        assertEquals(expected, solution.findMaxForm(strs, m, n));
    }
}