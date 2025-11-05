package com.leetcode.string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 3. 无重复字符的最长子串 - 测试类
 */
class LongestSubstringWithoutRepeatingTest {

    private final LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

    @Test
    void testExample1() {
        String s = "abcabcbb";
        assertEquals(3, solution.lengthOfLongestSubstring(s));
        assertEquals(3, solution.lengthOfLongestSubstringSet(s));
        assertEquals(3, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testExample2() {
        String s = "bbbbb";
        assertEquals(1, solution.lengthOfLongestSubstring(s));
        assertEquals(1, solution.lengthOfLongestSubstringSet(s));
        assertEquals(1, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testExample3() {
        String s = "pwwkew";
        assertEquals(3, solution.lengthOfLongestSubstring(s));
        assertEquals(3, solution.lengthOfLongestSubstringSet(s));
        assertEquals(3, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testEmptyString() {
        String s = "";
        assertEquals(0, solution.lengthOfLongestSubstring(s));
        assertEquals(0, solution.lengthOfLongestSubstringSet(s));
        assertEquals(0, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testSingleChar() {
        String s = "a";
        assertEquals(1, solution.lengthOfLongestSubstring(s));
        assertEquals(1, solution.lengthOfLongestSubstringSet(s));
        assertEquals(1, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testAllUnique() {
        String s = "abcdef";
        assertEquals(6, solution.lengthOfLongestSubstring(s));
        assertEquals(6, solution.lengthOfLongestSubstringSet(s));
        assertEquals(6, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testWithSpace() {
        String s = "a b c d";
        // "a b c d" = 'a',' ','b',' ','c',' ','d'，空格重复了
        // 最长无重复子串是 "a b" 或 " b c" 或 " c d"，但空格会重复
        // 实际上最长的是 "a " 或 " b" 或 " c" 或 " d"，长度为2
        // 但更准确的是 "a b" 去掉重复空格后，或者考虑 "a" 和 "b" 之间
        // 实际上算法返回的是 " b c" 去掉空格后，或者 "a b" 去掉空格后
        // 验证：算法正确返回3（"a b"或" b c"去掉重复空格前）
        assertEquals(3, solution.lengthOfLongestSubstring(s)); // "a b" 或 " b c" 等
        assertEquals(3, solution.lengthOfLongestSubstringSet(s));
        assertEquals(3, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testComplexCase() {
        String s = "dvdf";
        assertEquals(3, solution.lengthOfLongestSubstring(s)); // "vdf"
        assertEquals(3, solution.lengthOfLongestSubstringSet(s));
        assertEquals(3, solution.lengthOfLongestSubstringArray(s));
    }

    @Test
    void testWithNumbers() {
        String s = "au1b2c3";
        assertEquals(7, solution.lengthOfLongestSubstring(s));
        assertEquals(7, solution.lengthOfLongestSubstringSet(s));
        assertEquals(7, solution.lengthOfLongestSubstringArray(s));
    }
}

