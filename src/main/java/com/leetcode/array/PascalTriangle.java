package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 
 * 题目链接：https://leetcode.cn/problems/pascals-triangle/
 * 难度：简单
 * 标签：数组、动态规划
 * 
 * ==================== 题目描述 ====================
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * 示例 1：
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 
 * 示例 2：
 * 输入: numRows = 1
 * 输出: [[1]]
 * 
 * 提示：
 * - 1 <= numRows <= 30
 * 
 * ==================== 解题思路 ====================
 * 方法一：模拟（推荐）★★★
 * 思路：
 * 1. 杨辉三角的特点：
 *    - 每行的第一个和最后一个元素都是 1
 *    - 其他元素等于上一行相邻两个元素之和
 * 2. 逐行生成：
 *    - 第 i 行有 i 个元素
 *    - 第 i 行第 j 个元素 = 第 i-1 行第 j-1 个元素 + 第 i-1 行第 j 个元素
 * 
 * 时间复杂度：O(numRows^2) - 需要生成 1+2+...+numRows 个元素
 * 空间复杂度：O(1) - 不计算返回值的空间
 * 
 * 方法二：数学公式（组合数）
 * 思路：
 * 1. 杨辉三角第 n 行第 k 个元素 = C(n, k) = n! / (k! * (n-k)!)
 *    - 注意：这里的 n 和 k 都是从 0 开始计数
 *    - 例如：第 4 行（索引3）的元素是 C(3,0), C(3,1), C(3,2), C(3,3) = 1, 3, 3, 1
 *
 * 2. 利用组合数的递推关系避免计算阶乘：
 *    - C(n, 0) = 1
 *    - C(n, k) = C(n, k-1) * (n-k+1) / k
 *    - 例如：C(4, 2) = C(4, 1) * (4-2+1) / 2 = 4 * 3 / 2 = 6
 *
 * 3. 为什么这样计算？
 *    - C(n, k) = n! / (k! * (n-k)!)
 *    - C(n, k-1) = n! / ((k-1)! * (n-k+1)!)
 *    - C(n, k) / C(n, k-1) = (n-k+1) / k
 *    - 因此：C(n, k) = C(n, k-1) * (n-k+1) / k
 *
 * 时间复杂度：O(numRows^2)
 * 空间复杂度：O(1) - 不计算返回值
 * 
 * ==================== 知识点总结 ====================
 * 1. 杨辉三角的性质：
 *    - 对称性：每一行都是对称的
 *    - 组合数：第 n 行第 k 个数是组合数 C(n, k)
 *    - 递推关系：每个数是上一行相邻两数之和
 * 
 * 2. 相关题目：
 *    - 119. 杨辉三角 II（返回第 k 行）
 *    - 组合数相关问题
 * 
 * 3. 易错点：
 *    - 边界处理：第一个和最后一个元素
 *    - 索引问题：注意行号和列号的对应关系
 * 
 * @Author Dxboy266
 * @Date 2025-11-19
 */
public class PascalTriangle {
    
    /**
     * 方法一：模拟（推荐）★★★
     * 时间复杂度：O(numRows^2)
     * 空间复杂度：O(1) - 不计算返回值
     * 
     * @param numRows 行数
     * @return 杨辉三角的前 numRows 行
     */
    public List<List<Integer>> generate(int numRows) {
        // 1. 创建结果列表
        List<List<Integer>> result = new ArrayList<>();
        // 2. 逐行生成：
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
            }
            if (i > 0) {
                row.add(1);
            }
            result.add(row);
        }
        return result;
    }
    
    /**
     * 方法二：数学公式（组合数）
     * 时间复杂度：O(numRows^2)
     * 空间复杂度：O(1) - 不计算返回值
     *
     * 使用组合数公式 C(n, k) = C(n, k-1) * (n-k+1) / k 计算每个元素
     *
     * @param numRows 行数
     * @return 杨辉三角的前 numRows 行
     */
    public List<List<Integer>> generateMath(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            // 第 i 行（从0开始）有 i+1 个元素，对应组合数 C(i, 0), C(i, 1), ..., C(i, i)
            long combination = 1; // C(i, 0) = 1

            for (int j = 0; j <= i; j++) {
                row.add((int) combination);

                // 计算下一个组合数：C(i, j+1) = C(i, j) * (i-j) / (j+1)
                if (j < i) {
                    combination = combination * (i - j) / (j + 1);
                }
            }

            result.add(row);
        }

        return result;
    }
}

