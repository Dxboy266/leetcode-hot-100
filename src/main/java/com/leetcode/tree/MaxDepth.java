package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 
 * 题目链接：https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * 难度：简单
 * 标签：树、深度优先搜索、广度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给定一个二叉树 root ，返回其最大深度。
 * 
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 输出：3
 * 
 * 示例 2：
 * 输入：root = [1,null,2]
 * 输出：2
 * 
 * 示例 3：
 * 输入：root = []
 * 输出：0
 * 
 * 示例 4：
 * 输入：root = [0]
 * 输出：1
 * 
 * 提示：
 * - 树中节点的数量在 [0, 10^4] 区间内
 * - -100 <= Node.val <= 100
 * 
 * ==================== 解题提示 ====================
 * 
 * 【方法一：递归（DFS）】
 * 思路：
 * - 树的最大深度 = max(左子树深度, 右子树深度) + 1
 * 
 * 【方法二：迭代（BFS 层序遍历）】
 * 思路：
 * - 使用队列进行层序遍历
 * - 统计层数即为最大深度
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class MaxDepth {
    
    /**
     * 计算二叉树的最大深度
     * 
     * @param root 二叉树根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        // 1. 递归终止条件：root == null，返回 0
        if (root == null) {
            return 0;
        }
        // 2. 递归计算左子树深度
        int leftDepth = maxDepth(root.left);
        // 3. 递归计算右子树深度
        int rightDepth = maxDepth(root.right);
        // 4. 返回 max(左子树深度, 右子树深度) + 1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 迭代（BFS 层序遍历）
    public int maxDepthIterative(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            depth++;
        }
        return depth;
    }
}

