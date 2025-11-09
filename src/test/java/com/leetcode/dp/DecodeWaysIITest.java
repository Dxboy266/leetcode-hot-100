package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 639. 解码方法 II
 *
 * 测试覆盖：
 * - 基本示例测试
 * - 包含 '*' 的各种情况
 * - 边界条件测试
 * - 复杂场景测试
 *
 * @Author Dxboy266
 * @Date 2025-11-07
 */
public class DecodeWaysIITest {

    private final DecodeWaysII solution = new DecodeWaysII();

    // ==================== 基本示例测试 ====================

    @Test
    public void testExample1() {
        // 输入："*"
        // 输出：9
        // 解释：可以表示 "1"-"9"，共 9 种解码方式
        assertEquals(9, solution.numDecodings("*"));
        assertEquals(9, solution.numDecodingsOptimized("*"));
    }

    @Test
    public void testExample2() {
        // 输入："1*"
        // 输出：18
        // 解释："11"-"19" 各有 2 种解码方式，共 9 * 2 = 18
        assertEquals(18, solution.numDecodings("1*"));
        assertEquals(18, solution.numDecodingsOptimized("1*"));
    }

    @Test
    public void testExample3() {
        // 输入："2*"
        // 输出：15
        // 解释："21"-"26" 各有 2 种，"27"-"29" 各有 1 种，共 6*2 + 3*1 = 15
        assertEquals(15, solution.numDecodings("2*"));
        assertEquals(15, solution.numDecodingsOptimized("2*"));
    }

    // ==================== 边界条件测试 ====================

    @Test
    public void testSingleDigit() {
        // 单个有效数字
        assertEquals(1, solution.numDecodings("1"));
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
    public void testTwoStars() {
        // 输入："**"
        // 解释：单独解码 9*9=81，组合解码 15（11-19有9个，21-26有6个）
        // 总共：81 + 15 = 96
        assertEquals(96, solution.numDecodings("**"));
        assertEquals(96, solution.numDecodingsOptimized("**"));
    }

    // ==================== 包含 '*' 的特殊情况 ====================

    @Test
    public void testStarWithZero() {
        // 输入："*0"
        // 解释：只能是 "10" 或 "20"，共 2 种
        assertEquals(2, solution.numDecodings("*0"));
        assertEquals(2, solution.numDecodingsOptimized("*0"));
    }

    @Test
    public void testDigitWithStar() {
        // 输入："3*"
        // 解释："31"-"39" 只能单独解码，共 9 种
        assertEquals(9, solution.numDecodings("3*"));
        assertEquals(9, solution.numDecodingsOptimized("3*"));
    }

    @Test
    public void testStarWithDigit() {
        // 输入："*1"
        // 解释：单独解码 9*1=9，组合解码 2（11, 21），共 9+2=11
        assertEquals(11, solution.numDecodings("*1"));
        assertEquals(11, solution.numDecodingsOptimized("*1"));
    }

    @Test
    public void testStarWithSeven() {
        // 输入："*7"
        // 解释：单独解码 9*1=9，组合解码 1（17），共 9+1=10
        assertEquals(10, solution.numDecodings("*7"));
        assertEquals(10, solution.numDecodingsOptimized("*7"));
    }

    // ==================== 复杂场景测试 ====================

    @Test
    public void testComplexCase1() {
        // 输入："1*1*0"
        // 复杂的组合情况
        assertEquals(40, solution.numDecodings("1*1*0"));
        assertEquals(40, solution.numDecodingsOptimized("1*1*0"));
    }

    @Test
    public void testComplexCase2() {
        // 输入："***"
        // 三个星号的组合
        assertEquals(999, solution.numDecodings("***"));
        assertEquals(999, solution.numDecodingsOptimized("***"));
    }

    @Test
    public void testMixedNumbers() {
        // 输入："12*3"
        // 混合数字和星号
        // 解码方式：
        // 1. "1"+"2"+"*"+"3" → 9种（*可以是1-9）
        // 2. "12"+"*"+"3" → 9种
        // 3. "1"+"2*"+"3" → 6种（2*可以是21-26）
        // 4. "1"+"2"+"*3" → 2种（*3可以是13或23）
        // 5. "12"+"*3" → 2种
        // 总计：9+9+6+2+2 = 28种
        assertEquals(28, solution.numDecodings("12*3"));
        assertEquals(28, solution.numDecodingsOptimized("12*3"));
    }

    @Test
    public void testWithZeros() {
        // 输入："1*0"
        // 包含 0 的情况
        assertEquals(2, solution.numDecodings("1*0"));
        assertEquals(2, solution.numDecodingsOptimized("1*0"));
    }

    @Test
    public void testInvalidWithZero() {
        // 输入："*0*"
        // 包含 0 的复杂情况
        assertEquals(18, solution.numDecodings("*0*"));
        assertEquals(18, solution.numDecodingsOptimized("*0*"));
    }

    // ==================== 边界值测试 ====================

    @Test
    public void testLongString() {
        // 较长的字符串
        assertEquals(291868912, solution.numDecodings("*********"));
        assertEquals(291868912, solution.numDecodingsOptimized("*********"));
    }

    @Test
    public void testAllDigits() {
        // 全是数字（类似 91 题）
        assertEquals(2, solution.numDecodings("12"));
        assertEquals(3, solution.numDecodings("226"));
        assertEquals(2, solution.numDecodingsOptimized("12"));
        assertEquals(3, solution.numDecodingsOptimized("226"));
    }

    @Test
    public void testLeadingZero() {
        // 前导零
        assertEquals(0, solution.numDecodings("01"));
        assertEquals(0, solution.numDecodingsOptimized("01"));
    }

    @Test
    public void testConsecutiveZeros() {
        // 连续的 0
        assertEquals(0, solution.numDecodings("100"));
        assertEquals(0, solution.numDecodingsOptimized("100"));
    }
}

