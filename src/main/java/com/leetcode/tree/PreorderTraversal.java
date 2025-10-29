package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 * 
 * 题目链接：https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 难度：简单
 * 标签：树、深度优先搜索、二叉树、栈
 * 
 * ==================== 题目描述 ====================
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 
 * 示例 1：
 * 输入：root = [1,null,2,3]
 *       1
 *        \
 *         2
 *        /
 *       3
 * 输出：[1,2,3]
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
 * 【前序遍历顺序】：根 → 左 → 右
 * 
 * 【方法一：递归】⭐ 推荐
 * 核心思想：
 * 1. 访问根节点
 * 2. 递归遍历左子树
 * 3. 递归遍历右子树
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度（递归栈）
 * 
 * 【方法二：迭代（使用栈）】
 * 核心思想：使用栈模拟递归过程
 * 关键点：
 * - 先访问根节点
 * - 右子节点先入栈（后访问）
 * - 左子节点后入栈（先访问）
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class PreorderTraversal {
    
    /**
     * 前序遍历（递归实现）⭐ 推荐
     * 
     * 核心思路：根 → 左 → 右
     * 
     * @param root 二叉树根节点
     * @return 前序遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
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
        
        // 前序遍历：根 → 左 → 右
        result.add(root.val);           // 访问根节点
        traverse(root.left, result);    // 遍历左子树
        traverse(root.right, result);   // 遍历右子树
    }
    
    /**
     * 前序遍历（迭代实现）
     * 
     * 核心思路：使用栈模拟递归
     * 
     * @param root 二叉树根节点
     * @return 前序遍历结果
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);           // 访问根节点
            
            // 右子节点先入栈（后访问）
            if (node.right != null) {
                stack.push(node.right);
            }
            // 左子节点后入栈（先访问）
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return result;
    }
}

