package com.leetcode.twopointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 11. 盛最多水的容器 - 测试用例
 */
@DisplayName("11. 盛最多水的容器")
public class MaxAreaTest {
    
    private final MaxArea solution = new MaxArea();
    
    @Test
    @DisplayName("示例1：[1,8,6,2,5,4,8,3,7] -> 49")
    public void testExample1() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int expected = 49;
        int result = solution.maxArea(height);
        assertEquals(expected, result, 
            "输入: [1,8,6,2,5,4,8,3,7], 期望: 49, 实际: " + result);
    }
    
    @Test
    @DisplayName("示例2：[1,1] -> 1")
    public void testExample2() {
        int[] height = {1, 1};
        int expected = 1;
        int result = solution.maxArea(height);
        assertEquals(expected, result, 
            "输入: [1,1], 期望: 1, 实际: " + result);
    }
    
    @Test
    @DisplayName("边界条件：最小长度 [1,2]")
    public void testMinLength() {
        int[] height = {1, 2};
        int expected = 1;  // min(1,2) * (2-1) = 1 * 1 = 1
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("边界条件：相同高度 [5,5,5,5]")
    public void testSameHeight() {
        int[] height = {5, 5, 5, 5};
        int expected = 15;  // 5 * (4-1) = 15
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("递增序列：[1,2,3,4,5]")
    public void testIncreasing() {
        int[] height = {1, 2, 3, 4, 5};
        int expected = 6;  // min(2,5) * (5-2) = 2 * 3 = 6 或 min(3,4) * (4-3) = 3 * 1 = 3
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("递减序列：[5,4,3,2,1]")
    public void testDecreasing() {
        int[] height = {5, 4, 3, 2, 1};
        int expected = 6;  // min(5,2) * (5-1) = 2 * 3 = 6
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("两端最高：[9,1,2,3,4,5,6,7,8,9]")
    public void testHighestAtEnds() {
        int[] height = {9, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int expected = 81;  // min(9,9) * (10-1) = 9 * 9 = 81
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("中间最高：[1,2,10,2,1]")
    public void testHighestInMiddle() {
        int[] height = {1, 2, 10, 2, 1};
        int expected = 4;  // min(2,2) * (4-2) = 2 * 2 = 4
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("包含0：[0,2,0,3,0,4]")
    public void testWithZeros() {
        int[] height = {0, 2, 0, 3, 0, 4};
        int expected = 8;  // min(2,4) * (6-2) = 2 * 4 = 8
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("大数据量：100个元素")
    public void testLargeInput() {
        int[] height = new int[100];
        for (int i = 0; i < 100; i++) {
            height[i] = i + 1;
        }
        int result = solution.maxArea(height);
        assertTrue(result > 0, "Should find a valid area");
    }
    
    @Test
    @DisplayName("最大高度：[10000,1,1,1,10000]")
    public void testMaxHeight() {
        int[] height = {10000, 1, 1, 1, 10000};
        int expected = 40000;  // min(10000,10000) * (5-1) = 10000 * 4 = 40000
        int result = solution.maxArea(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("对称分布：[1,3,5,7,5,3,1]")
    public void testSymmetric() {
        int[] height = {1, 3, 5, 7, 5, 3, 1};
        int expected = 12;  // min(5,5) * (5-3) = 5 * 2 = 10 或 min(3,5) * (5-2) = 3 * 3 = 9
        int result = solution.maxArea(height);
        assertTrue(result >= 12, "Expected at least 12, but got: " + result);
    }
    
    @Test
    @DisplayName("暴力解法对比：验证双指针正确性")
    public void testBruteForceComparison() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result1 = solution.maxArea(height);
        int result2 = solution.maxAreaBruteForce(height);
        assertEquals(result1, result2, 
            "Two-pointer method should produce the same result as brute force");
    }
}

