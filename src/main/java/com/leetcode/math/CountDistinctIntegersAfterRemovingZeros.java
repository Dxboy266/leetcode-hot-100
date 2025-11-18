package com.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * 统计移除所有零后的不同整数数量
 * 
 * 题目链接：LeetCode 类似题目
 * 难度：中等
 * 标签：数学、数位DP、组合数学
 * 
 * ==================== 题目描述 ====================
 * 给你一个正整数 n。
 * 
 * 对于从 1 到 n 的每个整数 x，我们记下通过移除 x 的十进制表示中的所有零而得到的整数。
 * 返回一个整数，表示记下的不同整数的数量。
 * 
 * 示例 1：
 * 输入：n = 10
 * 输出：9
 * 解释：
 * 我们记下的整数是 1, 2, 3, 4, 5, 6, 7, 8, 9, 1。有 9 个不同的整数 (1, 2, 3, 4, 5, 6, 7, 8, 9)。
 * 
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：
 * 我们记下的整数是 1, 2, 3。有 3 个不同的整数 (1, 2, 3)。
 * 
 * 示例 3：
 * 输入：n = 100
 * 输出：90
 * 解释：
 * 1-9: 9个
 * 10-99: 移除0后得到 1-9 (重复) 和 11-99中不含0的数
 * 100: 移除0后得到 1 (重复)
 * 
 * 提示：
 * 1 <= n <= 10^15
 * 
 * ==================== 解题思路 ====================
 * 
 * 【错误思路分析】
 * 很多人会想到用乘法：对于 n 的每一位数字，乘以该位的数字。
 * 例如 n = 123，认为答案是 1 * 2 * 3 = 6。
 * 这是错误的！因为：
 * 1. 没有考虑到不同数字移除0后可能得到相同结果（如 10 和 100 都变成 1）
 * 2. 没有考虑到数字本身包含0的情况
 * 3. 没有考虑到边界条件
 * 
 * 【正确思路：数位DP】
 * 
 * 核心观察：
 * 1. 移除0后的数字，只包含 1-9 这些非零数字
 * 2. 我们需要统计所有可能的"非零数字组合"的数量
 * 3. 这些组合必须 <= n 移除0后的结果
 * 
 * 方法一：暴力法（仅适用于小数据）
 * - 遍历 1 到 n，对每个数字移除0，用 HashSet 去重
 * - 时间复杂度：O(n * log n)
 * - 空间复杂度：O(n)
 * - 适用范围：n <= 10^6
 * 
 * 方法二：数位DP（推荐）★★★
 * - 核心思想：统计所有长度 <= len(n) 且不含0的数字有多少个
 * - 需要考虑前导零和上界限制
 * - 时间复杂度：O(log n)
 * - 空间复杂度：O(log n)
 * - 适用范围：n <= 10^15
 * 
 * 【数位DP详解】
 * 
 * 状态定义：
 * dp[pos][isLimit][isNum] 表示：
 * - pos: 当前处理到第几位（从高位到低位）
 * - isLimit: 当前是否受到 n 的限制（前面的位是否都取到了上界）
 * - isNum: 前面是否已经填了数字（用于处理前导零）
 * 
 * 转移方程：
 * - 如果 isNum = false，可以选择跳过当前位（继续前导零）
 * - 如果 isNum = true 或选择填数字，枚举 1-9（不能填0）
 * - 如果 isLimit = true，当前位最大只能填到 n[pos]
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 数位DP的应用场景：
 *    - 统计某个范围内满足特定条件的数字个数
 *    - 条件通常与数字的每一位有关
 *    - 例如：不含某些数字、数字和为某个值等
 * 
 * 2. 数位DP的关键点：
 *    - isLimit: 处理上界限制
 *    - isNum: 处理前导零
 *    - 记忆化搜索：避免重复计算
 * 
 * 3. 易错点：
 *    - 忘记处理前导零（会导致 001 被当作有效数字）
 *    - 忘记处理上界限制（会统计到超过 n 的数字）
 *    - 忘记去重（不同的原始数字可能移除0后相同）
 * 
 * 4. 相关题目：
 *    - LeetCode 233. 数字1的个数
 *    - LeetCode 902. 最大为 N 的数字组合
 *    - LeetCode 1012. 至少有1位重复的数字
 * 
 * @Author Dxboy266
 * @Date 2025-11-16
 */
public class CountDistinctIntegersAfterRemovingZeros {
    
