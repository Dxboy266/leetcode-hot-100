package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

/**
 * 101. 对称二叉树
 * 
 * 题目链接：https://leetcode.cn/problems/symmetric-tree/
 * 难度：简单
 * 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 *         1
 *        / \
 *       2   2
 *      / \ / \
 *     3  4 4  3
 * 输出：true
 * 
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 *         1
 *        / \
 *       2   2
 *        \   \
 *         3   3
 * 输出：false
 * 
 * 示例 3：
 * 输入：root = [1]
 * 输出：true
 * 
 * 提示：
 * - 树中节点数目在范围 [1, 1000] 内
 * - -100 <= Node.val <= 100
 * 
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心思想】：判断左右子树是否镜像对称
 * 
 * 【方法一：递归】
 * 思路：
 * - 两棵树镜像对称的条件：
 *   1. 两个根节点值相等
 *   2. 左树的左子树 和 右树的右子树 镜像对称
 *   3. 左树的右子树 和 右树的左子树 镜像对称
 * 
 * 【方法二：迭代（使用队列）】
 * 思路：
 * - 使用队列，每次取出两个节点进行比较
 * - 将对应的子节点按镜像顺序入队
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class IsSymmetric {
    
    /**
     * 判断二叉树是否对称
     * 
     * @param root 二叉树根节点
     * @return 是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        // 1. 空树是对称的
        if (root == null) {
            return true;
        }
        // 2. 调用辅助函数判断左右子树是否镜像
        return isMirror(root.left, root.right);
    }
    
    /**
     * 辅助函数：判断两棵树是否镜像对称
     * 
     * @param left 左子树
     * @param right 右子树
     * @return 是否镜像对称
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        // 1. 如果两个节点都为空，返回 true
        if (left == null && right == null) {
            return true;
        }
        // 2. 如果只有一个节点为空，返回 false
        if (left == null || right == null) {
            return false;
        }
        // 3. 如果两个节点值不相等，返回 false
        if (left.val != right.val) {
            return false;
        }
        // 4. 递归判断：
        //    - left.left 和 right.right 是否镜像
        //    - left.right 和 right.left 是否镜像
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}

