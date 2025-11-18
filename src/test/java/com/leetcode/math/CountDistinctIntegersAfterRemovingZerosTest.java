package com.leetcode.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CountDistinctIntegersAfterRemovingZeros 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试
 * - 边界条件测试
 * - 大数据测试
 * - 性能测试
 */
class CountDistinctIntegersAfterRemovingZerosTest {
    
    private CountDistinctIntegersAfterRemovingZeros solution;
    
    @BeforeEach
    void setUp() {
        solution = new CountDistinctIntegersAfterRemovingZeros();
    }
    
    /**
     * 测试用例1：示例1
     * 输入：n = 10
     * 输出：9
     * 解释：1-9 移除0后还是 1-9，10 移除0后是 1（重复）
     */
    @Test
    @DisplayName("示例1：n = 10 -> 9")
    void testExample1() {
        assertEquals(9, solution.countDistinct(10));
        assertEquals(9, solution.countDistinctMath(10));
    }
    
    /**
     * 测试用例2：示例2
     * 输入：n = 3
     * 输出：3
     */
    @Test
    @DisplayName("示例2：n = 3 -> 3")
    void testExample2() {
        assertEquals(3, solution.countDistinct(3));
        assertEquals(3, solution.countDistinctMath(3));
    }
    
    /**
     * 测试用例3：n = 21
     * 输入：n = 21
     * 输出：19
     * 解释：
     * 1-9: 9个
     * 10: 移除0后是 1（重复）
     * 11-19: 移除0后是 11-19，9个
     * 20: 移除0后是 2（重复）
     * 21: 移除0后是 21，1个
     * 总共：9 + 9 + 1 = 19
     */
    @Test
    @DisplayName("n = 21 -> 19")
    void testN21() {
        assertEquals(19, solution.countDistinct(21));
        assertEquals(19, solution.countDistinctMath(21));
    }
    
    /**
     * 测试用例4：n = 100
     * 输入：n = 100
     * 输出：90
     */
    @Test
    @DisplayName("n = 100 -> 90")
    void testN100() {
        assertEquals(90, solution.countDistinct(100));
        assertEquals(90, solution.countDistinctMath(100));
    }
    
    /**
     * 测试用例5：边界条件 - n = 1
     */
    @Test
    @DisplayName("边界条件：n = 1 -> 1")
    void testN1() {
        assertEquals(1, solution.countDistinct(1));
        assertEquals(1, solution.countDistinctMath(1));
    }
    
    /**
     * 测试用例6：边界条件 - n = 9
     */
    @Test
    @DisplayName("边界条件：n = 9 -> 9")
    void testN9() {
        assertEquals(9, solution.countDistinct(9));
        assertEquals(9, solution.countDistinctMath(9));
    }
    
    /**
     * 测试用例7：n = 99
     */
    @Test
    @DisplayName("n = 99 -> 90")
    void testN99() {
        assertEquals(90, solution.countDistinct(99));
        assertEquals(90, solution.countDistinctMath(99));
    }
    
    /**
     * 测试用例8：n = 1000
     */
    @Test
    @DisplayName("n = 1000 -> 738")
    void testN1000() {
        // 暴力法验证
        long expected = solution.countDistinctBruteForce(1000);
        assertEquals(expected, solution.countDistinct(1000));
        assertEquals(expected, solution.countDistinctMath(1000));
    }
    
    /**
     * 测试用例9：包含多个0的数字
     * n = 10000
     */
    @Test
    @DisplayName("n = 10000")
    void testN10000() {
        long result1 = solution.countDistinct(10000);
        long result2 = solution.countDistinctMath(10000);
        assertEquals(result1, result2);
        assertTrue(result1 > 0);
    }
    
    /**
     * 测试用例10：大数据测试 - 10^8
     */
    @Test
    @DisplayName("大数据测试：n = 10^8")
    void testLargeN1() {
        long n = 100_000_000L; // 10^8
        long result1 = solution.countDistinct(n);
        long result2 = solution.countDistinctMath(n);
        assertEquals(result1, result2);
        assertTrue(result1 > 0);
    }
    
