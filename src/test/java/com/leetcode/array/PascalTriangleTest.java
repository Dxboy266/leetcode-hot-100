package com.leetcode.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PascalTriangle 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试（示例用例）
 * - 边界条件测试（最小值、最大值）
 * - 特殊情况测试
 * - 多种方法一致性测试
 * 
 * @Author Dxboy266
 * @Date 2025-11-19
 */
class PascalTriangleTest {
    
    private PascalTriangle solution;
    
    @BeforeEach
    void setUp() {
        solution = new PascalTriangle();
    }
    
    /**
     * 测试用例1：示例1
     * 输入：numRows = 5
     * 输出：[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */
    @Test
    void testExample1() {
        int numRows = 5;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(1, 1),
            Arrays.asList(1, 2, 1),
            Arrays.asList(1, 3, 3, 1),
            Arrays.asList(1, 4, 6, 4, 1)
        );
        
        assertEquals(expected, solution.generate(numRows));
    }
    
    /**
     * 测试用例2：示例2
     * 输入：numRows = 1
     * 输出：[[1]]
     */
    @Test
    void testExample2() {
        int numRows = 1;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1)
        );
        
        assertEquals(expected, solution.generate(numRows));
    }
    
    /**
     * 测试用例3：两行
     * 输入：numRows = 2
     * 输出：[[1],[1,1]]
     */
    @Test
    void testTwoRows() {
        int numRows = 2;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(1, 1)
        );
        
        assertEquals(expected, solution.generate(numRows));
    }
    
    /**
     * 测试用例4：三行
     * 输入：numRows = 3
     * 输出：[[1],[1,1],[1,2,1]]
     */
    @Test
    void testThreeRows() {
        int numRows = 3;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(1, 1),
            Arrays.asList(1, 2, 1)
        );
        
        assertEquals(expected, solution.generate(numRows));
    }
    
    /**
     * 测试用例5：较大的行数
     * 输入：numRows = 10
     * 验证：第10行应该是 [1,9,36,84,126,126,84,36,9,1]
     */
    @Test
    void testTenRows() {
        int numRows = 10;
        List<List<Integer>> result = solution.generate(numRows);
        
        // 验证行数
        assertEquals(10, result.size());
        
        // 验证第10行（索引为9）
        List<Integer> lastRow = result.get(9);
        List<Integer> expectedLastRow = Arrays.asList(1, 9, 36, 84, 126, 126, 84, 36, 9, 1);
        assertEquals(expectedLastRow, lastRow);
    }
    
    /**
     * 测试用例6：验证对称性
     * 杨辉三角的每一行都应该是对称的
     */
    @Test
    void testSymmetry() {
        int numRows = 7;
        List<List<Integer>> result = solution.generate(numRows);
        
        for (List<Integer> row : result) {
            // 验证每一行都是对称的
            int left = 0, right = row.size() - 1;
            while (left < right) {
                assertEquals(row.get(left), row.get(right), 
                    "Row should be symmetric: " + row);
                left++;
                right--;
            }
        }
    }
    
    /**
     * 测试用例7：验证每行元素个数
     * 第 i 行应该有 i 个元素
     */
    @Test
    void testRowSize() {
        int numRows = 8;
        List<List<Integer>> result = solution.generate(numRows);
        
        for (int i = 0; i < numRows; i++) {
            assertEquals(i + 1, result.get(i).size(), 
                "Row " + (i + 1) + " should have " + (i + 1) + " elements");
        }
    }
    
    /**
     * 测试用例8：验证边界元素都是1
     * 每一行的第一个和最后一个元素都应该是1
     */
    @Test
    void testBoundaryElements() {
        int numRows = 6;
        List<List<Integer>> result = solution.generate(numRows);
        
        for (List<Integer> row : result) {
            assertEquals(1, row.get(0), "First element should be 1");
            assertEquals(1, row.get(row.size() - 1), "Last element should be 1");
        }
    }
    
    // ========== 测试数学公式方法 ==========
    
    /**
     * 测试数学公式方法：示例1
     */
    @Test
    void testMathExample1() {
        int numRows = 5;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(1, 1),
            Arrays.asList(1, 2, 1),
            Arrays.asList(1, 3, 3, 1),
            Arrays.asList(1, 4, 6, 4, 1)
        );
        
        assertEquals(expected, solution.generateMath(numRows));
    }
    
    /**
     * 测试数学公式方法：边界情况
     */
    @Test
    void testMathBoundary() {
        int numRows = 1;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1)
        );
        
        assertEquals(expected, solution.generateMath(numRows));
    }
    
    /**
     * 测试两种方法的一致性
     * 两种方法应该产生相同的结果
     */
    @Test
    void testMethodsConsistency() {
        for (int numRows = 1; numRows <= 15; numRows++) {
            List<List<Integer>> result1 = solution.generate(numRows);
            List<List<Integer>> result2 = solution.generateMath(numRows);
            
            assertEquals(result1, result2, 
                "Methods should produce same result for numRows = " + numRows);
        }
    }
}

