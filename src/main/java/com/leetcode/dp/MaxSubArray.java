package com.leetcode.dp;

/**
 * 53. 最大子数组和 (Maximum Subarray)
 *
 * 题目链接：https://leetcode.cn/problems/maximum-subarray/
 * 难度：中等
 * 标签：数组、分治、动态规划、前缀和
 *
 * ==================== 题目描述 ====================
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 提示：
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * ==================== 解题提示 ====================
 * 常见写法（建议都掌握）：
 * 1) Kadane 算法（贪心 + DP 一维，O(1) 空间）⭐ 推荐
 * 2) 显式 DP 数组：dp[i] 表示以 i 结尾的最大子数组和
 * 3) 前缀和（维护当前最小前缀）
 * 4) 分治（求跨中点的最大子段和）
 * 5) 线段树（维护四种信息：总和、前缀最大、后缀最大、区间最大）
 *
 * @Author Dxboy266
 * @Date 2025-10-28
 */
public class MaxSubArray {

    /**
     * 解法一：Kadane 算法（O(1) 空间）⭐ 推荐
     * 思路：遍历过程中维护“以当前元素为结尾的最大子数组和”和“全局最大值”
     * @param nums 数组
     * @return 最大子数组和
     */
    public int maxSubArrayKadane(int[] nums) {
        // curr = Math.max(nums[i], curr + nums[i]); ans = Math.max(ans, curr)
        int curr = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    /**
     * 解法二：DP 数组
     * 定义：dp[i] = 以 i 结尾的最大子数组和
     * 转移：dp[i] = Math.max(nums[i], dp[i-1] + nums[i])
     * 答案：max(dp)
     * @param nums 数组
     * @return 最大子数组和
     */
    public int maxSubArrayDP(int[] nums) {
        int[] dp = new int[nums.length];
        // 以第 0 个元素结尾的最大子数组和就是它本身
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 解法三：前缀和
     * 思路：maxSum = max(prefix[i] - minPrefix[0..i-1])，遍历维护最小前缀
     * @param nums 数组
     * @return 最大子数组和
     */
    public int maxSubArrayPrefixSum(int[] nums) {
        // 在线前缀：curPrefix 为当前前缀和，minPrefix 为历史最小前缀
        int curPrefix = 0;
        int minPrefix = 0; // 空前缀为 0，允许从索引 0 开始
        int maxSum = Integer.MIN_VALUE;
        for (int x : nums) {
            curPrefix += x;
            maxSum = Math.max(maxSum, curPrefix - minPrefix);
            minPrefix = Math.min(minPrefix, curPrefix);
        }
        return maxSum;
    }

    /**
     * 解法四：分治
     * 思路：区间最大子段和 = max(左区间最大, 右区间最大, 跨中点最大)
     * 跨中点最大 = 左侧后缀最大 + 右侧前缀最大
     * @param nums 数组
     * @return 最大子数组和
     */
    public int maxSubArrayDivideAndConquer(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    // 分治：返回 [l, r] 区间的最大子数组和
    private int divideAndConquer(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;
        int leftBest = divideAndConquer(nums, l, mid);
        int rightBest = divideAndConquer(nums, mid + 1, r);
        int crossBest = maxCrossingSum(nums, l, mid, r);
        return Math.max(Math.max(leftBest, rightBest), crossBest);
    }

    // 计算跨越 mid 的最大子数组和：左侧取后缀最大 + 右侧取前缀最大
    private int maxCrossingSum(int[] nums, int l, int mid, int r) {
        int leftMaxSuffix = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= l; i--) {
            sum += nums[i];
            leftMaxSuffix = Math.max(leftMaxSuffix, sum);
        }
        int rightMaxPrefix = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= r; i++) {
            sum += nums[i];
            rightMaxPrefix = Math.max(rightMaxPrefix, sum);
        }
        return leftMaxSuffix + rightMaxPrefix;
    }

    /**
     * 解法五：线段树（进阶）
     *
     * 维护每个区间的四个量（经典模板）：
     * - sum：区间总和
     * - prefixMax：区间的前缀最大子数组和
     * - suffixMax：区间的后缀最大子数组和
     * - best：区间内的最大子数组和
     *
     * 合并两个子区间 L、R：
     * - sum = L.sum + R.sum
     * - prefixMax = max(L.prefixMax, L.sum + R.prefixMax)
     * - suffixMax = max(R.suffixMax, R.sum + L.suffixMax)
     * - best = max(L.best, R.best, L.suffixMax + R.prefixMax)
     *
     * 整体答案即为 build(0, n-1) 的 best。
     */
    public int maxSubArraySegmentTree(int[] nums) {
        return build(nums, 0, nums.length - 1).best;
    }

    // 线段树节点维护的四个量
    private static class NodeInfo {
        int sum;        // 区间总和
        int prefixMax;  // 区间前缀最大和
        int suffixMax;  // 区间后缀最大和
        int best;       // 区间最大子数组和
        NodeInfo(int sum, int prefixMax, int suffixMax, int best) {
            this.sum = sum;
            this.prefixMax = prefixMax;
            this.suffixMax = suffixMax;
            this.best = best;
        }
    }

    // 自底向上的分治构建（与线段树思想等价）
    private NodeInfo build(int[] nums, int l, int r) {
        if (l == r) {
            int v = nums[l];
            return new NodeInfo(v, v, v, v);
        }
        int mid = l + (r - l) / 2;
        NodeInfo left = build(nums, l, mid);
        NodeInfo right = build(nums, mid + 1, r);
        return pushUp(left, right);
    }

    // 合并左右子区间的信息
    private NodeInfo pushUp(NodeInfo left, NodeInfo right) {
        int sum = left.sum + right.sum;
        int prefixMax = Math.max(left.prefixMax, left.sum + right.prefixMax);
        int suffixMax = Math.max(right.suffixMax, right.sum + left.suffixMax);
        int best = Math.max(Math.max(left.best, right.best), left.suffixMax + right.prefixMax);
        return new NodeInfo(sum, prefixMax, suffixMax, best);
    }
}
