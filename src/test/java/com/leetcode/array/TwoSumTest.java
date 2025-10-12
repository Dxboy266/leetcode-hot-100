package com.leetcode.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TwoSum 测试类
 */
class TwoSumTest {
    
    private final TwoSum solution = new TwoSum();
    
    @Test
    void testExample1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }
    
    @Test
    void testExample2() {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] expected = {1, 2};
        
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }
    
    @Test
    void testExample3() {
        int[] nums = {3, 3};
        int target = 6;
        int[] expected = {0, 1};
        
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }
    
    @Test
    void testNegativeNumbers() {
        int[] nums = {-1, -2, -3, -4, -5};
        int target = -8;
        int[] expected = {2, 4};
        
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }
    
    @Test
    void testMixedNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] expected = {0, 2};
        
        assertArrayEquals(expected, solution.twoSum(nums, target));
    }
    
    @Test
    void testBruteForce() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        
        assertArrayEquals(expected, solution.twoSumBruteForce(nums, target));
    }
}

