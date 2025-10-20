package com.leetcode.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * DailyTemperatures 测试类
 */
class DailyTemperaturesTest {
    
    private final DailyTemperatures solution = new DailyTemperatures();
    
    @Test
    void testExample1() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testExample2() {
        int[] temperatures = {30, 40, 50, 60};
        int[] expected = {1, 1, 1, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testExample3() {
        int[] temperatures = {30, 60, 90};
        int[] expected = {1, 1, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testSingleElement() {
        int[] temperatures = {30};
        int[] expected = {0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testAllDecreasing() {
        int[] temperatures = {100, 90, 80, 70, 60};
        int[] expected = {0, 0, 0, 0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testAllIncreasing() {
        int[] temperatures = {30, 40, 50, 60, 70};
        int[] expected = {1, 1, 1, 1, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testAllSame() {
        int[] temperatures = {50, 50, 50, 50};
        int[] expected = {0, 0, 0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testTwoElements() {
        int[] temperatures = {30, 40};
        int[] expected = {1, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testTwoElementsDecreasing() {
        int[] temperatures = {40, 30};
        int[] expected = {0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testComplexPattern() {
        int[] temperatures = {89, 62, 70, 58, 47, 47, 46, 76, 100, 70};
        int[] expected = {8, 1, 5, 4, 3, 2, 1, 1, 0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testLongDistance() {
        int[] temperatures = {30, 29, 28, 27, 26, 100};
        int[] expected = {5, 4, 3, 2, 1, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
    
    @Test
    void testMinMaxTemperatures() {
        int[] temperatures = {30, 100, 30};
        int[] expected = {1, 0, 0};
        
        assertArrayEquals(expected, solution.dailyTemperatures(temperatures));
    }
}

