package com.leetcode.array;

import java.util.HashMap;

/**
 * 560. 和为K的子数组
 * 
 * 题目链接：https://leetcode.cn/problems/subarray-sum-equals-k/
 * 难度：中等
 * 标签：数组、哈希表、前缀和
 * 
 * ==================== 题目描述 ====================
 * 给你一个整数数组 nums 和一个整数 k，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * 
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 解释：子数组 [1,1] 和 [1,1] 的和为 2
 * 
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 解释：子数组 [1,2] 和 [3] 的和为 3
 * 
 * 提示：
 * - 1 <= nums.length <= 2 * 10^4
 * - -1000 <= nums[i] <= 1000
 * - -10^7 <= k <= 10^7
 * 
 * ==================== 解题思路 ====================
 * 
 * 方法一：暴力解法
 * - 枚举所有可能的子数组
 * - 计算每个子数组的和，统计等于k的个数
 * - 时间复杂度：O(n²)，空间复杂度：O(1)
 * - 优点：思路直观，易于理解
 * - 缺点：效率低，大数据量会超时
 * 
 * 方法二：前缀和 + 哈希表（推荐）★★★
 * - 利用前缀和的思想：sum[i,j] = prefixSum[j] - prefixSum[i-1]
 * - 如果 sum[i,j] = k，则 prefixSum[j] - prefixSum[i-1] = k
 * - 即：prefixSum[i-1] = prefixSum[j] - k
 * - 用HashMap记录每个前缀和出现的次数
 * - 时间复杂度：O(n)，空间复杂度：O(n)
 * - 优点：效率高，满足大数据量要求
 * - 缺点：需要理解前缀和的概念
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 前缀和的应用：
 *    - 快速计算任意子数组的和
 *    - 将子数组和问题转化为两数之差问题
 * 
 * 2. 哈希表的应用：
 *    - 记录前缀和及其出现次数
 *    - 快速查找是否存在某个前缀和
 * 
 * 3. 算法优化思路：
 *    - 从暴力 O(n²) 优化到 O(n)
 *    - 通过数学变换将问题转化为已知问题
 * 
 * 4. 易错点：
 *    - 需要初始化 prefixSum[0] = 0 的情况
 *    - 注意数组元素可能为负数
 *    - 子数组是连续的，不是子序列
 * 
 * 5. 相关题目：
 *    - 1. 两数之和（基础）
 *    - 523. 连续的子数组和
 *    - 974. 和可被K整除的子数组
 * 
 * @Author Dxboy266
 * @Date 2025-10-17
 */
public class SubarraySumEqualsK {
    
    /**
     * 方法一：暴力解法
     * 时间复杂度：O(n²) - 两层嵌套循环
     * 空间复杂度：O(1) - 只使用常数空间
     * 
     * 思路：
     * 1. 枚举所有可能的子数组 [i, j]
     * 2. 计算子数组的和
     * 3. 统计和等于k的子数组个数
     */
    public int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        // 1. 外层循环：子数组的起始位置 i
        for (int i = 0; i < nums.length; i++) {
            // 2. 内层循环：子数组的结束位置 j (从 i 开始)
            if (nums[i] == k) {
                count++;
            }
            int currentSum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                // 3. 计算子数组 [i, j] 的和
                currentSum += nums[j];
                // 4. 如果和等于k，计数器+1
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * 方法二：前缀和 + 哈希表（推荐）★★★
     * 时间复杂度：O(n) - 只需遍历一次数组
     * 空间复杂度：O(n) - HashMap存储前缀和
     * 
     * 思路：
     * 1. 计算前缀和数组
     * 2. 利用公式：sum[i,j] = prefixSum[j] - prefixSum[i-1]
     * 3. 如果 sum[i,j] = k，则 prefixSum[i-1] = prefixSum[j] - k
     * 4. 用HashMap记录每个前缀和出现的次数
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;  // 当前前缀和
        // 1. 创建HashMap：key是前缀和，value是出现次数
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 2. 初始化：prefixSum[0] = 0，出现次数为1
        countMap.put(0, 1);
        
        for (int num : nums) {
            // 3. 计算当前前缀和
            prefixSum += num;
            // 4. 查找是否存在 prefixSum - k 的前缀和
            if (countMap.containsKey(prefixSum - k)) {
                count += countMap.get(prefixSum - k);
            }
            // 5. 将当前前缀和加入HashMap
            countMap.put(prefixSum, countMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
