package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 
 * 题目链接：https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 难度：中等
 * 标签：树、广度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。
 * （即逐层地，从左到右访问所有节点）。
 * 
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 输出：[[3],[9,20],[15,7]]
 * 
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * 
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * 
 * 提示：
 * - 树中节点数目在范围 [0, 2000] 内
 * - -1000 <= Node.val <= 1000
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心思想】：BFS（广度优先搜索）
 * 
 * 【方法：使用队列】
 * 思路：
 * 1. 使用队列存储每一层的节点
 * 2. 遍历当前层的所有节点
 * 3. 将下一层的节点加入队列
 * 4. 重复步骤2-3，直到队列为空
 * 
 * 关键点：
 * - 如何区分每一层？
 *   → 在处理每一层前，记录当前队列的大小（即当前层的节点数）
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class LevelOrder {
    
    /**
     * 层序遍历二叉树
     * 
     * @param root 二叉树根节点
     * @return 层序遍历结果（每层一个列表）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. 边界条件：root == null，返回空列表
        if (root == null) {
            return result;
        }
        // 2. 创建队列，将根节点入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 3. 当队列不为空时：
        while (!queue.isEmpty()) {
            //    a. 记录当前层的节点数 size = queue.size()
            int size = queue.size();
            //    b. 创建当前层的结果列表
            List<Integer> level = new ArrayList<>();
            //    c. 遍历 size 次：
            for (int i = 0; i < size; i++) {
                //       - 出队一个节点
                TreeNode node = queue.poll();
                //       - 将节点值加入当前层结果
                level.add(node.val);
                //       - 将左右子节点入队（如果存在）
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            //    d. 将当前层结果加入总结果
            result.add(level);
        }

        // 4. 返回结果
        return result;
    }
}

