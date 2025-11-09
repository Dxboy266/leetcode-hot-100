package com.leetcode.backtrack;

/**
 * 拼多多算法题：马走日问题（Knight Tour 变种）
 * 
 * 题目描述：
 * 对于一个 n*m 的二维数组，给定一个点 [i,j]，下一步能够走的点为：
 * [i+1,j+2] [i-1,j+2] [i-1,j-2] [i+1,j-2]
 * [i-2,j+1] [i-2,j-1] [i+2,j+1] [i+2,j-1]
 * 中的一种。
 * 
 * 如果从 [0,0] 开始出发，每个位置不能重复走，一直到无法走下一步为止，为一种走法。
 * 一共有多少种走法？
 * 
 * 示例：
 * 输入：n = 3, m = 3
 * 输出：某种走法的数量
 * 
 * 说明：
 * - 这是经典的"马走日"问题（中国象棋中的马）
 * - 马可以走"日"字，即从一个点走L形路径
 * - 要求：不能重复走，走到无法继续为止
 * 
 * ==================== 解题思路 ====================
 * 
 * 核心：回溯算法（DFS）
 * 
 * 算法步骤：
 * 1. 从起点 [0,0] 开始
 * 2. 尝试所有8个可能的下一步位置
 * 3. 如果位置合法（在边界内且未访问过），则继续递归
 * 4. 回溯：撤销访问标记，尝试其他路径
 * 5. 当无法继续走时，说明找到一种完整的走法，计数+1
 * 
 * 优化：
 * - 使用 visited 数组标记已访问的位置
 * - 8个方向的偏移量数组
 * - 剪枝：提前判断位置是否合法
 * 
 * @Author Dxboy266
 * @Date 2025-11-05
 */
public class KnightTour {

    // 马可以走的8个方向（相对当前位置的偏移量）
    // 格式：[行偏移, 列偏移]
    private static final int[][] DIRECTIONS = {
        {1, 2},   // [i+1, j+2]
        {-1, 2},  // [i-1, j+2]
        {-1, -2}, // [i-1, j-2]
        {1, -2},  // [i+1, j-2]
        {-2, 1},  // [i-2, j+1]
        {-2, -1}, // [i-2, j-1]
        {2, 1},   // [i+2, j+1]
        {2, -1}   // [i+2, j-1]
    };

    /**
     * 主解法：回溯算法（DFS）
     * 
     * 核心思想：
     * - 从起点开始，尝试所有可能的路径
     * - 使用 visited 数组记录已访问的位置
     * - 当无法继续走时，计数+1
     * - 回溯时撤销访问标记
     * 
     * 时间复杂度：O(8^k)，k 为最大步数（最坏情况遍历所有路径）
     * 空间复杂度：O(n*m)，visited 数组和递归栈
     * 
     * @param n 棋盘行数
     * @param m 棋盘列数
     * @return 从 [0,0] 出发的所有走法数量
     */
    public int knightTour(int n, int m) {
        // 边界检查
        if (n <= 0 || m <= 0) {
            return 0;
        }
        
        // visited[i][j] 表示位置 [i,j] 是否已访问
        boolean[][] visited = new boolean[n][m];
        
        // 从 [0,0] 开始
        visited[0][0] = true;
        
        // 使用计数器
        int[] count = new int[1]; // 使用数组以便在递归中修改
        
        // 开始回溯
        backtrack(0, 0, n, m, visited, count);
        
        return count[0];
    }
    
    /**
     * 回溯函数
     * 
     * @param row 当前行
     * @param col 当前列
     * @param n 棋盘行数
     * @param m 棋盘列数
     * @param visited 访问标记数组
     * @param count 计数器
     */
    private void backtrack(int row, int col, int n, int m, 
                          boolean[][] visited, int[] count) {
        // 尝试所有8个方向
        boolean canMove = false;
        
        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            
            // 检查位置是否合法且未访问
            if (isValid(nextRow, nextCol, n, m) && !visited[nextRow][nextCol]) {
                canMove = true;
                
                // 标记为已访问
                visited[nextRow][nextCol] = true;
                
                // 递归到下一个位置
                backtrack(nextRow, nextCol, n, m, visited, count);
                
                // 回溯：撤销访问标记
                visited[nextRow][nextCol] = false;
            }
        }
        
        // 如果无法继续走，说明找到一种完整的走法
        if (!canMove) {
            count[0]++;
        }
    }
    
    /**
     * 检查位置是否合法（在棋盘范围内）
     * 
     * @param row 行
     * @param col 列
     * @param n 棋盘行数
     * @param m 棋盘列数
     * @return 是否合法
     */
    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    /**
     * 调试用解法：回溯算法（返回所有路径详情）
     * 
     * 这个版本会记录每条路径，适合小规模测试和调试
     * 
     * @param n 棋盘行数
     * @param m 棋盘列数
     * @return 所有走法的路径列表
     */
    public java.util.List<java.util.List<int[]>> knightTourWithPaths(int n, int m) {
        if (n <= 0 || m <= 0) {
            return new java.util.ArrayList<>();
        }
        
        boolean[][] visited = new boolean[n][m];
        java.util.List<java.util.List<int[]>> allPaths = new java.util.ArrayList<>();
        java.util.List<int[]> currentPath = new java.util.ArrayList<>();
        
        visited[0][0] = true;
        currentPath.add(new int[]{0, 0});
        
        backtrackWithPaths(0, 0, n, m, visited, currentPath, allPaths);
        
        return allPaths;
    }
    
    private void backtrackWithPaths(int row, int col, int n, int m,
                                    boolean[][] visited,
                                    java.util.List<int[]> currentPath,
                                    java.util.List<java.util.List<int[]>> allPaths) {
        boolean canMove = false;
        
        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            
            if (isValid(nextRow, nextCol, n, m) && !visited[nextRow][nextCol]) {
                canMove = true;
                
                visited[nextRow][nextCol] = true;
                currentPath.add(new int[]{nextRow, nextCol});
                
                backtrackWithPaths(nextRow, nextCol, n, m, visited, currentPath, allPaths);
                
                // 回溯
                visited[nextRow][nextCol] = false;
                currentPath.remove(currentPath.size() - 1);
            }
        }
        
        // 无法继续走，保存当前路径
        if (!canMove) {
            allPaths.add(new java.util.ArrayList<>(currentPath));
        }
    }
}


