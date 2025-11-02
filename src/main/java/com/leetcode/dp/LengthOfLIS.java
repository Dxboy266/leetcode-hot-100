package com.leetcode.dp;

import java.util.Arrays;

/**
 * 300. 最长递增子序列 (Longest Increasing Subsequence)
 *
 * 题目链接：https://leetcode.cn/problems/longest-increasing-subsequence/
 * 难度：中等
 * 标签：数组、二分查找、动态规划
 *
 * ==================== 题目描述 ====================
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * - 1 <= nums.length <= 2500
 * - -10^4 <= nums[i] <= 10^4
 *
 * ==================== 解题提示 ====================
 * 常见写法（建议都掌握）：
 * 1) DP 数组：dp[i] 表示以 nums[i] 结尾的最长递增子序列长度，O(n²)
 * 2) 贪心 + 二分：维护一个 tails 数组，tails[i] 表示长度为 i+1 的递增子序列的最小末尾元素，O(n log n) ⭐ 推荐
 * 3) 记忆化搜索：自顶向下的 DFS + memo
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class LengthOfLIS {

    /**
     * 解法一：动态规划 O(n²)
     * 定义：dp[i] = 以 nums[i] 结尾的最长递增子序列长度
     * 转移：dp[i] = max(dp[j] + 1)，其中 j < i 且 nums[j] < nums[i]
     * 初始：dp[i] = 1（每个元素自己构成长度为 1 的子序列）
     * 答案：max(dp)
     *
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

       return maxLen;
    }


    /**
     * 解法二：贪心 + 二分 O(n log n) ⭐ 推荐
     * 思路：维护一个 tails 数组，tails[i] 表示长度为 i+1 的递增子序列的最小末尾元素
     * - 遍历 nums[i]：
     *   - 如果 nums[i] > tails 的最后一个元素，直接追加到 tails 末尾
     *   - 否则，二分查找 tails 中第一个 >= nums[i] 的位置，替换它
     * - tails 的长度即为答案
     *
     * 核心思想：对于相同长度的递增子序列，末尾元素越小，越有利于后续元素的追加
     *
     * 时间复杂度：O(n log n)
     * 空间复杂度：O(n)
     *
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        // tails[i] 表示长度为 i+1 的递增子序列的最小末尾元素
        int[] tails = new int[nums.length];
        int size = 0;  // tails 数组的有效长度
        
        for (int num : nums) {
            // 二分查找：在 tails[0...size-1] 中找第一个 >= num 的位置
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            // left 就是第一个 >= num 的位置
            tails[left] = num;
            
            // 如果 left == size，说明 num 比所有已有的末尾元素都大，追加到末尾
            if (left == size) {
                size++;
            }
        }
        
        return size;
    }

    /**
     * 解法三：记忆化搜索
     * 思路：dfs(i) 表示从 i 开始能形成的最长递增子序列长度
     * 转移：dfs(i) = max(dfs(j) + 1)，其中 j > i 且 nums[j] > nums[i]
     *
     * 时间复杂度：O(n²)
     * 空间复杂度：O(n) - 递归栈 + memo 数组
     *
     * @param nums 数组
     * @return 最长递增子序列长度
     */
    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];  // memo[i] 表示从 i 开始的最长递增子序列长度
        Arrays.fill(memo, -1);
        
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, dfs(nums, i, memo));
        }
        
        return maxLen;
    }
    
    private int dfs(int[] nums, int start, int[] memo) {
        if (memo[start] != -1) {
            return memo[start];
        }
        
        // 至少包含自己
        int maxLen = 1;
        
        // 尝试从 start 之后的每个位置继续递增
        for (int j = start + 1; j < nums.length; j++) {
            if (nums[j] > nums[start]) {
                maxLen = Math.max(maxLen, dfs(nums, j, memo) + 1);
            }
        }
        
        memo[start] = maxLen;
        return maxLen;
    }
}

