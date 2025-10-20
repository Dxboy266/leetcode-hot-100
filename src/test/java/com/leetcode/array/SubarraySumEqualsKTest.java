package com.leetcode.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SubarraySumEqualsK 测试类
 */
class SubarraySumEqualsKTest {
    
    private final SubarraySumEqualsK solution = new SubarraySumEqualsK();
    
    @Test
    void testExample1() {
        // 测试用例 1：子数组 [1,1] 和 [1,1] 的和为 2
        int[] nums = {1, 1, 1};
        int k = 2;
        int expected = 2;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testExample2() {
        // 测试用例 2：子数组 [1,2] 和 [3] 的和为 3
        int[] nums = {1, 2, 3};
        int k = 3;
        int expected = 2;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testSingleElement() {
        // 测试单个元素
        int[] nums = {1};
        int k = 1;
        int expected = 1;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testSingleElementNotEqual() {
        // 测试单个元素不等于k
        int[] nums = {1};
        int k = 2;
        int expected = 0;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testNegativeNumbers() {
        // 测试包含负数的情况
        int[] nums = {1, -1, 0};
        int k = 0;
        int expected = 3; // [1,-1], [0], [1,-1,0]
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testAllNegativeNumbers() {
        // 测试全负数数组
        int[] nums = {-1, -1, 1};
        int k = 0;
        int expected = 1; // [-1, 1]
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testZeroInArray() {
        // 测试包含0的情况
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int k = 0;
        int expected = 55; // C(10,2) + 10 = 45 + 10 = 55
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testLargeK() {
        // 测试大的k值
        int[] nums = {1, 2, 3, 4, 5};
        int k = 15;
        int expected = 1; // [1,2,3,4,5]
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testNegativeK() {
        // 测试负的k值
        int[] nums = {1, -1, 1, -1};
        int k = 0;
        int expected = 4; // [1,-1], [-1,1], [1,-1,1,-1], [0] (如果存在)
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testNoSubarray() {
        // 测试没有符合条件的子数组
        int[] nums = {1, 2, 3, 4, 5};
        int k = 100;
        int expected = 0;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testConsecutiveOnes() {
        // 测试连续1的情况
        int[] nums = {1, 1, 1, 1, 1};
        int k = 2;
        int expected = 4; // [1,1] 出现4次
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testMixedNumbers() {
        // 测试混合正负数
        int[] nums = {3, 4, 7, 2, -3, 1, 4, 2};
        int k = 7;
        int expected = 4; // [3,4], [7], [7,2,-3,1], [1,4,2]
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    @Test
    void testEdgeCaseEmpty() {
        // 测试边界情况：空数组（虽然题目保证长度>=1，但测试鲁棒性）
        int[] nums = {};
        int k = 0;
        int expected = 0;
        
        assertEquals(expected, solution.subarraySum(nums, k));
    }
    
    // ========== 测试暴力解法 ==========
    
    @Test
    void testBruteForce_Example1() {
        int[] nums = {1, 1, 1};
        int k = 2;
        int expected = 2;
        
        assertEquals(expected, solution.subarraySumBruteForce(nums, k));
    }
    
    @Test
    void testBruteForce_Example2() {
        int[] nums = {1, 2, 3};
        int k = 3;
        int expected = 2;
        
        assertEquals(expected, solution.subarraySumBruteForce(nums, k));
    }
    
    @Test
    void testBruteForce_NegativeNumbers() {
        int[] nums = {1, -1, 0};
        int k = 0;
        int expected = 3;
        
        assertEquals(expected, solution.subarraySumBruteForce(nums, k));
    }
    
    @Test
    void testBruteForce_ConsecutiveOnes() {
        int[] nums = {1, 1, 1, 1, 1};
        int k = 2;
        int expected = 4;
        
        assertEquals(expected, solution.subarraySumBruteForce(nums, k));
    }

    @Test
    void testBruteForce_AllZeros() {
        int[] nums = {0, 0};
        int k = 0;
        int expected = 3;

        assertEquals(expected, solution.subarraySumBruteForce(nums, k));
    }
}

