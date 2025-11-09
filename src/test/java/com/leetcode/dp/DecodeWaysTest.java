package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 91. 解码方法
 *
 * 测试覆盖：
 * - 基本示例测试
 * - 边界条件测试（单字符、长字符串）
 * - 特殊情况测试（包含 0、前导零、连续 0）
 * - 复杂场景测试
 *
 * @Author Dxboy266
 * @Date 2025-11-07
 */
public class DecodeWaysTest {

    private final DecodeWays solution = new DecodeWays();

    // ==================== 基本示例测试 ====================

    @Test
    public void testExample1() {
        // 输入："11"
        // 输出：2
        // 解释：可以解码为 "AA" (1, 1) 或 "K" (11)
        assertEquals(2, solution.numDecodings("11"));
        assertEquals(2, solution.numDecodingsOptimized("11"));
    }

    @Test
    public void testExample2() {
        // 输入："10"
        // 输出：1
        // 解释：只能解码为 "J" (10)
        assertEquals(1, solution.numDecodings("10"));
        assertEquals(1, solution.numDecodingsOptimized("10"));
    }

    @Test
    public void testExample3() {
        // 输入："100"
        // 输出：0
        // 解释：无法解码，因为 "00" 无效
        assertEquals(0, solution.numDecodings("100"));
        assertEquals(0, solution.numDecodingsOptimized("100"));
    }

    @Test
    public void testExample4() {
        // 输入："226"
        // 输出：3
        // 解释：可以解码为 "BBF" (2,2,6)、"VF" (22,6)、"BZ" (2,26)
        assertEquals(3, solution.numDecodings("226"));
        assertEquals(3, solution.numDecodingsOptimized("226"));
    }

    // ==================== 边界条件测试 ====================

    @Test
    public void testSingleDigit() {
        // 单个有效数字
        // "1" -> "A"
        assertEquals(1, solution.numDecodings("1"));
        // "9" -> "I"
        assertEquals(1, solution.numDecodings("9"));
        assertEquals(1, solution.numDecodingsOptimized("1"));
        assertEquals(1, solution.numDecodingsOptimized("9"));
    }

    @Test
    public void testSingleZero() {
        // 单个 0，无法解码
        assertEquals(0, solution.numDecodings("0"));
        assertEquals(0, solution.numDecodingsOptimized("0"));
    }

    @Test
    public void testTwoDigitsValid() {
        // 两位数，都可以解码
        // "12" -> "AB" (1,2) 或 "L" (12)
        assertEquals(2, solution.numDecodings("12"));
        // "26" -> "BF" (2,6) 或 "Z" (26)
        assertEquals(2, solution.numDecodings("26"));
        assertEquals(2, solution.numDecodingsOptimized("12"));
        assertEquals(2, solution.numDecodingsOptimized("26"));
    }

    @Test
    public void testTwoDigitsPartialValid() {
        // 两位数，只能单独解码
        // "27" -> 只能是 "BG" (2,7)
        assertEquals(1, solution.numDecodings("27"));
        // "99" -> 只能是 "II" (9,9)
        assertEquals(1, solution.numDecodings("99"));
        assertEquals(1, solution.numDecodingsOptimized("27"));
        assertEquals(1, solution.numDecodingsOptimized("99"));
    }

    // ==================== 包含 0 的特殊情况 ====================

    @Test
    public void testWithValidZero() {
        // 包含有效的 0（10 或 20）
        // "20" -> "T" (20)
        assertEquals(1, solution.numDecodings("20"));
        // "30" -> 无效，30 > 26，无法解码
        assertEquals(0, solution.numDecodings("30"));
        assertEquals(1, solution.numDecodingsOptimized("20"));
        assertEquals(0, solution.numDecodingsOptimized("30"));
    }

    @Test
    public void testWithInvalidZero() {
        // 包含无效的 0
        // "01" -> 前导零，无法解码
        assertEquals(0, solution.numDecodings("01"));
        // "301" -> 30 无效，无法解码
        assertEquals(0, solution.numDecodings("301"));
        // "00" -> 连续零，无法解码
        assertEquals(0, solution.numDecodings("00"));
        assertEquals(0, solution.numDecodingsOptimized("01"));
        assertEquals(0, solution.numDecodingsOptimized("301"));
        assertEquals(0, solution.numDecodingsOptimized("00"));
    }

    @Test
    public void testConsecutiveZeros() {
        // 连续的 0
        assertEquals(0, solution.numDecodings("1000"));
        assertEquals(0, solution.numDecodings("2000"));
        assertEquals(0, solution.numDecodingsOptimized("1000"));
        assertEquals(0, solution.numDecodingsOptimized("2000"));
    }

    // ==================== 复杂场景测试 ====================

