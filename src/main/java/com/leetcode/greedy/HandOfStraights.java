package com.leetcode.greedy;

import java.util.*;

/**
 * 846. 一手顺子
 * 难度：中等
 * 
 * 题目描述：
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize，
 * 并且由 groupSize 张连续的牌组成。
 * 
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌上的数值。
 * 如果她可能重新排列这些牌，返回 true；否则，返回 false。
 * 
 * 示例 1：
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 
 * 示例 2：
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 * 
 * 提示：
 * - 1 <= hand.length <= 10^4
 * - 0 <= hand[i] <= 10^9
 * - 1 <= groupSize <= hand.length
 * 
 * 相关标签：贪心、数组、哈希表、排序
 */
public class HandOfStraights {
    
    /**
     * 方法一：贪心 + TreeMap（推荐）⭐⭐⭐
     * 
     * 核心思想：
     * 1. 如果牌的总数不能被 groupSize 整除，直接返回 false
     * 2. 使用 TreeMap 统计每张牌的数量（自动排序）
     * 3. 贪心策略：每次从最小的牌开始，尝试组成一组连续的牌
     * 4. 如果能组成 hand.length / groupSize 组，返回 true
     * 
     * 时间复杂度：O(n log n) - TreeMap 插入和查找都是 O(log n)
     * 空间复杂度：O(n) - TreeMap 存储空间
     * 
     * @param hand 手中的牌
     * @param groupSize 每组的牌数
     * @return 是否能重新排列
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // 1. 边界条件：如果牌的总数不能被 groupSize 整除，无法分组
        if (hand.length % groupSize != 0) {
            return false;
        }
        
        // 2. 使用 TreeMap 统计每张牌的数量（TreeMap 会自动按 key 排序）
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int card : hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }
        
        // 3. 贪心：每次从最小的牌开始，尝试组成一组连续的牌
        while (!count.isEmpty()) {
            // 获取最小的牌
            int first = count.firstKey();
            
            // 尝试组成一组连续的牌：[first, first+1, ..., first+groupSize-1]
            for (int i = 0; i < groupSize; i++) {
                int card = first + i;
                
                // 如果这张牌不存在，无法组成连续的牌
                if (!count.containsKey(card)) {
                    return false;
                }
                
                // 使用这张牌，数量 -1
                count.put(card, count.get(card) - 1);
                
                // 如果这张牌用完了，从 TreeMap 中移除
                if (count.get(card) == 0) {
                    count.remove(card);
                }
            }
        }
        
        // 4. 如果所有牌都能成功分组，返回 true
        return true;
    }
    
    /**
     * 方法二：排序 + 哈希表
     * 
     * 核心思想：
     * 1. 先对数组排序
     * 2. 使用 HashMap 统计每张牌的数量
     * 3. 从小到大遍历，每次尝试组成一组连续的牌
     * 
     * 时间复杂度：O(n log n) - 排序的时间复杂度
     * 空间复杂度：O(n) - HashMap 存储空间
     * 
     * @param hand 手中的牌
     * @param groupSize 每组的牌数
     * @return 是否能重新排列
     */
    public boolean isNStraightHandSort(int[] hand, int groupSize) {
        // 1. 边界条件
        if (hand.length % groupSize != 0) {
            return false;
        }
        
        // 2. 排序
        Arrays.sort(hand);
        
        // 3. 使用 HashMap 统计每张牌的数量
        Map<Integer, Integer> count = new HashMap<>();
        for (int card : hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }
        
        // 4. 从小到大遍历，每次尝试组成一组连续的牌
        for (int card : hand) {
            // 如果这张牌已经用完了，跳过
            if (count.get(card) == 0) {
                continue;
            }
            
            // 尝试组成一组连续的牌：[card, card+1, ..., card+groupSize-1]
            for (int i = 0; i < groupSize; i++) {
                int current = card + i;
                
                // 如果这张牌不存在或已经用完，无法组成连续的牌
                if (!count.containsKey(current) || count.get(current) == 0) {
                    return false;
                }
                
                // 使用这张牌，数量 -1
                count.put(current, count.get(current) - 1);
            }
        }
        
        return true;
    }
}

