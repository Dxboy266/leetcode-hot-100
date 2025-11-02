package com.leetcode.dp;

import java.util.Arrays;

/**
 * 322. 零钱兑换 (Coin Change)
 *
 * 题目链接：https://leetcode.cn/problems/coin-change/
 * 难度：中等
 * 标签：广度优先搜索、数组、动态规划
 *
 * ==================== 题目描述 ====================
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 提示：
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 2^31 - 1
 * - 0 <= amount <= 10^4
 *
 * ==================== 解题提示 ====================
 * 常见写法（建议都掌握）：
 * 1) 完全背包 DP：dp[i] 表示凑成金额 i 所需的最少硬币数，O(amount * n)
 * 2) 记忆化搜索：自顶向下的 DFS + memo
 * 3) BFS：将问题看作图的最短路径问题（从 0 到 amount）
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class CoinChange {

    /**
     * 解法一：完全背包 DP ⭐ 推荐
     * 定义：dp[i] = 凑成金额 i 所需的最少硬币数
     * 转移：dp[i] = min(dp[i - coin] + 1)，对于所有 coin <= i
     * 初始：dp[0] = 0，dp[其他] = amount + 1（表示不可达）
     * 答案：dp[amount] > amount ? -1 : dp[amount]
     *
     * 时间复杂度：O(amount * n)，n 为硬币种类数
     * 空间复杂度：O(amount)
     *
     * @param coins  硬币面额数组
     * @param amount 总金额
     * @return 最少硬币个数，无法凑成返回 -1
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i] 表示凑成金额 i 所需的最少硬币数
        int[] dp = new int[amount + 1];
        
        // 初始化：除了 dp[0] = 0，其他都设为 amount + 1（表示无法凑成）
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        // 外层循环：遍历金额（完全背包问题）
        for (int i = 1; i <= amount; i++) {
            // 内层循环：尝试每种硬币
            for (int coin : coins) {
                // 如果当前金额 >= 硬币面额，可以选择这个硬币
                if (i >= coin) {
                    // dp[i] = min(不选这个硬币, 选这个硬币)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 如果 dp[amount] 仍然是初始值，说明无法凑成
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 解法二：记忆化搜索
     * 思路：dfs(remain) 表示凑成剩余金额 remain 所需的最少硬币数
     * 转移：dfs(remain) = min(dfs(remain - coin) + 1)，对于所有 coin <= remain
     * 边界：remain == 0 返回 0，remain < 0 返回 -1
     *
     * 时间复杂度：O(amount * n)
     * 空间复杂度：O(amount) - 递归栈 + memo 数组
     *
     * @param coins  硬币面额数组
     * @param amount 总金额
     * @return 最少硬币个数，无法凑成返回 -1
     */
    public int coinChangeMemo(int[] coins, int amount) {
        // memo[i] 表示凑成金额 i 所需的最少硬币数，-2 表示未计算
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return dfs(coins, amount, memo);
    }
    
    private int dfs(int[] coins, int remain, int[] memo) {
        // 边界条件
        if (remain == 0) return 0;
        if (remain < 0) return -1;
        
        // 如果已经计算过，直接返回
        if (memo[remain] != -2) {
            return memo[remain];
        }
        
        // 尝试每种硬币，选择最优的
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subResult = dfs(coins, remain - coin, memo);
            if (subResult != -1) {
                minCount = Math.min(minCount, subResult + 1);
            }
        }
        
        // 记忆化
        memo[remain] = (minCount == Integer.MAX_VALUE) ? -1 : minCount;
        return memo[remain];
    }

    /**
     * 解法三：BFS（将问题看作图的最短路径）
     * 思路：从金额 0 开始，每次可以选择一种硬币面额前进，求到达 amount 的最短步数
     * - 使用队列进行 BFS
     * - 使用 visited 数组避免重复访问
     * - 第一次到达 amount 时的步数即为答案
     *
     * 时间复杂度：O(amount * n)
     * 空间复杂度：O(amount) - 队列 + visited 数组
     *
     * @param coins  硬币面额数组
     * @param amount 总金额
     * @return 最少硬币个数，无法凑成返回 -1
     */
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        // 使用队列进行 BFS
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        
        queue.offer(0);
        visited[0] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            
            // 处理当前层的所有节点
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                // 尝试每种硬币
                for (int coin : coins) {
                    int next = current + coin;
                    
                    // 如果到达目标金额
                    if (next == amount) {
                        return steps;
                    }
                    
                    // 如果未超出范围且未访问过
                    if (next < amount && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        
        return -1;
    }
}