    @Test
    public void testComplexCase1() {
        // 输入："111111"
        // 可能的解码方式:
        // 1. (1)(1)(1)(1)(1)(1) -> "AAAAAA"
        // 2. (11)(1)(1)(1)(1) -> "KAAAA"
        // 3. (1)(11)(1)(1)(1) -> "AKAAA"
        // 4. (1)(1)(11)(1)(1) -> "AAKAA"
        // 5. (1)(1)(1)(11)(1) -> "AAAKA"
        // 6. (1)(1)(1)(1)(11) -> "AAA AK"
        // 7. (11)(11)(1)(1) -> "KKAA"
        // 8. (11)(1)(11)(1) -> "KAKA"
        // 9. (11)(1)(1)(11) -> "KAAK"
        // 10. (1)(11)(11)(1) -> "AKKA"
        // 11. (1)(11)(1)(11) -> "AKAK"
        // 12. (1)(1)(11)(11) -> "AAKK"
        // 13. (11)(11)(11) -> "KKK"
        assertEquals(13, solution.numDecodings("111111"));
        assertEquals(13, solution.numDecodingsOptimized("111111"));
    }

    @Test
    public void testComplexCase2() {
        // 输入："1212"
        // 可能的解码方式:
        // 1. (1)(2)(1)(2) -> "ABAB"
        // 2. (12)(1)(2) -> "LAB"
        // 3. (1)(21)(2) -> "UAB"
        // 4. (1)(2)(12) -> "ABL"
        // 5. (12)(12) -> "LL"
        assertEquals(5, solution.numDecodings("1212"));
        assertEquals(5, solution.numDecodingsOptimized("1212"));
    }

    @Test
    public void testComplexCase3() {
        // 输入："2101"
        // 可能的解码方式:
        //  (2)(10)(1) -> "BJA"
        assertEquals(1, solution.numDecodings("2101"));
        assertEquals(1, solution.numDecodingsOptimized("2101"));
    }

    @Test
    public void testAllOnes() {
        // 全是 1："1111"
        // 可能的解码方式:
        // 1. (1)(1)(1)(1) -> "AAAA"
        // 2. (11)(1)(1) -> "KAA"
        // 3. (1)(11)(1) -> "AKA"
        // 4. (1)(1)(11) -> "AAK"
        // 5. (11)(11) -> "KK"
        assertEquals(5, solution.numDecodings("1111"));
        assertEquals(5, solution.numDecodingsOptimized("1111"));
    }

    @Test
    public void testAllTwos() {
        // 全是 2："2222"
        // 可能的解码方式:
        // 1. (2)(2)(2)(2) -> "BBBB"
        // 2. (22)(2)(2) -> "ZBB"
        // 3. (2)(22)(2) -> "BZB"
        // 4. (2)(2)(22) -> "BBZ"
        // 5. (22)(22) -> "ZZ"
        assertEquals(5, solution.numDecodings("2222"));
        assertEquals(5, solution.numDecodingsOptimized("2222"));
    }

    @Test
    public void testMixedNumbers() {
        // 混合数字："123"
        // 可能的解码方式:
        // 1. (1)(2)(3) -> "ABC"
        // 2. (12)(3) -> "LC"
        // 3. (1)(23) -> "AW"
        assertEquals(3, solution.numDecodings("123"));
        assertEquals(3, solution.numDecodingsOptimized("123"));
    }

    // ==================== 边界值测试 ====================

    @Test
    public void testBoundary26() {
        // 边界值 26（最大的有效两位数）："126"
        // 可能的解码方式:
        // 1. (1)(2)(6) -> "ABF"
        // 2. (12)(6) -> "LF"
        // 3. (1)(26) -> "AZ"
        assertEquals(3, solution.numDecodings("126"));
        assertEquals(3, solution.numDecodingsOptimized("126"));
    }

    @Test
    public void testBoundary27() {
        // 边界值 27（超过最大有效两位数）："127"
        // 可能的解码方式:
        // 1. (1)(2)(7) -> "ABG"
        // 2. (12)(7) -> "LG"
        assertEquals(2, solution.numDecodings("127"));
        assertEquals(2, solution.numDecodingsOptimized("127"));
    }

    @Test
    public void testLongString() {
        // 较长的字符串 "12121"
        // 可能的解码方式:
        // 1. (1)(2)(1)(2)(1) -> "BABAB"
        // 2. (12)(1)(2)(1) -> "LABAB"
        // 3. (1)(21)(2)(1) -> "UAB"
        // 4. (1)(2)(12)(1) -> "ABL"
        // 5. (1)(2)(1)(21) -> "ABU"
        // 6. (12)(12)(1) -> "LLA"
        // 7. (12)(1)(21) -> "LAU"
        // 8. (1)(21)(21) -> "UU"
        assertEquals(8, solution.numDecodings("12121"));
        assertEquals(8, solution.numDecodingsOptimized("12121"));
    }
}