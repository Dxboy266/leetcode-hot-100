package com.leetcode.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinNumberOperationsTest {

    private final MinNumberOperations solution = new MinNumberOperations();

    @Test
    public void testExample1() {
        int[] target = {1, 2, 3, 2, 1};
        int expected = 3;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testExample2() {
        int[] target = {3, 1, 1, 2};
        int expected = 4;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testExample3() {
        int[] target = {3, 1, 5, 4, 2};
        int expected = 7;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testExample4() {
        int[] target = {1, 1, 1, 1};
        int expected = 1;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testSingleElement() {
        int[] target = {5};
        int expected = 5;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testAscendingArray() {
        int[] target = {1, 2, 3, 4, 5};
        int expected = 5;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }

    @Test
    public void testDescendingArray() {
        int[] target = {5, 4, 3, 2, 1};
        int expected = 5;
        assertEquals(expected, solution.minNumberOperations(target));
        assertEquals(expected, solution.minNumberOperationsGreedy(target));
    }
}