    /**
     * 测试用例11：大数据测试 - 10^12
     */
    @Test
    @DisplayName("大数据测试：n = 10^12")
    void testLargeN2() {
        long n = 1_000_000_000_000L; // 10^12
        long result1 = solution.countDistinct(n);
        long result2 = solution.countDistinctMath(n);
        assertEquals(result1, result2);
        assertTrue(result1 > 0);
    }
    
    /**
     * 测试用例12：大数据测试 - 10^15
     */
    @Test
    @DisplayName("大数据测试：n = 10^15")
    void testLargeN3() {
        long n = 1_000_000_000_000_000L; // 10^15
        long result1 = solution.countDistinct(n);
        long result2 = solution.countDistinctMath(n);
        assertEquals(result1, result2);
        assertTrue(result1 > 0);
    }
    
    /**
     * 测试用例13：性能测试 - 验证数位DP比暴力法快
     */
    @Test
    @DisplayName("性能测试：数位DP vs 暴力法")
    void testPerformance() {
        long n = 100000; // 10^5
        
        // 暴力法
        long start1 = System.nanoTime();
        long result1 = solution.countDistinctBruteForce(n);
        long time1 = System.nanoTime() - start1;
        
        // 数位DP
        long start2 = System.nanoTime();
        long result2 = solution.countDistinct(n);
        long time2 = System.nanoTime() - start2;
        
        // 数学法
        long start3 = System.nanoTime();
        long result3 = solution.countDistinctMath(n);
        long time3 = System.nanoTime() - start3;
        
        // 验证结果一致
        assertEquals(result1, result2);
        assertEquals(result1, result3);
        
        // 打印性能对比
        System.out.println("n = " + n);
        System.out.println("暴力法耗时: " + time1 / 1_000_000 + " ms");
        System.out.println("数位DP耗时: " + time2 / 1_000_000 + " ms");
        System.out.println("数学法耗时: " + time3 / 1_000_000 + " ms");
        System.out.println("数位DP加速比: " + (double) time1 / time2 + "x");
        System.out.println("数学法加速比: " + (double) time1 / time3 + "x");
    }
    
    /**
     * 测试用例14：验证 removeZeros 方法
     */
    @Test
    @DisplayName("验证 removeZeros 方法")
    void testRemoveZeros() {
        CountDistinctIntegersAfterRemovingZeros sol = new CountDistinctIntegersAfterRemovingZeros();
        
        // 使用反射访问私有方法进行测试
        try {
            java.lang.reflect.Method method = CountDistinctIntegersAfterRemovingZeros.class
                .getDeclaredMethod("removeZeros", long.class);
            method.setAccessible(true);
            
            assertEquals(1L, method.invoke(sol, 10L));
            assertEquals(1L, method.invoke(sol, 100L));
            assertEquals(1L, method.invoke(sol, 1000L));
            assertEquals(123L, method.invoke(sol, 123L));
            assertEquals(123L, method.invoke(sol, 1203L));
            assertEquals(123L, method.invoke(sol, 10203L));
            assertEquals(99L, method.invoke(sol, 9009L));
        } catch (Exception e) {
            fail("反射调用失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试用例15：特殊数字 - 全是9
     * 解释：
     * 1位数：1-9，共9个
     * 2位数：11-99（不含0），共 9*9 = 81个
     * 3位数：111-999（不含0），共 9*9*9 = 729个
     * 总共：9 + 81 + 729 = 819个
     */
    @Test
    @DisplayName("特殊数字：n = 999")
    void testAllNines() {
        assertEquals(819, solution.countDistinct(999));
        assertEquals(819, solution.countDistinctMath(999));
    }
    
    /**
     * 测试用例16：特殊数字 - 包含0的数字
     */
    @Test
    @DisplayName("特殊数字：n = 505")
    void testWithZeros() {
        long result1 = solution.countDistinct(505);
        long result2 = solution.countDistinctMath(505);
        assertEquals(result1, result2);
    }
}

