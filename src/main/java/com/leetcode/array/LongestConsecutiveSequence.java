package com.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 128. 最长连续序列
 * 
 * 题目链接：https://leetcode.cn/problems/longest-consecutive-sequence/
 * 难度：中等
 * 标签：数组、哈希表、并查集
 * 
 * ==================== 题目描述 ====================
 * 给定一个未排序的整数数组 nums，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 
 * 提示：
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * 
 * ==================== 解题思路 ====================
 * 
 * 方法一：排序 + 一次遍历
 * - 先对数组排序
 * - 一次遍历找出最长连续序列
 * - 注意处理重复元素
 * - 时间复杂度：O(n log n)，空间复杂度：O(1)
 * - 优点：思路直观，易于理解
 * - 缺点：时间复杂度不满足题目要求的 O(n)
 * 
 * 方法二：哈希表（推荐）★★★
 * - 使用 HashSet 存储所有数字
 * - 对于每个数字，只在它是连续序列的起点时才开始计数
 * - 如何判断起点？如果 num-1 不在集合中，说明 num 是起点
 * - 从起点开始，不断查找 num+1, num+2, ... 是否在集合中
 * - 时间复杂度：O(n)，空间复杂度：O(n)
 * - 优点：满足题目要求的 O(n) 时间复杂度
 * - 缺点：需要额外的 HashSet 空间
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 哈希表的应用：
 *    - 快速判断元素是否存在（O(1) 时间）
 *    - 避免重复计算的关键
 * 
 * 2. 算法优化技巧：
 *    - 只从序列的起点开始计数，避免重复计算
 *    - 每个数字最多被访问两次（一次判断起点，一次计数）
 * 
 * 3. 易错点：
 *    - 要处理重复元素（排序方法）
 *    - 要避免对每个数字都开始计数（会导致 O(n²)）
 *    - 空数组的处理
 * 
 * 4. 相关题目：
 *    - 298. 二叉树最长连续序列
 *    - 549. 二叉树中最长的连续序列
 * 
 * @Author Dxboy266
 * @Date 2025-10-17
 */
public class LongestConsecutiveSequence {
    
    /**
     * 方法一：排序 + 一次遍历
     * 时间复杂度：O(n log n) - 排序的时间复杂度
     * 空间复杂度：O(1) - 不考虑排序的额外空间
     * 
     * 思路：
     * 1. 先对数组排序，这样连续的数字会相邻
     * 2. 一次遍历，记录当前连续序列长度
     * 3. 注意处理重复元素
     */
    public int longestConsecutiveSort(int[] nums) {
        // 1. 处理边界情况：空数组
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 2. 对数组排序
        Arrays.sort(nums);
        
        // 3. 一次遍历，维护当前连续序列长度和最大长度
        int maxLength = 1;      // 最长连续序列长度
        int currentLength = 1;  // 当前连续序列长度
        
        for (int i = 1; i < nums.length; i++) {
            // 4. 如果当前数字等于前一个数字（重复），跳过
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 5. 如果当前数字 = 前一个数字 + 1，连续序列长度 +1
            if (nums[i] == nums[i - 1] + 1) {
                currentLength++;
            } else {
                // 6. 否则，更新最大长度，重新开始计数
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }
        
        // 7. 最后还需要更新一次（处理最后一段连续序列）
        return Math.max(maxLength, currentLength);
    }
    
    /**
     * 方法二：哈希表（推荐）★★★
     * 时间复杂度：O(n) - 每个数字最多被访问两次
     * 空间复杂度：O(n) - HashSet 存储所有数字
     * 
     * 思路：
     * 1. 将所有数字放入 HashSet
     * 2. 遍历每个数字，判断它是否是连续序列的起点
     * 3. 只在起点开始计数，避免重复计算
     */
    public int longestConsecutive(int[] nums) {
        // 1. 处理边界情况：空数组
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 2. 将所有数字放入 HashSet
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        // 3. 遍历 HashSet 中的每个数字 num：
        //    - 如果 num-1 不在集合中，说明 num 是某个连续序列的起点
        //    - 从 num 开始，不断查找 num+1, num+2, ... 直到不连续
        //    - 记录这个序列的长度
        int res = 1;
        for (Integer num : hashSet) {
            if (!hashSet.contains(num - 1)) {
                int count = 1;
                while (hashSet.contains(num + 1)) {
                    count++;
                    num++;
                }
                res = Math.max(res, count);
            }
        }

        // 4. 返回最长的序列长度
        return res;
    }
}

