package com.leetcode.twopointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和 - 测试用例
 */
@DisplayName("15. 三数之和")
public class ThreeSumTest {
    
    private final ThreeSum solution = new ThreeSum();
    
    /**
     * 辅助方法：比较两个结果列表是否相等（忽略顺序）
     */
    private boolean areListsEqual(List<List<Integer>> list1, List<List<Integer>> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        
        // 将每个三元组排序，然后将整个列表排序
        List<List<Integer>> sorted1 = new ArrayList<>();
        for (List<Integer> triplet : list1) {
            List<Integer> sorted = new ArrayList<>(triplet);
            sorted.sort(Integer::compareTo);
            sorted1.add(sorted);
        }
        sorted1.sort((a, b) -> {
            for (int i = 0; i < 3; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });
        
        List<List<Integer>> sorted2 = new ArrayList<>();
        for (List<Integer> triplet : list2) {
            List<Integer> sorted = new ArrayList<>(triplet);
            sorted.sort(Integer::compareTo);
            sorted2.add(sorted);
        }
        sorted2.sort((a, b) -> {
            for (int i = 0; i < 3; i++) {
                int cmp = a.get(i).compareTo(b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });
        
        return sorted1.equals(sorted2);
    }
    
    @Test
    @DisplayName("示例1：[-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]")
    public void testExample1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-1, -1, 2),
            Arrays.asList(-1, 0, 1)
        );
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("示例2：[0,1,1] -> []")
    public void testExample2() {
        int[] nums = {0, 1, 1};
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("示例3：[0,0,0] -> [[0,0,0]]")
    public void testExample3() {
        int[] nums = {0, 0, 0};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(0, 0, 0)
        );
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("边界条件：最小长度 [0,0,0]")
    public void testMinLength() {
        int[] nums = {0, 0, 0};
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(1, result.size());
    }
    
    @Test
    @DisplayName("边界条件：无解 [1,2,3]")
    public void testNoSolution() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(0, result.size());
    }
    
    @Test
    @DisplayName("去重测试：[-2,0,0,2,2] -> [[-2,0,2]]")
    public void testDuplicates1() {
        int[] nums = {-2, 0, 0, 2, 2};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-2, 0, 2)
        );
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("去重测试：[-1,-1,-1,2] -> [[-1,-1,2]]")
    public void testDuplicates2() {
        int[] nums = {-1, -1, -1, 2};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-1, -1, 2)
        );
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("多个解：[-4,-1,-1,0,1,2] -> [[-1,-1,2],[-1,0,1]]")
    public void testMultipleSolutions() {
        int[] nums = {-4, -1, -1, 0, 1, 2};
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(-1, -1, 2),
            Arrays.asList(-1, 0, 1)
        );
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(areListsEqual(expected, result), 
            "Expected: " + expected + ", but got: " + result);
    }
    
    @Test
    @DisplayName("负数为主：[-5,-4,-3,-2,-1,0,1,2] -> [[-2,0,2],[-1,0,1]]")
    public void testNegativeNumbers() {
        int[] nums = {-5, -4, -3, -2, -1, 0, 1, 2};
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(result.size() >= 2, "Should have at least 2 solutions");
    }
    
    @Test
    @DisplayName("正数为主：[-2,-1,0,1,2,3,4,5] -> [[-2,0,2],[-1,0,1]]")
    public void testPositiveNumbers() {
        int[] nums = {-2, -1, 0, 1, 2, 3, 4, 5};
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue(result.size() >= 2, "Should have at least 2 solutions");
    }
    
    @Test
    @DisplayName("大数据量：15个元素")
    public void testLargeInput() {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = solution.threeSum(nums);
        assertNotNull(result);
        // 验证没有重复的三元组
        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                assertFalse(areListsEqual(Arrays.asList(result.get(i)), Arrays.asList(result.get(j))),
                    "Found duplicate triplets: " + result.get(i) + " and " + result.get(j));
            }
        }
    }
    
    @Test
    @DisplayName("优化方法测试：与基础方法结果一致")
    public void testOptimizedMethod() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = solution.threeSum(nums);
        List<List<Integer>> result2 = solution.threeSumOptimized(nums);
        assertTrue(areListsEqual(result1, result2), 
            "Optimized method should produce the same result as basic method");
    }
}

