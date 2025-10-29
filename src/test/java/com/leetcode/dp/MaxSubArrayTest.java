package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 53. 最大子数组和
 * 覆盖多种写法：Kadane / DP数组 / 前缀和 / 分治 / 线段树（可选）
 *
 * @Author Dxboy266
 * @Date 2025-10-28
 */
public class MaxSubArrayTest {

    private final MaxSubArray solution = new MaxSubArray();

    private void assertAllMethods(int[] nums, int expected) {
        assertEquals(expected, solution.maxSubArrayKadane(nums), "Kadane failed");
        assertEquals(expected, solution.maxSubArrayDP(nums), "DP failed");
        assertEquals(expected, solution.maxSubArrayPrefixSum(nums), "PrefixSum failed");
        assertEquals(expected, solution.maxSubArrayDivideAndConquer(nums), "Divide&Conquer failed");
        // 线段树为可选实现，如未实现可注释下一行
        assertEquals(expected, solution.maxSubArraySegmentTree(nums), "SegmentTree failed");
    }

    @Test
    public void testExample1() {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        assertAllMethods(nums, 6); // [4,-1,2,1]
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        assertAllMethods(nums, 1);
    }

    @Test
    public void testAllNegative() {
        int[] nums = {-8, -3, -6, -2, -5, -4};
        assertAllMethods(nums, -2);
    }

    @Test
    public void testAllPositive() {
        int[] nums = {1, 2, 3, 4, 5};
        assertAllMethods(nums, 15);
    }

    @Test
    public void testWithZeros() {
        int[] nums = {0, -1, 0, -2, 0, 3, 0};
        assertAllMethods(nums, 3);
    }

    @Test
    public void testTailBest() {
        int[] nums = {-1, -2, 3, 4, 5};
        assertAllMethods(nums, 12); // [3,4,5]
    }

    @Test
    public void testHeadBest() {
        int[] nums = {5, 4, -10, 1};
        assertAllMethods(nums, 9); // [5,4]
    }

    @Test
    public void testMiddleBest() {
        int[] nums = {-1, 5, -1, 5, -1};
        assertAllMethods(nums, 9); // [5,-1,5]
    }

    @Test
    public void testLargeFluctuations() {
        int[] nums = {100, -90, 80, -70, 60, -50, 40, -30, 20, -10};
        assertAllMethods(nums, 100); // 只取第一个 100
    }

    @Test
    public void testEqualOptions() {
        int[] nums = {2, -1, 2, -1, 2, -1};
        assertAllMethods(nums, 4); // [2,-1,2, -1,2] 也可多段相同最大值
    }

    @Test
    public void testLargePositiveBlock() {
        int[] nums = {-5, -2, 10, 20, -1, -2, 30, -40, 5};
        assertAllMethods(nums, 57); // [10,20,-1,-2,30]
    }
}
