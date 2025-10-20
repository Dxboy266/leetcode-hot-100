package com.leetcode.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LongestConsecutiveSequence 测试类
 */
class LongestConsecutiveSequenceTest {
    
    private final LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
    
    @Test
    void testExample1() {
        // 测试用例 1：最长连续序列是 [1, 2, 3, 4]
        int[] nums = {100, 4, 200, 1, 3, 2};
        int expected = 4;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testExample2() {
        // 测试用例 2：最长连续序列是 [0, 1, 2, 3, 4, 5, 6, 7, 8]
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int expected = 9;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testEmptyArray() {
        // 测试空数组
        int[] nums = {};
        int expected = 0;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testSingleElement() {
        // 测试单个元素
        int[] nums = {1};
        int expected = 1;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testAllSameElements() {
        // 测试所有元素相同
        int[] nums = {1, 1, 1, 1};
        int expected = 1;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testNegativeNumbers() {
        // 测试负数
        int[] nums = {-1, -2, -3, 0, 1, 2};
        int expected = 6;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testNoConsecutive() {
        // 测试没有连续序列（都是单独的数字）
        int[] nums = {1, 3, 5, 7, 9};
        int expected = 1;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testFullyConsecutive() {
        // 测试完全连续
        int[] nums = {5, 6, 7, 8, 9};
        int expected = 5;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testWithDuplicates() {
        // 测试包含重复元素
        int[] nums = {1, 2, 0, 1};
        int expected = 3;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testLargeGaps() {
        // 测试间隔很大的数字
        int[] nums = {1, 2, 3, 1000, 1001, 1002, 1003};
        int expected = 4;
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testMultipleSequences() {
        // 测试多个不同长度的连续序列
        int[] nums = {100, 4, 200, 1, 3, 2, 101, 102};
        int expected = 4; // [1, 2, 3, 4] 或 [100, 101, 102] 中较长的
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    @Test
    void testUnorderedSequence() {
        // 测试完全无序的连续序列
        int[] nums = {9, 1, 4, 7, 3, 2, 8, 5, 6};
        int expected = 9; // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        assertEquals(expected, solution.longestConsecutive(nums));
    }
    
    // ========== 测试排序方法 ==========
    
    @Test
    void testSortMethod_Example1() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int expected = 4;
        
        assertEquals(expected, solution.longestConsecutiveSort(nums));
    }
    
    @Test
    void testSortMethod_Example2() {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int expected = 9;
        
        assertEquals(expected, solution.longestConsecutiveSort(nums));
    }
    
    @Test
    void testSortMethod_WithDuplicates() {
        int[] nums = {1, 2, 0, 1};
        int expected = 3;
        
        assertEquals(expected, solution.longestConsecutiveSort(nums));
    }
    
    @Test
    void testSortMethod_EmptyArray() {
        int[] nums = {};
        int expected = 0;
        
        assertEquals(expected, solution.longestConsecutiveSort(nums));
    }
}

