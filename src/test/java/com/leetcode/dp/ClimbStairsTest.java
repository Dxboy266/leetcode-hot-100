package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 70. 爬楼梯
 *
 * @Author Dxboy266
 * @Date 2025-10-28
 */
public class ClimbStairsTest {

    private final ClimbStairs solution = new ClimbStairs();

    @Test
    public void testExample1() {
        // n = 2 -> 2
        assertEquals(2, solution.climbStairs(2));
        assertEquals(2, solution.climbStairsOptimized(2));
        assertEquals(2, solution.climbStairsMemo(2));
    }

    @Test
    public void testExample2() {
        // n = 3 -> 3
        assertEquals(3, solution.climbStairs(3));
        assertEquals(3, solution.climbStairsOptimized(3));
    }

    @Test
    public void testSmall() {
        assertEquals(1, solution.climbStairs(1));
        assertEquals(1, solution.climbStairsOptimized(1));
    }

    @Test
    public void testMedium() {
        assertEquals(5, solution.climbStairs(4));
        assertEquals(8, solution.climbStairs(5));
        assertEquals(5, solution.climbStairsOptimized(4));
        assertEquals(8, solution.climbStairsOptimized(5));
    }

    @Test
    public void testLarger() {
        assertEquals(89, solution.climbStairs(10));
        assertEquals(144, solution.climbStairs(11));
        assertEquals(89, solution.climbStairsOptimized(10));
        assertEquals(144, solution.climbStairsOptimized(11));
    }

    @Test
    public void testEdgeMaxConstraint() {
        // 上界 n = 45，结果为 1836311903
        assertEquals(1836311903, solution.climbStairs(45));
        assertEquals(1836311903, solution.climbStairsOptimized(45));
    }
}
