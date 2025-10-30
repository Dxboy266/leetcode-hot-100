package com.leetcode.dp;

/**
 * 198. 打家劫舍 (House Robber)
 *
 * 题目链接：https://leetcode.cn/problems/house-robber/
 * 难度：中等
 * 标签：动态规划、数组
 *
 * ==================== 题目描述 ====================
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，
 * 一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1)，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 400
 *
 * ==================== 解题思路 ====================
 * 方法一：动态规划（数组）
 * 1. 定义状态：dp[i] 表示偷窃前 i+1 个房屋（即从索引 0 到 i 的房屋）能获得的最大金额
 * 2. 状态转移方程：dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 *    - dp[i-1] 表示不偷第 i 个房屋，直接用前 i-1 个房屋的最大金额
 *    - dp[i-2] + nums[i] 表示偷第 i 个房屋，则不能偷第 i-1 个房屋，所以用前 i-2 个房屋的最大金额加上当前房屋金额
 * 3. 初始条件：
 *    - dp[0] = nums[0] （只有一个房屋时，只能偷它）
 *    - dp[1] = max(nums[0], nums[1]) （有两个房屋时，偷其中金额较大的）
 * 4. 返回值：dp[n-1] （表示考虑完所有房屋后的最大金额）
 *
 * 方法二：空间优化（滚动变量）
 * 由于状态转移方程中每个状态只依赖于前两个状态，可以使用两个变量代替整个数组
 * - dpI1 表示 dp[i-2]
 * - dpI2 表示 dp[i-1]
 * - max 表示当前 dp[i]
 *
 * 方法三：自顶向下（记忆化搜索）
 * 使用递归的方式，从最后一个房屋开始思考，通过备忘录避免重复计算
 *
 * ==================== 复杂度分析 ====================
 * 方法一（动态规划）：
 * - 时间复杂度：O(n) - 需要遍历一次数组
 * - 空间复杂度：O(n) - 需要一个长度为 n 的 dp 数组
 *
 * 方法二（空间优化）：
 * - 时间复杂度：O(n) - 需要遍历一次数组
 * - 空间复杂度：O(1) - 只使用了常数个额外变量
 *
 * 方法三（记忆化搜索）：
 * - 时间复杂度：O(n) - 每个状态只计算一次
 * - 空间复杂度：O(n) - 递归栈和备忘录数组的空间
 *
 * @Author Dxboy266
 * @Date 2025-10-30
 */
public class Rob {

    /**
     * 解法一：动态规划（数组）
     * @param nums 每个房屋存放金额的数组
     * @return 能够偷窃到的最高金额
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp  = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int max = dp[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 解法二：空间优化（滚动变量）
     * @param nums 每个房屋存放金额的数组
     * @return 能够偷窃到的最高金额
     */
    public int robOptimized(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int dpI1 = nums[0], dpI2 = Math.max(nums[0], nums[1]);
        int max = dpI2;
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(dpI2, dpI1 + nums[i]);
            dpI1 = dpI2;
            dpI2 = max;
        }
        return max;
    }
    
    /**
     * 解法三：自顶向下（记忆化搜索）
     * @param nums 每个房屋存放金额的数组
     * @return 能够偷窃到的最高金额
     */
    public int robMemoization(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        // 初始化备忘录数组
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = -1;  // -1 表示尚未计算
        }
        
        return robHelper(nums, nums.length - 1, memo);
    }
    
    /**
     * 递归辅助函数
     * @param nums 房屋金额数组
     * @param i 当前考虑的房屋索引
     * @param memo 备忘录数组
     * @return 考虑前i+1个房屋能偷到的最大金额
     */
    private int robHelper(int[] nums, int i, int[] memo) {
        // 边界条件
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return nums[0];
        }
        if (i == 1) {
            return Math.max(nums[0], nums[1]);
        }
        
        // 如果已经计算过，直接返回结果
        if (memo[i] != -1) {
            return memo[i];
        }
        
        // 计算并存储结果
        // 选择1：不偷第i个房屋，结果为前i-1个房屋的最大金额
        // 选择2：偷第i个房屋，结果为前i-2个房屋的最大金额加上第i个房屋的金额
        memo[i] = Math.max(robHelper(nums, i - 1, memo), 
                          robHelper(nums, i - 2, memo) + nums[i]);
        return memo[i];
    }
}