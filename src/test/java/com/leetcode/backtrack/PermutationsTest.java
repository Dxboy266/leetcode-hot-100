package com.leetcode.backtrack;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LeetCode 46. 全排列 - 测试类
 */
class PermutationsTest {

    private final Permutations solution = new Permutations();

    @Test
    void testExample1() {
        // 测试示例1: [1,2,3]
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (3! = 6)
        assertEquals(6, result.size());
        
        // 验证是否包含所有可能的排列
        assertTrue(result.contains(List.of(1, 2, 3)));
        assertTrue(result.contains(List.of(1, 3, 2)));
        assertTrue(result.contains(List.of(2, 1, 3)));
        assertTrue(result.contains(List.of(2, 3, 1)));
        assertTrue(result.contains(List.of(3, 1, 2)));
        assertTrue(result.contains(List.of(3, 2, 1)));
    }

    @Test
    void testExample2() {
        // 测试示例2: [0,1]
        int[] nums = {0, 1};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (2! = 2)
        assertEquals(2, result.size());
        
        // 验证是否包含所有可能的排列
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
    }

    @Test
    void testExample3() {
        // 测试示例3: [1]
        int[] nums = {1};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (1! = 1)
        assertEquals(1, result.size());
        
        // 验证是否包含唯一排列
        assertTrue(result.contains(List.of(1)));
    }

    @Test
    void testFourElements() {
        // 测试4个元素: [1,2,3,4]
        int[] nums = {1, 2, 3, 4};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (4! = 24)
        assertEquals(24, result.size());
        
        // 验证几个示例排列
        assertTrue(result.contains(List.of(1, 2, 3, 4)));
        assertTrue(result.contains(List.of(4, 3, 2, 1)));
    }

    @Test
    void testNegativeNumbers() {
        // 测试包含负数: [-1,0,1]
        int[] nums = {-1, 0, 1};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (3! = 6)
        assertEquals(6, result.size());
        
        // 验证是否包含所有可能的排列
        assertTrue(result.contains(List.of(-1, 0, 1)));
        assertTrue(result.contains(List.of(-1, 1, 0)));
        assertTrue(result.contains(List.of(0, -1, 1)));
        assertTrue(result.contains(List.of(0, 1, -1)));
        assertTrue(result.contains(List.of(1, -1, 0)));
        assertTrue(result.contains(List.of(1, 0, -1)));
    }

    @Test
    void testMaximumLength() {
        // 测试最大长度: 6个元素
        int[] nums = {1, 2, 3, 4, 5, 6};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确 (6! = 720)
        assertEquals(720, result.size());
    }

    @Test
    void testEdgeCaseMinimumLength() {
        // 测试最小长度: 1个元素
        int[] nums = {5};
        List<List<Integer>> result = solution.permute(nums);
        
        // 验证结果不为null
        assertNotNull(result);
        
        // 验证排列数量是否正确
        assertEquals(1, result.size());
        
        // 验证内容
        assertEquals(List.of(5), result.get(0));
    }

    @Test
    void testResultValidity() {
        // 验证结果的有效性
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.permute(nums);
        
        // 检查每个排列:
        // 1. 长度应该等于原数组长度
        // 2. 不应该包含重复元素
        // 3. 应该包含原数组的所有元素
        for (List<Integer> permutation : result) {
            assertEquals(nums.length, permutation.size());
            
            // 检查是否包含重复元素
            assertEquals(nums.length, permutation.stream().distinct().count());
            
            // 检查是否包含原数组的所有元素
            for (int num : nums) {
                assertTrue(permutation.contains(num));
            }
        }
    }
}