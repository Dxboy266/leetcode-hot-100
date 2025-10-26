package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 * 
 * 题目链接：https://leetcode.cn/problems/invert-binary-tree/
 * 难度：简单
 * 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 *       4                4
 *      / \              / \
 *     2   7    =>      7   2
 *    / \ / \          / \ / \
 *   1  3 6  9        9  6 3  1
 * 输出：[4,7,2,9,6,3,1]
 * 
 * 示例 2：
 * 输入：root = [2,1,3]
 *       2              2
 *      / \    =>      / \
 *     1   3          3   1
 * 输出：[2,3,1]
 * 
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * 
 * 提示：
 * - 树中节点数目范围在 [0, 100] 内
 * - -100 <= Node.val <= 100
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心思想】：交换每个节点的左右子树
 * 
 * 【方法一：递归】
 * 思路：
 * 1. 递归翻转左子树
 * 2. 递归翻转右子树
 * 3. 交换左右子树
 * 
 * 【方法二：迭代（使用队列）】
 * 思路：
 * 1. 使用队列进行层序遍历
 * 2. 对每个节点交换其左右子节点
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class InvertTree {
    
    /**
     * 翻转二叉树
     * 
     * @param root 二叉树根节点
     * @return 翻转后的根节点
     */
    public TreeNode invertTree(TreeNode root) {
        // 1. 递归终止条件：root == null，返回 null
        if (root == null) {
            return null;
        }
        // 2. 递归翻转左子树
        TreeNode left = invertTree(root.left);
        // 3. 递归翻转右子树
        TreeNode right = invertTree(root.right);
        // 4. 交换左右子树
        root.left = right;
        root.right = left;
        // 5. 返回 root
        return root;
    }

    // 迭代（使用队列）
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                TreeNode left = curr.left;
                TreeNode right = curr.right;
                curr.left = right;
                curr.right = left;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }
}

