package com.leetcode.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和（中等）
 * 
 * 题目描述：
 * 给你一个整数数组 nums，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0。
 * 
 * 请你返回所有和为 0 且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * 
 * 提示：
 * - 3 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 * 
 * 标签：数组、双指针、排序
 * 难度：中等
 * 公司：字节跳动（高频）、拼多多、阿里巴巴
 * 
 * 解题思路：
 * 1. 排序 + 双指针
 *    - 先对数组排序
 *    - 固定一个数 nums[i]，然后用双指针在 [i+1, n-1] 范围内找两个数，使得三数之和为 0
 *    - 关键：去重！
 *      - 对于 nums[i]，如果 nums[i] == nums[i-1]，跳过
 *      - 对于 left 和 right，找到答案后也要跳过重复的数
 * 
 * 2. 时间复杂度：O(n²)
 *    - 排序：O(nlogn)
 *    - 外层循环：O(n)
 *    - 内层双指针：O(n)
 *    - 总时间：O(n²)
 * 
 * 3. 空间复杂度：O(logn)
 *    - 排序的栈空间：O(logn)
 *    - 结果列表不计入空间复杂度
 * 
 * 关键点：
 * - 排序是前提
 * - 去重是难点（三个位置都要去重）
 * - 剪枝优化：如果 nums[i] > 0，后面不可能有三数之和为 0
 */
public class ThreeSum {
    
    /**
     * 方法1：排序 + 双指针（推荐）
     * 
     * 思路：
     * 1. 先对数组排序
     * 2. 固定第一个数 nums[i]
     * 3. 用双指针在 [i+1, n-1] 范围内找两个数
     * 4. 如果三数之和 == 0，记录结果
     * 5. 如果三数之和 < 0，left++
     * 6. 如果三数之和 > 0，right--
     * 7. 注意去重！
     * 
     * @param nums 整数数组
     * @return 所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 边界条件检查
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        
        // 排序数组，为双指针做准备
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        // 遍历数组，固定第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 剪枝优化1：如果当前数字大于0，则三数之和一定大于0，结束循环
            if (nums[i] > 0) {
                break;
            }
            
            // 去重：跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 剪枝优化2：如果最小的三数之和大于0，结束循环
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            
            // 剪枝优化3：如果最大的三数之和小于0，跳过当前循环
            if (nums[i] + nums[nums.length - 2] + nums[nums.length - 1] < 0) {
                continue;
            }
            
            // 双指针查找另外两个数
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    // 找到一个三元组
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳过重复的left值
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的right值
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移动指针继续查找
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 和小于0，需要增大，移动左指针
                    left++;
                } else {
                    // 和大于0，需要减小，移动右指针
                    right--;
                }
            }
        }
        return res;
    }
    
    /**
     * 方法2：排序 + 双指针（带剪枝优化）
     * 
     * 优化点：
     * 1. 如果 nums[i] > 0，后面不可能有三数之和为 0，直接 break
     * 2. 如果 nums[i] + nums[i+1] + nums[i+2] > 0，后面不可能有三数之和为 0，直接 break
     * 3. 如果 nums[i] + nums[n-2] + nums[n-1] < 0，当前 i 不可能有答案，continue
     * 
     * @param nums 整数数组
     * @return 所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSumOptimized(int[] nums) {
        // 边界条件检查
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        
        // 排序数组，为双指针做准备
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        // 遍历数组，固定第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 剪枝优化1：如果当前数字大于0，则三数之和一定大于0，结束循环
            if (nums[i] > 0) {
                break;
            }
            
            // 去重：跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 剪枝优化2：如果最小的三数之和大于0，结束循环
            if (i + 2 < nums.length && nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            
            // 剪枝优化3：如果最大的三数之和小于0，跳过当前循环
            if (nums.length - 2 >= 0 && nums[i] + nums[nums.length - 2] + nums[nums.length - 1] < 0) {
                continue;
            }
            
            // 双指针查找另外两个数
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    // 找到一个三元组
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳过重复的left值
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的right值
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移动指针继续查找
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 和小于0，需要增大，移动左指针
                    left++;
                } else {
                    // 和大于0，需要减小，移动右指针
                    right--;
                }
            }
        }
        return res;
    }
}

