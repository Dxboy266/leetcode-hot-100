package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 198. 打家劫舍
 *
 * @Author TODO: 你的名字
 * @Date TODO: 完成日期
 */
public class RobTest {

    private final Rob solution = new Rob();

    @Test
    public void testExample1() {
        // 输入：[1,2,3,1] -> 输出：4
        int[] nums = {1, 2, 3, 1};
        assertEquals(4, solution.rob(nums));
        assertEquals(4, solution.robOptimized(nums));
        assertEquals(4, solution.robMemoization(nums));
    }

    @Test
    public void testExample2() {
        // 输入：[2,7,9,3,1] -> 输出：12
        int[] nums = {2, 7, 9, 3, 1};
        assertEquals(12, solution.rob(nums));
        assertEquals(12, solution.robOptimized(nums));
        assertEquals(12, solution.robMemoization(nums));
    }

    @Test
    public void testSingleHouse() {
        // 只有一个房屋的情况
        int[] nums = {5};
        assertEquals(5, solution.rob(nums));
        assertEquals(5, solution.robOptimized(nums));
        assertEquals(5, solution.robMemoization(nums));
    }

    @Test
    public void testTwoHouses() {
        // 只有两个房屋的情况
        int[] nums = {2, 3};
        assertEquals(3, solution.rob(nums));
        assertEquals(3, solution.robOptimized(nums));
        assertEquals(3, solution.robMemoization(nums));
    }

    @Test
    public void testThreeHouses() {
        // 三个房屋的情况
        int[] nums = {2, 1, 1, 2};
        assertEquals(4, solution.rob(nums));
        assertEquals(4, solution.robOptimized(nums));
    }

    @Test
    public void testAllSameValue() {
        // 所有房屋金额相同
        int[] nums = {1, 1, 1, 1, 1};
        assertEquals(3, solution.rob(nums));
        assertEquals(3, solution.robOptimized(nums));
    }

    @Test
    public void testIncreasingOrder() {
        // 递增序列
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(9, solution.rob(nums));
        assertEquals(9, solution.robOptimized(nums));
    }

    @Test
    public void testDecreasingOrder() {
        // 递减序列
        int[] nums = {5, 4, 3, 2, 1};
        assertEquals(9, solution.rob(nums));
        assertEquals(9, solution.robOptimized(nums));
    }

    @Test
    public void testEdgeCaseWithZero() {
        // 包含零的情况
        int[] nums = {0, 0, 0, 1};
        assertEquals(1, solution.rob(nums));
        assertEquals(1, solution.robOptimized(nums));
    }

    @Test
    public void testMaximumConstraints() {
        // 边界情况：长度为100，数值为400
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = (i % 2 == 0) ? 400 : 0;
        }
        // 应该选择所有偶数位置的房屋，共50个，总金额为50*400=20000
        assertEquals(20000, solution.rob(nums));
        assertEquals(20000, solution.robOptimized(nums));
    }
}