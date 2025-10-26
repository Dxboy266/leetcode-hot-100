package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 
 * 题目链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 难度：简单
 * 标签：树、深度优先搜索、二叉树、栈
 * 
 * ==================== 题目描述 ====================
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历结果。
 * 
 * 示例 1：
 * 输入：root = [1,null,2,3]
 *       1
 *        \
 *         2
 *        /
 *       3
 * 输出：[1,3,2]
 * 
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * 
 * 提示：
 * - 树中节点数目在范围 [0, 100] 内
 * - -100 <= Node.val <= 100
 * 
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * ==================== 解题思路 ====================
 * 
 * 【中序遍历顺序】：左 → 根 → 右
 * 
 * 【方法一：递归】⭐ 推荐
 * 核心思想：
 * 1. 递归遍历左子树
 * 2. 访问根节点
 * 3. 递归遍历右子树
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度（递归栈）
 * 
 * 【方法二：迭代（使用栈）】
 * 核心思想：使用栈模拟递归过程
 * 步骤：
 * 1. 一直向左走到底，将路径节点入栈
 * 2. 弹出栈顶节点并访问
 * 3. 转向右子树，重复步骤1
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class InorderTraversal {
    
    /**
     * 中序遍历（递归实现）⭐ 推荐
     * 
     * 核心思路：左 → 根 → 右
     * 
     * @param root 二叉树根节点
     * @return 中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    /**
     * 递归辅助函数
     */
    private void traverse(TreeNode root, List<Integer> result) {
        // 递归终止条件
        if (root == null) {
            return;
        }
        
        // 中序遍历：左 → 根 → 右
        traverse(root.left, result);    // 遍历左子树
        result.add(root.val);           // 访问根节点
        traverse(root.right, result);   // 遍历右子树
    }

    /**
     * 中序遍历（迭代实现）
     * 
     * 核心思路：使用栈模拟递归过程
     * 
     * @param root 二叉树根节点
     * @return 中序遍历结果
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 一直向左走到底，将路径节点入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 弹出栈顶节点并访问
            current = stack.pop();
            result.add(current.val);

            // 转向右子树
            current = current.right;
        }
        
        return result;
    }
}


