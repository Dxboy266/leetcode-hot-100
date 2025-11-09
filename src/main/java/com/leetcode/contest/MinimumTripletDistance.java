package com.leetcode.contest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 力扣周赛题目：有效三元组的最小距离
 * 
 * 题目描述：
 * 给你一个整数数组 nums。
 * 
 * 如果满足 nums[i] == nums[j] == nums[k]，且 (i, j, k) 是 3 个 不同 下标，
 * 那么三元组 (i, j, k) 被称为 有效三元组。
 * 
 * 有效三元组 的 距离 被定义为 abs(i - j) + abs(j - k) + abs(k - i)，
 * 其中 abs(x) 表示 x 的 绝对值。
 * 
 * 返回一个整数，表示 有效三元组 的 最小 可能距离。如果不存在 有效三元组，返回 -1。
 * 
 * 示例 1：
 * 输入：nums = [1,1,1,1]
 * 输出：4
 * 解释：
 * 所有可能的三元组都是 (0,1,2), (0,1,3), (0,2,3), (1,2,3)。
 * 它们的距离都是 4。
 * 例如 (0,1,2)：abs(0-1) + abs(1-2) + abs(2-0) = 1 + 1 + 2 = 4
 * 
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：-1
 * 解释：
 * 不存在有效三元组。
 * 
 * 示例 3：
 * 输入：nums = [1,1,2,2,2]
 * 输出：6
 * 解释：
 * 有效三元组只有 (2,3,4)，因为只有这三个下标的值相同（都是2）。
 * 距离 = abs(2-3) + abs(3-4) + abs(4-2) = 1 + 1 + 2 = 4
 * 等等，让我重新计算...
 * 实际上对于连续的三个下标 i, i+1, i+2：
 * 距离 = abs(i-(i+1)) + abs((i+1)-(i+2)) + abs((i+2)-i) = 1 + 1 + 2 = 4
 * 
 * 提示：
 * - 3 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * 
 * 标签：数组、哈希表、贪心
 * 难度：中等
 * 公司：力扣周赛
 */
public class MinimumTripletDistance {
    
    /**
     * 方法1：暴力解法（用于理解题意）
     * 
     * 思路：
     * 1. 枚举所有可能的三元组 (i, j, k)，其中 i < j < k
     * 2. 检查 nums[i] == nums[j] == nums[k]
     * 3. 计算距离：abs(i-j) + abs(j-k) + abs(k-i)
     * 4. 维护最小距离
     * 
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     * 
     * 注意：这个方法会超时，仅用于理解题意！
     */
    public int minimumDistanceBruteForce(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        int distance = calculateDistance(i, j, k);
                        if (distance < minDistance) {
                            minDistance = distance;
                        }
                    }
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
    /**
     * 方法2：哈希表优化（推荐）
     * 
     * 思路：
     * 1. 使用哈希表记录每个值出现的所有下标
     * 2. 对于每个值，如果出现次数 >= 3，计算最小距离
     * 3. 关键观察：对于三个下标 i < j < k，距离公式可以简化
     *    距离 = abs(i-j) + abs(j-k) + abs(k-i)
     *         = (j-i) + (k-j) + (k-i)  （因为 i < j < k）
     *         = 2*(k-i)
     * 4. 所以要使距离最小，应该选择相邻的三个下标！
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * 提示：
     * - 使用 HashMap<Integer, List<Integer>> 存储每个值的下标列表
     * - 对于每个值，遍历其下标列表，找连续的三个下标
     * - 计算距离并更新最小值
     */
    public int minimumDistance(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0;i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList());
            list.add(i);
            map.put(nums[i], list);
        }

        AtomicInteger curMin = new AtomicInteger(Integer.MAX_VALUE);
        map.forEach((key, list) -> {
            if (list.size() >= 3) {
                for (int i = 0; i < list.size(); i++) {
                    int curListSize = list.size() - i;
                    if (curListSize >= 3) {
                        int distance = calculateDistance(list.get(i), list.get(i + 1), list.get(i + 2));
                        if (distance < curMin.get()) {
                            curMin.set(distance);
                        }
                    } else{
                        break;
                    }
                }
            }
        });
        return curMin.get() == Integer.MAX_VALUE ? -1 : curMin.get();
    }
    
    /**
     * 方法3：一次遍历优化（进阶）
     * 
     * 思路：
     * 1. 边遍历边维护每个值的最近三个下标
     * 2. 当某个值出现第三次时，立即计算距离
     * 3. 使用滑动窗口的思想，只保留最近的三个下标
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * 提示：
     * - 可以使用 HashMap<Integer, Deque<Integer>> 存储每个值的最近下标
     * - 当队列大小超过3时，移除最旧的下标
     */
    public int minimumDistanceOptimized(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int minDistance = Integer.MAX_VALUE;
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Deque<Integer> deque = map.getOrDefault(nums[i], new LinkedList<>());
            deque.addLast(i);
            if (deque.size() == 3) {
                // 当队列中有3个元素时，计算距离
                int distance = calculateDistance(
                    ((LinkedList<Integer>) deque).get(0), 
                    ((LinkedList<Integer>) deque).get(1), 
                    ((LinkedList<Integer>) deque).get(2));
                minDistance = Math.min(minDistance, distance);
            } else if (deque.size() > 3) {
                // 当队列大小超过3时，先计算当前3个元素的距离
                int distance = calculateDistance(
                    ((LinkedList<Integer>) deque).get(1), 
                    ((LinkedList<Integer>) deque).get(2), 
                    ((LinkedList<Integer>) deque).get(3));
                minDistance = Math.min(minDistance, distance);
                // 然后移除最旧的下标
                deque.removeFirst();
            }
            map.put(nums[i], deque);
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
    /**
     * 辅助方法：计算三元组的距离
     * 
     * @param i 第一个下标
     * @param j 第二个下标
     * @param k 第三个下标
     * @return 距离
     */
    private int calculateDistance(int i, int j, int k) {
        return Math.abs(i - j) + Math.abs(j - k) + Math.abs(k - i);
    }
}

