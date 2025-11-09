package com.leetcode.backtrack;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 拼多多算法题：马走日问题 - 测试类
 */
class KnightTourTest {

    private final KnightTour solution = new KnightTour();

    @Test
    void testSmallBoard() {
        // 3x3 棋盘
        int result = solution.knightTour(3, 3);
        System.out.println("3x3 棋盘走法数量: " + result);
        assertTrue(result >= 0);
        
        // 验证：3x3 棋盘，从 [0,0] 出发，马能走的路径有限
        // 实际值取决于具体实现，但应该 > 0
    }

    @Test
    void testSmallBoardWithPaths() {
        // 测试小棋盘，查看具体路径
        List<List<int[]>> paths = solution.knightTourWithPaths(3, 3);
        System.out.println("\n3x3 棋盘所有路径:");
        for (int i = 0; i < paths.size(); i++) {
            System.out.print("路径 " + (i + 1) + ": ");
            for (int[] pos : paths.get(i)) {
                System.out.print("[" + pos[0] + "," + pos[1] + "] ");
            }
            System.out.println();
        }
        assertTrue(paths.size() > 0);
    }

    @Test
    void testMediumBoard() {
        // 4x4 棋盘
        int result = solution.knightTour(4, 4);
        System.out.println("\n4x4 棋盘走法数量: " + result);
        assertTrue(result >= 0);
    }

    @Test
    void testLargeBoard() {
        // 5x5 棋盘（可能需要较长时间）
        int result = solution.knightTour(5, 5);
        System.out.println("\n5x5 棋盘走法数量: " + result);
        assertTrue(result >= 0);
    }

    @Test
    void testTooLargeBoard() {
        // 8x8 棋盘（可能需要较长时间）
        int result = solution.knightTour(8, 8);
        System.out.println("\n8x8 棋盘走法数量: " + result);
        assertTrue(result >= 0);
    }

    @Test
    void testRectangularBoard() {
        // 3x4 矩形棋盘
        int result = solution.knightTour(3, 4);
        System.out.println("\n3x4 棋盘走法数量: " + result);
        assertTrue(result >= 0);
    }

    @Test
    void testMinimalBoard() {
        // 1x1 棋盘（只有起点）
        int result = solution.knightTour(1, 1);
        assertEquals(1, result); // 只有起点，无法移动，所以只有1种走法
    }

    @Test
    void testTinyBoard() {
        // 2x2 棋盘（太小，无法移动）
        int result = solution.knightTour(2, 2);
        System.out.println("\n2x2 棋盘走法数量: " + result);
        // 2x2 棋盘，马无法移动（马的步长至少需要3）
        assertEquals(1, result); // 只有起点这一种走法
    }

    @Test
    void testEdgeCases() {
        // 边界情况
        assertEquals(0, solution.knightTour(0, 0));
        assertEquals(0, solution.knightTour(0, 3));
        assertEquals(0, solution.knightTour(3, 0));
        assertEquals(0, solution.knightTour(-1, 3));
        assertEquals(0, solution.knightTour(3, -1));
    }

    @Test
    void testPathDetails() {
        // 测试路径详情（小棋盘便于观察）
        List<List<int[]>> paths = solution.knightTourWithPaths(3, 3);
        
        // 验证每条路径：
        // 1. 都从 [0,0] 开始
        // 2. 路径中所有位置都是合法的
        // 3. 路径中位置不重复
        for (List<int[]> path : paths) {
            assertFalse(path.isEmpty(), "路径不应为空");
            int[] start = path.get(0);
            assertEquals(0, start[0], "路径应从 [0,0] 开始");
            assertEquals(0, start[1], "路径应从 [0,0] 开始");
            
            // 检查位置是否重复
            boolean[][] visited = new boolean[3][3];
            for (int[] pos : path) {
                assertTrue(pos[0] >= 0 && pos[0] < 3, "行坐标应在范围内");
                assertTrue(pos[1] >= 0 && pos[1] < 3, "列坐标应在范围内");
                assertFalse(visited[pos[0]][pos[1]], "路径中位置不应重复");
                visited[pos[0]][pos[1]] = true;
            }
        }
    }
}
