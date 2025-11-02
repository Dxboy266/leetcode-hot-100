package com.leetcode.dp;

/**
 * 5. 最长回文子串 (Longest Palindromic Substring)
 *
 * 题目链接：https://leetcode.cn/problems/longest-palindromic-substring/
 * 难度：中等
 * 标签：字符串、动态规划
 *
 * ==================== 题目描述 ====================
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 提示：
 * - 1 <= s.length <= 1000
 * - s 仅由数字和英文字母组成
 *
 * ==================== 解题提示 ====================
 * 常见写法（建议都掌握）：
 * 1) 中心扩展法：枚举每个中心（单字符或双字符），向两边扩展，O(n²) ⭐ 推荐
 * 2) 动态规划：dp[i][j] 表示 s[i..j] 是否为回文，O(n²)
 * 3) Manacher 算法：线性时间 O(n)，但较复杂
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class LongestPalindrome {

    /**
     * 解法一：中心扩展法 ⭐ 推荐
     * 思路：枚举每个可能的回文中心（单字符或双字符），向两边扩展
     * - 奇数长度回文：中心是单个字符
     * - 偶数长度回文：中心是两个相同字符
     * 时间复杂度：O(n²)，空间复杂度：O(1)
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        // TODO: 实现中心扩展法
        return "";
    }

    /**
     * 解法二：动态规划
     * 定义：dp[i][j] = s[i..j] 是否为回文
     * 转移：dp[i][j] = (s[i] == s[j]) && (j - i < 2 || dp[i+1][j-1])
     *   - j - i < 2：长度为 1 或 2 的子串
     *   - dp[i+1][j-1]：内部子串是否为回文
     * 初始：dp[i][i] = true
     * 答案：记录最长的 dp[i][j] = true 的子串
     *
     * 注意：遍历顺序要保证 dp[i+1][j-1] 在 dp[i][j] 之前计算
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindromeDP(String s) {
        // TODO: 实现 DP 解法
        return "";
    }

    /**
     * 解法三：Manacher 算法（进阶）
     * 思路：通过预处理字符串（插入分隔符）统一奇偶情况，利用回文的对称性减少重复计算
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * 核心思想：
     * - 预处理：在每个字符之间插入 '#'，如 "babad" -> "#b#a#b#a#d#"
     * - 维护 p[i] 表示以 i 为中心的最长回文半径
     * - 利用已知的回文信息加速计算
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindromeManacher(String s) {
        // TODO: 实现 Manacher 算法（选做）
        return "";
    }
}

