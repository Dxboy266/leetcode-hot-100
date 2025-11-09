package com.leetcode.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 46. 全排列
 * 
 * 题目描述：
 * 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。
 * 你可以按任意顺序返回答案。
 * 
 * 示例：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 
 * 输入：nums = [1]
 * 输出：[[1]]
 * 
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数互不相同
 * 
 * ==================== 解题思路 ====================
 * 
 * 核心：回溯算法
 * 
 * 算法步骤：
 * 1. 使用递归和回溯的方法生成所有可能的排列
 * 2. 用一个布尔数组记录每个元素是否已被使用
 * 3. 当路径长度等于数组长度时，说明找到了一个完整排列
 * 4. 回溯时需要撤销选择，恢复状态
 * 
 * 时间复杂度：O(n × n!)，其中 n 是数组的长度
 * 空间复杂度：O(n)，递归栈的深度
 * 
 * @Author 
 * @Date 2025-11-06
 */
public class Permutations {

    // 存储所有排列结果
    List<List<Integer>> res = new LinkedList<>();

    /**
     * 主解法：回溯算法
     *
     * 核心思想：
     * - 通过递归遍历所有可能的选择
     * - 使用 visited 数组记录哪些元素已经被使用
     * - 当路径达到数组长度时，将当前排列加入结果集
     * - 回溯时撤销选择，尝试其他可能性
     *
     * @param nums 不含重复数字的数组
     * @return 所有可能的全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        // 存储当前正在构建的排列
        List<Integer> list = new LinkedList<>();
        // 记录每个元素是否已被使用
        boolean[] visited = new boolean[nums.length];
        dfs(list, visited, nums);
        return res;
    }

    private void dfs(List<Integer> list, boolean[] visited, int[] nums) {
        // 递归终止条件：当前排列已经包含了所有元素
        if (list.size() == nums.length) {
            // 将当前排列加入结果集
            res.add(new LinkedList<>(list));
            return;
        }
        // 遍历所有元素，尝试每一种可能的选择
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                // 如果当前元素已经被使用，则跳过
                continue;
            }
            // 做选择
            // 将元素加入当前排列
            list.add(nums[i]);
            // 标记为已使用
            visited[i] = true;

            // 进入下一层决策树
            dfs(list, visited, nums);

            // 撤销选择（回溯）
            // 取消标记
            visited[i] = false;

            // 移除最后一个元素
            list.remove(list.size() - 1);
        }
    }
}