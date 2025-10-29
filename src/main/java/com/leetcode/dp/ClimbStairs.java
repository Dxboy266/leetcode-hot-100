package com.leetcode.dp;

/**
 * 70. 爬楼梯 (Climbing Stairs)
 *
 * 题目链接：https://leetcode.cn/problems/climbing-stairs/
 * 难度：简单
 * 标签：动态规划、记忆化搜索、斐波那契
 *
 * ==================== 题目描述 ====================
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 * - 1 <= n <= 45
 *
 * ==================== 解题提示 ====================
 * 定义状态：dp[i] 表示到达第 i 阶的方法数
 * 转移方程：dp[i] = dp[i-1] + dp[i-2]
 * 初始条件：dp[0] = 1, dp[1] = 1
 * 目标：dp[n]
 *
 * 也可用 O(1) 空间优化（滚动变量）
 *
 * @Author Dxboy266
 * @Date 2025-10-28
 */
public class ClimbStairs {

    /**
     * 解法一：动态规划（数组）
     * @param n 台阶数
     * @return 方案数
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    /**
     * 解法二：空间优化（滚动变量）
     * @param n 台阶数
     * @return 方案数
     */
    public int climbStairsOptimized(int n) {
        if (n <= 1) {
            return 1;
        }

        int dpI1 = 1, dpI2 = 1;
        int dpI = 0;
        for (int i = 2; i <= n; i++) {
            dpI = dpI1 + dpI2;
            dpI2 = dpI1;
            dpI1 = dpI;
        }
        return dpI;
    }

    /**
     * 可选：记忆化搜索（自顶向下）
     * @param n 台阶数
     * @return 方案数
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 0;
        return dpMemo(n, memo);
    }

    private int dpMemo(int n, int[] memo) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = dpMemo(n - 1, memo) + dpMemo(n - 2, memo);
        return memo[n];
    }
}