    /**
     * 方法一：暴力法（仅适用于小数据）
     * 时间复杂度：O(n * log n)
     * 空间复杂度：O(n)
     * 
     * 思路：
     * 1. 遍历 1 到 n 的每个数字
     * 2. 移除每个数字中的所有0
     * 3. 用 HashSet 存储去重后的结果
     * 4. 返回 HashSet 的大小
     * 
     * 注意：此方法仅适用于 n <= 10^6，对于 10^15 会超时
     */
    public long countDistinctBruteForce(long n) {
        Set<Long> distinctNumbers = new HashSet<>();
        
        for (long i = 1; i <= n; i++) {
            long numWithoutZeros = removeZeros(i);
            distinctNumbers.add(numWithoutZeros);
        }
        
        return distinctNumbers.size();
    }
    
    /**
     * 辅助方法：移除数字中的所有0
     */
    private long removeZeros(long num) {
        StringBuilder sb = new StringBuilder();
        String numStr = String.valueOf(num);
        
        for (char c : numStr.toCharArray()) {
            if (c != '0') {
                sb.append(c);
            }
        }
        
        return Long.parseLong(sb.toString());
    }
    
    /**
     * 方法二：数位DP（推荐）★★★
     * 时间复杂度：O(n) 对于小数据，O((log n)^2) 对于大数据
     * 空间复杂度：O(1) 对于大数据，O(n) 对于小数据
     *
     * 思路：
     * 这道题的关键理解：
     * 1. 我们需要遍历 1 到 n 的所有数字
     * 2. 对每个数字移除0后，可能得到相同的结果
     * 3. 统计有多少个不同的结果
     *
     * 关键观察：
     * 对于任意不含0的数字 m <= n，它一定可以通过某个 x <= n 移除0得到（至少 x=m）
     * 所以，我们需要统计有多少个不含0的数字 <= n
     *
     * 对于小数据，使用暴力法验证正确性
     * 对于大数据，使用数学方法
     */
    public long countDistinct(long n) {
        // 对于小数据，直接使用暴力法
        if (n <= 1_000_000) {
            return countDistinctBruteForce(n);
        }

        // 对于大数据，使用数学方法
        return countDistinctMath(n);
    }
    
    /**
     * 方法三：数学公式法（最优解）★★★★★
     * 时间复杂度：O((log n)^2)
     * 空间复杂度：O(log n)
     *
     * 思路：
     * 关键观察：移除0后的数字只包含1-9
     *
     * 对于1到n的每个数字，移除0后，有多少个不同的结果？
     *
     * 关键观察：
     * 1. 移除0后的最大值不一定是 removeZeros(n)
     * 2. 例如 n=100，removeZeros(100)=1，但99→99，所以最大值是99
     * 3. 移除0后的最大值是：从1到n中，移除0后得到的最大值
     *
     * 更深入的观察：
     * 对于任意不含0的数字 m，我们可以通过在其中插入0得到多个数字
     * 例如：m=12，可以得到 12, 102, 120, 1020, ...
     *
     * 所以，我们需要统计有多少个不含0的数字 m，使得：
     * - m <= n（m本身就 <= n）
     * - 或者，在m中插入0后得到的某个数字 <= n
     *
     * 简化：对于任意不含0的数字 m <= n，它一定可以通过某个 x <= n 移除0得到（至少 x=m）
     * 所以，我们需要统计有多少个不含0的数字 <= n
     */
    public long countDistinctMath(long n) {
        // 统计有多少个不含0的数字 <= n
        return countNonZeroNumbers(n);
    }

    /**
     * 统计有多少个不含0的数字 <= upperBound
     * 使用数位DP
     */
    private long countNonZeroNumbers(long upperBound) {
        String s = String.valueOf(upperBound);
        int len = s.length();

        long count = 0;

        // 1. 统计长度 < len 的所有不含0的数字
        for (int i = 1; i < len; i++) {
            count += pow9(i);
        }

        // 2. 统计长度 = len 且 <= upperBound 的不含0数字
        count += countWithLength(s, 0, true);

        return count;
    }

    /**
     * 计算 9^k
     */
    private long pow9(int k) {
        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= 9;
        }
        return result;
    }

    /**
     * 统计长度固定且 <= upperBound 的不含0数字的个数
     */
    private long countWithLength(String s, int pos, boolean isLimit) {
        if (pos == s.length()) {
            return 1;
        }

        int up = isLimit ? (s.charAt(pos) - '0') : 9;
        long count = 0;

        for (int digit = 1; digit <= up; digit++) {
            count += countWithLength(s, pos + 1, isLimit && (digit == up));
        }

        return count;
    }
}

