package com.leetcode.twopointers;

/**
 * 42. 接雨水（困难）
 * 
 * 题目描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 
 * 提示：
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 * 
 * 标签：栈、数组、双指针、动态规划、单调栈
 * 难度：困难
 * 公司：字节跳动（必考）、拼多多、阿里巴巴、腾讯
 * 
 * 解题思路：
 * 
 * 方法1：双指针（推荐，最优解）
 * - 核心思想：对于位置 i，能接的雨水 = min(左边最高, 右边最高) - height[i]
 * - 双指针优化：
 *   - left 指针从左往右，right 指针从右往左
 *   - 维护 leftMax 和 rightMax
 *   - 如果 height[left] < height[right]：
 *     - 说明右边一定有更高的柱子，left 位置的水量由 leftMax 决定
 *     - 计算 left 位置的水量，left++
 *   - 否则：
 *     - 说明左边一定有更高的柱子，right 位置的水量由 rightMax 决定
 *     - 计算 right 位置的水量，right--
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(1)
 * 
 * 方法2：动态规划
 * - 预处理：
 *   - leftMax[i] = max(height[0..i])
 *   - rightMax[i] = max(height[i..n-1])
 * - 计算：water[i] = min(leftMax[i], rightMax[i]) - height[i]
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(n)
 * 
 * 方法3：单调栈（加分项）
 * - 维护一个单调递减栈
 * - 当遇到比栈顶高的柱子时，说明可以接水
 * - 计算凹槽的水量
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(n)
 * 
 * 关键点：
 * - 理解"木桶效应"：水量由较短的边决定
 * - 双指针解法最优（时间 O(n)，空间 O(1)）
 * - 单调栈解法是加分项（面试时可以提到）
 */
public class Trap {
    
    /**
     * 方法1：双指针（推荐，最优解）
     *
     * 思路：
     * 1. 初始化 left = 0, right = n - 1
     * 2. 维护 leftMax 和 rightMax
     * 3. 如果 height[left] < height[right]：
     *    - left 位置的水量由 leftMax 决定
     *    - 计算水量，left++
     * 4. 否则：
     *    - right 位置的水量由 rightMax 决定
     *    - 计算水量，right--
     * 5. 重复 3-4，直到 left >= right
     *
     * @param height 高度数组
     * @return 能接的雨水总量
     */
    public int trap(int[] height) {
        // 边界条件检查
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        // 双指针从两端向中间移动
        while (left < right) {
            // 更新左右两侧的最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 核心逻辑：移动较短的一边
            if (height[left] < height[right]) {
                // 左边较短，left 位置的水量由 leftMax 决定
                // 因为右边一定有更高的柱子（height[right] >= height[left]）
                water += leftMax - height[left];
                left++;
            } else {
                // 右边较短或相等，right 位置的水量由 rightMax 决定
                // 因为左边一定有更高或相等的柱子
                water += rightMax - height[right];
                right--;
            }
        }

        return water;
    }
    
    /**
     * 方法2：动态规划
     *
     * 思路：
     * 1. 预处理 leftMax[i] 和 rightMax[i]
     * 2. 对于每个位置 i，计算 min(leftMax[i], rightMax[i]) - height[i]
     * 3. 累加所有位置的水量
     *
     * @param height 高度数组
     * @return 能接的雨水总量
     */
    public int trapDP(int[] height) {
        // 边界条件检查
        if (height == null || height.length < 3) {
            return 0;
        }

        int n = height.length;

        // leftMax[i] 表示 [0..i] 范围内的最大高度
        int[] leftMax = new int[n];
        // rightMax[i] 表示 [i..n-1] 范围内的最大高度
        int[] rightMax = new int[n];

        // 预处理 leftMax
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 预处理 rightMax
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算每个位置的水量
        int water = 0;
        for (int i = 0; i < n; i++) {
            // 当前位置能接的水 = min(左边最高, 右边最高) - 当前高度
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    /**
     * 方法3：单调栈（加分项）
     *
     * 思路：
     * 1. 维护一个单调递减栈（存储索引）
     * 2. 当遇到比栈顶高的柱子时：
     *    - 弹出栈顶（凹槽底部）
     *    - 计算凹槽的水量：
     *      - 宽度 = 当前位置 - 栈顶 - 1
     *      - 高度 = min(当前高度, 栈顶高度) - 凹槽底部高度
     *      - 水量 = 宽度 * 高度
     * 3. 将当前位置入栈
     *
     * @param height 高度数组
     * @return 能接的雨水总量
     */
    public int trapMonotonicStack(int[] height) {
        // 边界条件检查
        if (height == null || height.length < 3) {
            return 0;
        }

        int water = 0;
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        // 遍历每个柱子
        for (int i = 0; i < height.length; i++) {
            // 当前柱子高度大于栈顶柱子高度时，说明可以接水
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 弹出栈顶（凹槽底部）
                int bottom = stack.pop();

                // 如果栈为空，说明左边没有更高的柱子，无法接水
                if (stack.isEmpty()) {
                    break;
                }

                // 计算凹槽的宽度和高度
                int left = stack.peek();  // 左边界
                int width = i - left - 1;  // 宽度
                int h = Math.min(height[i], height[left]) - height[bottom];  // 高度

                // 累加水量
                water += width * h;
            }

            // 将当前位置入栈
            stack.push(i);
        }

        return water;
    }
}

