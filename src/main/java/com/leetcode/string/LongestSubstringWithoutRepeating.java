package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 
 * 题目链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 难度：中等
 * 标签：哈希表、字符串、滑动窗口
 *
 * ==================== 题目描述 ====================
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 * - 0 <= s.length <= 5 * 10^4
 * - s 由英文字母、数字、符号和空格组成
 *
 * ==================== 解题思路 ====================
 * 核心：滑动窗口 + 哈希表
 * 
 * 关键点：
 * 1. 使用滑动窗口维护一个无重复字符的子串
 * 2. 使用 HashMap 记录字符最后出现的位置
 * 3. 当遇到重复字符时，移动左指针到重复字符的下一个位置
 * 
 * 滑动窗口模板：
 * - left: 窗口左边界
 * - right: 窗口右边界（遍历指针）
 * - 扩大窗口：right++
 * - 收缩窗口：left++ (当遇到重复字符时)
 * - 更新答案：每次移动 right 后更新最大长度
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class LongestSubstringWithoutRepeating {

    /**
     * 解法一：滑动窗口 + HashMap ⭐ 推荐
     * 
     * 核心思想：
     * - 维护一个窗口 [left, right]，保证窗口内无重复字符
     * - 使用 HashMap 记录每个字符最后出现的位置
     * - 遇到重复字符时，将 left 移动到重复字符上次出现位置的下一个位置
     * 
     * 时间复杂度：O(n)，每个字符最多被访问两次
     * 空间复杂度：O(min(m,n))，m 是字符集大小
     * 
     * @param s 字符串
     * @return 最长无重复子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        // 特殊情况
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // map 记录字符最后出现的位置（索引）
        Map<Character, Integer> map = new HashMap<>();
        
        int maxLen = 0;  // 最长子串长度
        int left = 0;    // 窗口左边界
        
        // right 为窗口右边界，遍历整个字符串
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // 如果字符已存在，且在当前窗口内
            if (map.containsKey(c) && map.get(c) >= left) {
                // 移动左指针到重复字符的下一个位置
                left = map.get(c) + 1;
            }
            
            // 更新字符的最新位置
            map.put(c, right);
            
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    /**
     * 解法二：滑动窗口 + HashSet
     * 
     * 思路：
     * - 使用 HashSet 存储窗口内的字符
     * - 遇到重复字符时，不断移除 left 位置的字符，直到无重复
     * 
     * 时间复杂度：O(2n) = O(n)，最坏情况每个字符被访问两次
     * 空间复杂度：O(min(m,n))
     * 
     * @param s 字符串
     * @return 最长无重复子串长度
     */
    public int lengthOfLongestSubstringSet(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // 如果字符已存在，不断移除左边字符，直到无重复
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            
            // 添加当前字符
            set.add(c);
            
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }

    /**
     * 解法三：优化的数组（适用于 ASCII 字符）
     * 
     * 思路：
     * - 使用数组代替 HashMap，索引为字符 ASCII 值
     * - 数组值为字符最后出现的位置 + 1（0 表示未出现）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)，固定 128 大小的数组
     * 
     * @param s 字符串
     * @return 最长无重复子串长度
     */
    public int lengthOfLongestSubstringArray(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // ASCII 字符集，128 个字符
        int[] lastIndex = new int[128];
        
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // 更新左指针：取max确保left只前进不后退
            left = Math.max(left, lastIndex[c]);
            
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
            
            // 更新字符的位置（+1是为了区分未出现的情况）
            lastIndex[c] = right + 1;
        }
        
        return maxLen;
    }
}

