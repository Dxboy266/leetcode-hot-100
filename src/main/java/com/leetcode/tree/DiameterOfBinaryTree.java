package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

/**
 * 543. 二叉树的直径
 * 
 * 题目链接：https://leetcode.cn/problems/diameter-of-binary-tree/
 * 难度：简单
 * 标签：树、深度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。
 * 这条路径可能经过也可能不经过根节点 root 。
 * 
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 
 * 示例 2：
 * 输入：root = [1,2]
 * 输出：1
 * 
 * 提示：
 * - 树中节点数目在范围 [1, 10^4] 内
 * - -100 <= Node.val <= 100
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心思想】：后序遍历 + 递归
 * 
 * 【关键理解】：
 * - 直径 = 左子树的最大深度 + 右子树的最大深度
 * - 对于每个节点，计算经过该节点的最长路径
 * - 全局记录所有节点的最大直径
 * 
 * 【解题思路】：
 * 1. 使用后序遍历（左 → 右 → 根）
 * 2. 递归计算每个节点的深度
 * 3. 在递归过程中，更新全局最大直径：
 *    diameter = max(diameter, leftDepth + rightDepth)
 * 4. 返回当前节点的深度：max(leftDepth, rightDepth) + 1
 * 
 * 【递归函数设计】：
 * - 返回值：当前节点的深度
 * - 副作用：更新全局最大直径
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * ⚠️ 注意：
 * - 直径是边数，不是节点数
 * - 最长路径不一定经过根节点
 * - 需要使用全局变量或成员变量记录最大直径
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class DiameterOfBinaryTree {

    // 成员变量记录最大直径
    private int maxDiameter;

    /**
     * 计算二叉树的直径
     * 
     * 核心思路：直径 = 左子树深度 + 右子树深度
     * 
     * @param root 二叉树根节点
     * @return 直径长度
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 每次调用时重置最大直径
        maxDiameter = 0;
        // 调用辅助函数计算深度
        depth(root);
        return maxDiameter;
    }

    /**
     * 辅助函数：计算节点深度
     * 副作用：更新全局最大直径
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // 递归计算左右子树深度
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);

        // 更新最大直径（经过当前节点的路径长度）
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 返回当前节点的深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

