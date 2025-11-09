package com.leetcode.contest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

/**
 * 网格中的最大分数 - 测试用例
 */
@DisplayName("网格中的最大分数")
class MaxScoreInGridTest {

    private final MaxScoreInGrid solution = new MaxScoreInGrid();

    // ==================== 示例测试 ====================

    @Test
    @DisplayName("示例1：基本路径")
    void testExample1() {
        int[][] grid = {{0, 1}, {2, 0}};
        int k = 1;
        assertEquals(2, solution.maxScore(grid, k));
        // 解释：路径 (0,0) -> (1,0) -> (1,1)
        // 分数：0 + 2 + 0 = 2
        // 花费：0 + 1 + 0 = 1
    }

    @Test
    @DisplayName("示例2：无有效路径")
    void testExample2() {
        int[][] grid = {{0, 1}, {1, 2}};
        int k = 1;
        assertEquals(-1, solution.maxScore(grid, k));
        // 解释：任何路径的花费都超过 k=1
    }

    // ==================== 边界测试 ====================

    @Test
    @DisplayName("边界：1x1网格")
    void testSingleCell() {
        int[][] grid = {{0}};
        int k = 0;
        assertEquals(0, solution.maxScore(grid, k));
        // 解释：起点即终点，分数为0，花费为0
    }

    @Test
    @DisplayName("边界：k=0，只能走0单元格")
    void testZeroBudget() {
        int[][] grid = {{0, 0}, {0, 0}};
        int k = 0;
        assertEquals(0, solution.maxScore(grid, k));
        // 解释：所有单元格都是0，花费为0，分数为0
    }

    @Test
    @DisplayName("边界：k=0，存在非0单元格")
    void testZeroBudgetWithNonZero() {
        int[][] grid = {{0, 1}, {0, 0}};
        int k = 0;
        assertEquals(0, solution.maxScore(grid, k));
        // 解释：只能走 (0,0) -> (1,0) -> (1,1)，分数为0
    }

    @Test
    @DisplayName("边界：k很大，可以走任意路径")
    void testLargeBudget() {
        int[][] grid = {{0, 2, 2}, {2, 2, 2}, {2, 2, 0}};
        int k = 100;
        assertEquals(6, solution.maxScore(grid, k));
        // 解释：最短路径经过5个单元格，其中3个2（起点和终点都是0）
        // 分数：2*3 = 6，花费：3
    }

    // ==================== 功能测试 ====================

    @Test
    @DisplayName("功能：全是0")
    void testAllZeros() {
        int[][] grid = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int k = 0;
        assertEquals(0, solution.maxScore(grid, k));
        // 解释：所有单元格都是0，分数为0，花费为0
    }

    @Test
    @DisplayName("功能：全是1")
    void testAllOnes() {
        int[][] grid = {{0, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        int k = 4;
        assertEquals(3, solution.maxScore(grid, k));
        // 解释：最短路径经过5个单元格，其中3个1（起点和终点都是0）
        // 分数：1*3 = 3，花费：3
    }

    @Test
    @DisplayName("功能：全是2")
    void testAllTwos() {
        int[][] grid = {{0, 2, 2}, {2, 2, 2}, {2, 2, 0}};
        int k = 4;
        assertEquals(6, solution.maxScore(grid, k));
        // 解释：最短路径经过5个单元格，其中3个2（起点和终点都是0）
        // 分数：2*3 = 6，花费：3
    }

    @Test
    @DisplayName("功能：混合值")
    void testMixedValues() {
        int[][] grid = {{0, 1, 2}, {2, 0, 1}, {1, 2, 0}};
        int k = 3;
        assertEquals(5, solution.maxScore(grid, k));
        // 解释：可能的路径之一
    }

    // ==================== 特殊情况测试 ====================

    @Test
    @DisplayName("特殊：k刚好够最短路径")
    void testExactBudget() {
        int[][] grid = {{0, 1}, {1, 0}};
        int k = 1;
        assertEquals(1, solution.maxScore(grid, k));
        // 解释：路径 (0,0)[0] -> (0,1)[1] -> (1,1)[0] 或 (0,0)[0] -> (1,0)[1] -> (1,1)[0]
        // 分数：0 + 1 + 0 = 1，花费：0 + 1 + 0 = 1
    }

    @Test
    @DisplayName("特殊：k不够最短路径")
    void testInsufficientBudget() {
        int[][] grid = {{0, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        int k = 2;
        assertEquals(-1, solution.maxScore(grid, k));
        // 解释：最短路径经过3个1，花费为3，超过k=2，所以无法到达终点
    }

    @Test
    @DisplayName("特殊：优先选择2而非1")
    void testPreferTwoOverOne() {
        int[][] grid = {{0, 2}, {1, 0}};
        int k = 1;
        assertEquals(2, solution.maxScore(grid, k));
        // 解释：路径 (0,0) -> (0,1) -> (1,1)
        // 分数：0 + 2 + 0 = 2，花费：0 + 1 + 0 = 1
        // 优于路径 (0,0) -> (1,0) -> (1,1)，分数为1
    }

    @Test
    @DisplayName("特殊：绕路获得更高分数")
    void testDetourForHigherScore() {
        int[][] grid = {{0, 0, 2}, {0, 2, 0}, {2, 0, 0}};
        int k = 3;
        assertEquals(2, solution.maxScore(grid, k));
        // 解释：只能向右或向下移动，最多经过1个2
        // 最优路径：(0,0)[0] -> (0,1)[0] -> (0,2)[2] -> (1,2)[0] -> (2,2)[0]
        // 分数：2，花费：1
    }

    // ==================== 性能测试 ====================

    @Test
    @DisplayName("性能：大网格（50x50）")
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testLargeGrid() {
        int m = 50, n = 50;
        int[][] grid = new int[m][n];
        // 创建一个棋盘模式：0和2交替
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = (i + j) % 2 == 0 ? 0 : 2;
                }
            }
        }
        int k = 50;
        int result = solution.maxScore(grid, k);
        assertTrue(result >= 0, "应该能找到有效路径");
    }

    @Test
    @DisplayName("性能：大网格（100x100）")
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    void testVeryLargeGrid() {
        int m = 100, n = 100;
        int[][] grid = new int[m][n];
        // 全是0，最简单的情况
        int k = 0;
        int result = solution.maxScore(grid, k);
        assertEquals(0, result);
    }

    // ==================== 对比测试（可选） ====================

    @Test
    @DisplayName("对比：三种解法结果一致")
    void testAllMethodsConsistent() {
        int[][] grid = {{0, 1, 2}, {2, 0, 1}, {1, 2, 0}};
        int k = 3;
        
        int result1 = solution.maxScore(grid, k);
        int result2 = solution.maxScoreOptimized(grid, k);
        int result3 = solution.maxScoreDFS(grid, k);
        
        assertEquals(result1, result2, "DP解法和空间优化解法应该结果相同");
        assertEquals(result1, result3, "DP解法和DFS解法应该结果相同");
    }
}

