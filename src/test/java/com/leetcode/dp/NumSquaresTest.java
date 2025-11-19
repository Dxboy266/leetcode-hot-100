package com.leetcode.dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NumSquares 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试（示例用例）
 * - 边界条件测试（n=1, 完全平方数）
 * - 特殊情况测试（四平方和定理的各种情况）
 * - 多种方法一致性测试
 * 
 * @Author Dxboy266
 * @Date 2025-11-19
 */
class NumSquaresTest {
    
    private NumSquares solution;
    
    @BeforeEach
    void setUp() {
        solution = new NumSquares();
    }
    
    /**
     * 测试用例1：示例1
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     */
    @Test
    void testExample1() {
        int n = 12;
        int expected = 3;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例2：示例2
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     */
    @Test
    void testExample2() {
        int n = 13;
        int expected = 2;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例3：n = 1（最小值）
     * 输入：n = 1
     * 输出：1
     * 解释：1 = 1
     */
    @Test
    void testMinValue() {
        int n = 1;
        int expected = 1;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例4：n 本身是完全平方数
     * 输入：n = 4
     * 输出：1
     * 解释：4 = 4
     */
    @Test
    void testPerfectSquare() {
        int n = 4;
        int expected = 1;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例5：n = 9（完全平方数）
     * 输入：n = 9
     * 输出：1
     * 解释：9 = 9
     */
    @Test
    void testPerfectSquare9() {
        int n = 9;
        int expected = 1;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例6：n = 2（需要2个完全平方数）
     * 输入：n = 2
     * 输出：2
     * 解释：2 = 1 + 1
     */
    @Test
    void testTwo() {
        int n = 2;
        int expected = 2;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例7：n = 3（需要3个完全平方数）
     * 输入：n = 3
     * 输出：3
     * 解释：3 = 1 + 1 + 1
     */
    @Test
    void testThree() {
        int n = 3;
        int expected = 3;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例8：n = 7（需要4个完全平方数）
     * 输入：n = 7
     * 输出：4
     * 解释：7 = 4 + 1 + 1 + 1
     * 注意：7 = 4^0 * (8*0 + 7)，符合四平方和定理的特殊形式
     */
    @Test
    void testSeven() {
        int n = 7;
        int expected = 4;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例9：n = 10（需要2个完全平方数）
     * 输入：n = 10
     * 输出：2
     * 解释：10 = 9 + 1
     */
    @Test
    void testTen() {
        int n = 10;
        int expected = 2;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例10：n = 15（需要4个完全平方数）
     * 输入：n = 15
     * 输出：4
     * 解释：15 = 9 + 4 + 1 + 1
     * 注意：15 = 4^0 * (8*1 + 7)，符合四平方和定理的特殊形式
     */
    @Test
    void testFifteen() {
        int n = 15;
        int expected = 4;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例11：n = 100（完全平方数）
     * 输入：n = 100
     * 输出：1
     * 解释：100 = 100
     */
    @Test
    void testHundred() {
        int n = 100;
        int expected = 1;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例12：n = 63（需要4个完全平方数）
     * 输入：n = 63
     * 输出：4
     * 解释：63 = 49 + 9 + 4 + 1
     * 注意：63 = 4^0 * (8*7 + 7)，符合四平方和定理的特殊形式
     */
    @Test
    void testSixtyThree() {
        int n = 63;
        int expected = 4;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例13：n = 48（需要3个完全平方数）
     * 输入：n = 48
     * 输出：3
     * 解释：48 = 16 + 16 + 16
     */
    @Test
    void testFortyEight() {
        int n = 48;
        int expected = 3;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例14：n = 6（需要3个完全平方数）
     * 输入：n = 6
     * 输出：3
     * 解释：6 = 4 + 1 + 1
     */
    @Test
    void testSix() {
        int n = 6;
        int expected = 3;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例15：较大的数 n = 9999
     * 输入：n = 9999
     * 输出：4
     * 解释：9999 = 9801 + 196 + 1 + 1 = 99^2 + 14^2 + 1 + 1
     */
    @Test
    void testLargeNumber() {
        int n = 9999;
        int expected = 4;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    /**
     * 测试用例16：n = 5（需要2个完全平方数）
     * 输入：n = 5
     * 输出：2
     * 解释：5 = 4 + 1
     */
    @Test
    void testFive() {
        int n = 5;
        int expected = 2;
        
        assertEquals(expected, solution.numSquares(n));
    }
    
    // ========== 测试 BFS 方法 ==========
    
    /**
     * 测试 BFS 方法：示例1
     */
    @Test
    void testBFSExample1() {
        int n = 12;
        int expected = 3;
        
        assertEquals(expected, solution.numSquaresBFS(n));
    }
    
    /**
     * 测试 BFS 方法：示例2
     */
    @Test
    void testBFSExample2() {
        int n = 13;
        int expected = 2;
        
        assertEquals(expected, solution.numSquaresBFS(n));
    }
    
    /**
     * 测试 BFS 方法：完全平方数
     */
    @Test
    void testBFSPerfectSquare() {
        int n = 16;
        int expected = 1;
        
        assertEquals(expected, solution.numSquaresBFS(n));
    }
    
    /**
     * 测试 BFS 方法：需要4个完全平方数
     */
    @Test
    void testBFSFour() {
        int n = 7;
        int expected = 4;
        
        assertEquals(expected, solution.numSquaresBFS(n));
    }
    
    // ========== 测试数学定理方法 ==========
    
    /**
     * 测试数学定理方法：示例1
     */
    @Test
    void testMathExample1() {
        int n = 12;
        int expected = 3;
        
        assertEquals(expected, solution.numSquaresMath(n));
    }
    
    /**
     * 测试数学定理方法：示例2
     */
    @Test
    void testMathExample2() {
        int n = 13;
        int expected = 2;
        
        assertEquals(expected, solution.numSquaresMath(n));
    }
    
    /**
     * 测试数学定理方法：完全平方数
     */
    @Test
    void testMathPerfectSquare() {
        int n = 25;
        int expected = 1;
        
        assertEquals(expected, solution.numSquaresMath(n));
    }
    
    /**
     * 测试数学定理方法：需要4个完全平方数
     */
    @Test
    void testMathFour() {
        int n = 7;
        int expected = 4;
        
        assertEquals(expected, solution.numSquaresMath(n));
    }
    
    // ========== 测试多种方法一致性 ==========
    
    /**
     * 测试三种方法的一致性
     * 所有方法应该产生相同的结果
     */
    @Test
    void testMethodsConsistency() {
        int[] testCases = {1, 2, 3, 4, 5, 6, 7, 10, 12, 13, 15, 48, 63, 100};
        
        for (int n : testCases) {
            int result1 = solution.numSquares(n);
            int result2 = solution.numSquaresBFS(n);
            int result3 = solution.numSquaresMath(n);
            
            assertEquals(result1, result2, 
                "DP and BFS should produce same result for n=" + n);
            assertEquals(result1, result3, 
                "DP and Math should produce same result for n=" + n);
        }
    }
}

