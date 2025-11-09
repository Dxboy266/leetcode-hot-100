package com.leetcode.twopointers;

/**
 * 11. 盛最多水的容器（中等）
 * 
 * 题目描述：
 * 给定一个长度为 n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 返回容器可以储存的最大水量。
 * 
 * 说明：你不能倾斜容器。
 * 
 * 示例 1：
 * 输入：height = [1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * 
 * 提示：
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 * 
 * 标签：贪心、数组、双指针
 * 难度：中等
 * 公司：拼多多（高频）、字节跳动、腾讯
 * 
 * 解题思路：
 * 1. 双指针（对撞指针）
 *    - 初始化：left = 0, right = n - 1
 *    - 计算当前面积：area = min(height[left], height[right]) * (right - left)
 *    - 更新最大面积
 *    - 移动指针：
 *      - 如果 height[left] < height[right]，left++（移动较短的边）
 *      - 否则，right--
 *    - 为什么移动较短的边？
 *      - 因为面积由较短的边决定
 *      - 移动较长的边，宽度减小，高度不会增加，面积一定减小
 *      - 移动较短的边，宽度减小，但高度可能增加，面积可能增加
 * 
 * 2. 时间复杂度：O(n)
 *    - 双指针遍历一次数组
 * 
 * 3. 空间复杂度：O(1)
 *    - 只使用常数个变量
 * 
 * 关键点：
 * - 贪心思想：每次移动较短的边
 * - 面积 = min(height[left], height[right]) * (right - left)
 * - 双指针从两端向中间移动
 */
public class MaxArea {
    
    /**
     * 方法1：双指针（对撞指针）
     * 
     * 思路：
     * 1. 初始化 left = 0, right = n - 1
     * 2. 计算当前面积，更新最大面积
     * 3. 移动较短的边
     * 4. 重复 2-3，直到 left >= right
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
    
    /**
     * 方法2：暴力解法（用于对比）
     * 
     * 思路：
     * 枚举所有可能的两条线，计算面积，取最大值
     * 
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}

