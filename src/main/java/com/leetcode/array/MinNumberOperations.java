package com.leetcode.array;

/**
 * 1526. 形成目标数组的子数组最少增加次数
 * 
 * 题目链接：https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 * 难度：困难
 * 标签：数组、贪心、动态规划
 *
 * ==================== 题目描述 ====================
 * 给你一个整数数组 target 和一个数组 initial ，initial 数组与 target 数组有同样的维度，
 * 且一开始全部为 0 。请你返回从 initial 得到 target 的最少操作次数，每次操作需遵循以下规则：
 * 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。
 *
 * 示例 1：
 * 输入：target = [1,2,3,2,1]
 * 输出：3
 * 解释：我们需要至少 3 次操作从 initial 数组得到 target 数组。
 * [0,0,0,0,0] 操作 1：[1,1,1,1,1]
 * [1,1,1,1,1] 操作 2：[1,2,2,2,1]
 * [1,2,2,2,1] 操作 3：[1,2,3,2,1]
 *
 * 示例 2：
 * 输入：target = [3,1,1,2]
 * 输出：4
 * 解释：[0,0,0,0] -> [1,1,1,1] -> [1,1,1,2] -> [2,1,1,2] -> [3,1,1,2]
 *
 * 示例 3：
 * 输入：target = [3,1,5,4,2]
 * 输出：7
 *
 * 提示：
 * - 1 <= target.length <= 10^5
 * - 1 <= target[i] <= 10^5
 *
 * ==================== 解题思路 ====================
 * 核心思想：贪心 + 差分思想
 * 
 * 关键观察：
 * 1. 第一个元素 target[0] 必须单独操作 target[0] 次
 * 2. 对于后续元素 target[i]：
 *    - 如果 target[i] > target[i-1]，需要额外操作 (target[i] - target[i-1]) 次
 *    - 如果 target[i] <= target[i-1]，不需要额外操作（可以复用之前的操作）
 * 3. 总操作次数 = target[0] + Σ max(0, target[i] - target[i-1])
 *
 * 类似问题：这题本质上是求差分数组中所有正数的和
 * 
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class MinNumberOperations {
    
    /**
     * 动态规划解法
     * 
     * 核心思想：
     * 1. 对于第一个元素，需要的操作次数就是 target[0]
     * 2. 对于后续元素，如果 target[i] > target[i-1]，则需要额外的操作次数
     * 3. 额外的操作次数为 target[i] - target[i-1]
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param target 目标数组
     * @return 最少操作次数
     */
    public int minNumberOperations(int[] target) {
        // dp 变量表示到达当前位置所需的最少操作次数
        int dp = target[0];
        
        // 从第二个元素开始遍历
        for (int i = 1; i < target.length; i++) {
            // 如果当前元素比前一个元素大，需要额外的操作次数
            if (target[i] > target[i - 1]) {
                dp += target[i] - target[i - 1];
            }
        }
        
        return dp;
    }
    
    /**
     * 贪心解法（等价于动态规划）
     * 
     * 核心思想：
     * 每个位置的操作次数等于它与前一个位置的差值（如果为正）
     * 
     * @param target 目标数组
     * @return 最少操作次数
     */
    public int minNumberOperationsGreedy(int[] target) {
        int result = target[0];
        
        for (int i = 1; i < target.length; i++) {
            result += Math.max(0, target[i] - target[i - 1]);
        }
        
        return result;
    }
}