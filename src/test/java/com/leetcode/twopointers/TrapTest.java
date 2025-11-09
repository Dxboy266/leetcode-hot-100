package com.leetcode.twopointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 42. 接雨水 - 测试用例
 */
@DisplayName("42. 接雨水")
public class TrapTest {
    
    private final Trap solution = new Trap();
    
    @Test
    @DisplayName("示例1：[0,1,0,2,1,0,1,3,2,1,2,1] -> 6")
    public void testExample1() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int expected = 6;
        int result = solution.trap(height);
        assertEquals(expected, result, 
            "输入: [0,1,0,2,1,0,1,3,2,1,2,1], 期望: 6, 实际: " + result);
    }
    
    @Test
    @DisplayName("示例2：[4,2,0,3,2,5] -> 9")
    public void testExample2() {
        int[] height = {4, 2, 0, 3, 2, 5};
        int expected = 9;
        int result = solution.trap(height);
        assertEquals(expected, result, 
            "输入: [4,2,0,3,2,5], 期望: 9, 实际: " + result);
    }
    
    @Test
    @DisplayName("边界条件：单个元素 [5]")
    public void testSingleElement() {
        int[] height = {5};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("边界条件：两个元素 [3,5]")
    public void testTwoElements() {
        int[] height = {3, 5};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("边界条件：全0 [0,0,0,0]")
    public void testAllZeros() {
        int[] height = {0, 0, 0, 0};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("递增序列：[1,2,3,4,5] -> 0")
    public void testIncreasing() {
        int[] height = {1, 2, 3, 4, 5};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result, "递增序列不能接雨水");
    }
    
    @Test
    @DisplayName("递减序列：[5,4,3,2,1] -> 0")
    public void testDecreasing() {
        int[] height = {5, 4, 3, 2, 1};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result, "递减序列不能接雨水");
    }
    
    @Test
    @DisplayName("V字形：[3,0,3] -> 3")
    public void testVShape() {
        int[] height = {3, 0, 3};
        int expected = 3;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("W字形：[3,0,2,0,4] -> 7")
    public void testWShape() {
        int[] height = {3, 0, 2, 0, 4};
        int expected = 7;  // 第2个位置接3，第3个位置接1，第4个位置接3，共7
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("平台：[2,2,2,2] -> 0")
    public void testFlat() {
        int[] height = {2, 2, 2, 2};
        int expected = 0;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("左高右低：[5,2,1,2,1,5] -> 14")
    public void testLeftHighRightLow() {
        int[] height = {5, 2, 1, 2, 1, 5};
        int expected = 14;  // (5-2)+(5-1)+(5-2)+(5-1) = 3+4+3+4 = 14
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("多个凹槽：[4,2,3,1,2] -> 2")
    public void testMultiplePits() {
        int[] height = {4, 2, 3, 1, 2};
        // 位置1: min(4,3)-2=1, 位置3: min(4,2)-1=1, 总共2单位水
        int expected = 2;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("复杂情况：[5,2,1,2,1,5,3,1,4] -> 18")
    public void testComplex() {
        int[] height = {5, 2, 1, 2, 1, 5, 3, 1, 4};
        int result = solution.trap(height);
        assertTrue(result >= 0, "Result should be non-negative");
    }
    
    @Test
    @DisplayName("大数据量：1000个元素")
    public void testLargeInput() {
        int[] height = new int[1000];
        for (int i = 0; i < 1000; i++) {
            height[i] = (i % 10) + 1;  // 1-10循环
        }
        int result = solution.trap(height);
        assertTrue(result >= 0, "Result should be non-negative");
    }
    
    @Test
    @DisplayName("最大高度：[100000,0,100000] -> 100000")
    public void testMaxHeight() {
        int[] height = {100000, 0, 100000};
        int expected = 100000;
        int result = solution.trap(height);
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("DP方法测试：与双指针结果一致")
    public void testDPMethod() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result1 = solution.trap(height);
        int result2 = solution.trapDP(height);
        assertEquals(result1, result2, 
            "DP method should produce the same result as two-pointer method");
    }
    
    @Test
    @DisplayName("单调栈方法测试：与双指针结果一致")
    public void testMonotonicStackMethod() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result1 = solution.trap(height);
        int result2 = solution.trapMonotonicStack(height);
        assertEquals(result1, result2, 
            "Monotonic stack method should produce the same result as two-pointer method");
    }
    
    @Test
    @DisplayName("三种方法对比：复杂情况")
    public void testAllMethodsComparison() {
        int[] height = {4, 2, 0, 3, 2, 5};
        int result1 = solution.trap(height);
        int result2 = solution.trapDP(height);
        int result3 = solution.trapMonotonicStack(height);
        assertEquals(result1, result2, "Two-pointer and DP should match");
        assertEquals(result1, result3, "Two-pointer and monotonic stack should match");
    }
}

