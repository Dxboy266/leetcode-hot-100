package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 5. 最长回文子串
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class LongestPalindromeTest {

    private final LongestPalindrome solution = new LongestPalindrome();

    @Test
    public void testExample1() {
        String s = "babad";
        String result = solution.longestPalindrome(s);
        assertTrue(result.equals("bab") || result.equals("aba"));

        String resultDP = solution.longestPalindromeDP(s);
        assertTrue(resultDP.equals("bab") || resultDP.equals("aba"));
    }

    @Test
    public void testExample2() {
        String s = "cbbd";
        assertEquals("bb", solution.longestPalindrome(s));
        assertEquals("bb", solution.longestPalindromeDP(s));
    }

    @Test
    public void testSingleChar() {
        String s = "a";
        assertEquals("a", solution.longestPalindrome(s));
        assertEquals("a", solution.longestPalindromeDP(s));
    }

    @Test
    public void testTwoSameChars() {
        String s = "aa";
        assertEquals("aa", solution.longestPalindrome(s));
        assertEquals("aa", solution.longestPalindromeDP(s));
    }

    @Test
    public void testTwoDifferentChars() {
        String s = "ab";
        assertTrue(solution.longestPalindrome(s).length() == 1);
        assertTrue(solution.longestPalindromeDP(s).length() == 1);
    }

    @Test
    public void testAllSameChars() {
        String s = "aaaa";
        assertEquals("aaaa", solution.longestPalindrome(s));
        assertEquals("aaaa", solution.longestPalindromeDP(s));
    }

    @Test
    public void testOddLengthPalindrome() {
        String s = "racecar";
        assertEquals("racecar", solution.longestPalindrome(s));
        assertEquals("racecar", solution.longestPalindromeDP(s));
    }

    @Test
    public void testEvenLengthPalindrome() {
        String s = "abccba";
        assertEquals("abccba", solution.longestPalindrome(s));
        assertEquals("abccba", solution.longestPalindromeDP(s));
    }

    @Test
    public void testMultiplePalindromes() {
        String s = "abacabad";
        String result = solution.longestPalindrome(s);
        assertTrue(result.equals("aba") || result.equals("aca") || result.equals("abacaba"));

        String resultDP = solution.longestPalindromeDP(s);
        assertTrue(resultDP.equals("aba") || resultDP.equals("aca") || resultDP.equals("abacaba"));
    }

    @Test
    public void testNoPalindrome() {
        String s = "abcdef";
        assertEquals(1, solution.longestPalindrome(s).length());
        assertEquals(1, solution.longestPalindromeDP(s).length());
    }

    @Test
    public void testLongString() {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendure";
        String result = solution.longestPalindrome(s);
        assertTrue(result.length() >= 7); // 至少包含一些回文

        String resultDP = solution.longestPalindromeDP(s);
        assertTrue(resultDP.length() >= 7);
    }

    @Test
    public void testPalindromeAtStart() {
        String s = "abaxyzzyxf";
        assertEquals("xyzzyx", solution.longestPalindrome(s));
        assertEquals("xyzzyx", solution.longestPalindromeDP(s));
    }

    @Test
    public void testPalindromeAtEnd() {
        String s = "xyzracecar";
        assertEquals("racecar", solution.longestPalindrome(s));
        assertEquals("racecar", solution.longestPalindromeDP(s));
    }

    @Test
    public void testEntireStringIsPalindrome() {
        String s = "abcba";
        assertEquals("abcba", solution.longestPalindrome(s));
        assertEquals("abcba", solution.longestPalindromeDP(s));
    }
}

