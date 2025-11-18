package com.leetcode.dp;

/**
 * 474. 一和零
 * 难度：中等
 *
 * 题目描述：
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"}，因此答案是 4 。
 *
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * 相关标签：动态规划
 */
public class OnesAndZeroes {
    
    /**
     * 找出最大子集的长度
     * 
     * 解题思路：
     * 这是一个典型的二维01背包问题。每个字符串都有两个"重量"：0的个数和1的个数，
     * 我们的目标是在不超过m个0和n个1的前提下，尽可能多地装入字符串（价值为1）。
     * 
     * 状态定义：dp[i][j] 表示最多使用i个0和j个1时能形成的最大子集大小
     * 状态转移方程：
     * 对于每个字符串str（包含zeros个0和ones个1）：
     * dp[i][j] = max(dp[i][j], dp[i-zeros][j-ones] + 1) （如果i>=zeros且j>=ones）
     * 
     * 为什么要逆序遍历？因为我们是01背包，每个元素只能使用一次，逆序可以确保
     * 在计算dp[i][j]时，dp[i-zeros][j-ones]使用的是上一轮（未考虑当前字符串）的结果。
     * 
     * @param strs 二进制字符串数组
     * @param m 最多允许的0的个数
     * @param n 最多允许的1的个数
     * @return 最大子集的长度
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 表示最多使用i个0和j个1时能形成的最大子集大小
        int[][] dp = new int[m+1][n+1];
        
        // 遍历每个字符串（每个物品）
        for (String str : strs) {
            int zeros = count0(str);
            int ones = count1(str);
            
            // 逆序遍历背包容量，防止重复选取同一字符串
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    // 状态转移方程
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        
        return dp[m][n];
    }

    /**
     * 计算字符串中0的个数
     *
     * @param str 字符串
     * @return 0的个数
     */
    private int count0(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计字符串中1的个数
     *
     * @param str 字符串
     * @return 1的个数
     */
    private int count1(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }
}