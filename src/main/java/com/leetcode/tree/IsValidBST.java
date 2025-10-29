package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 98. 验证二叉搜索树
 * 
 * 题目链接：https://leetcode.cn/problems/validate-binary-search-tree/
 * 难度：中等
 * 标签：树、深度优先搜索、二叉搜索树、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 
 * 有效 二叉搜索树定义如下：
 * - 节点的左子树只包含 小于 当前节点的数。
 * - 节点的右子树只包含 大于 当前节点的数。
 * - 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 示例 1：
 * 输入：root = [2,1,3]
 *       2
 *      / \
 *     1   3
 * 输出：true
 * 
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 *       5
 *      / \
 *     1   4
 *        / \
 *       3   6
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * 
 * 示例 3：
 * 输入：root = [5,4,6,null,null,3,7]
 * 输出：false
 * 解释：节点 6 的右子树包含节点 3，但 3 < 6
 * 
 * 提示：
 * - 树中节点数目范围在 [1, 10^4] 内
 * - -2^31 <= Node.val <= 2^31 - 1
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心性质】：二叉搜索树的中序遍历结果是严格递增的
 * 
 * 【方法一：中序遍历】⭐ 推荐
 * 思路：
 * 1. 中序遍历二叉树
 * 2. 判断遍历结果是否严格递增
 * 3. 可以使用一个变量记录前一个节点的值，边遍历边判断
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * 【方法二：递归（区间限制）】
 * 思路：
 * 1. 每个节点都有一个取值范围 (min, max)
 * 2. 根节点：(-∞, +∞)
 * 3. 左子树：(min, root.val)
 * 4. 右子树：(root.val, max)
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * ⚠️ 注意：
 * - 左子树的所有节点必须 < 根节点（不能只比较左子节点）
 * - 右子树的所有节点必须 > 根节点（不能只比较右子节点）
 * - 使用 Long 类型避免整数边界问题
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class IsValidBST {
    
    /**
     * 验证二叉搜索树（中序遍历）⭐ 推荐
     * 
     * 核心思路：BST 的中序遍历结果是严格递增的
     * 
     * @param root 二叉树根节点
     * @return 是否是有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        // 中序遍历获取所有节点值
        List<Long> inorder = new ArrayList<>();
        traverse(root, inorder);
        
        // 判断是否严格递增
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i) <= inorder.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历辅助函数
     */
    private void traverse(TreeNode root, List<Long> inorder) {
        if (root == null) {
            return;
        }
        traverse(root.left, inorder);       // 左
        inorder.add((long) root.val);       // 根
        traverse(root.right, inorder);      // 右
    }


    
    /**
     * 验证二叉搜索树（递归区间限制）
     * 
     * 核心思路：每个节点都有取值范围 (min, max)
     * 
     * @param root 二叉树根节点
     * @return 是否是有效的二叉搜索树
     */
    public boolean isValidBSTRange(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 递归验证辅助函数
     * 
     * @param root 当前节点
     * @param min 最小值（不包含）
     * @param max 最大值（不包含）
     */
    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        
        // 当前节点值必须在 (min, max) 范围内
        if (root.val <= min || root.val >= max) {
            return false;
        }
        
        // 递归验证左右子树
        // 左子树：(min, root.val)
        // 右子树：(root.val, max)
        return isValid(root.left, min, root.val) && 
               isValid(root.right, root.val, max);
    }
}

