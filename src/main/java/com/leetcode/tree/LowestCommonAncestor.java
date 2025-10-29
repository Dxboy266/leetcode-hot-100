package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * 
 * 题目链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * 难度：中等
 * 标签：树、深度优先搜索、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为："对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。"
 * 
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *         3
 *       /   \
 *      5     1
 *     / \   / \
 *    6   2 0   8
 *       / \
 *      7   4
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * 
 * 提示：
 * - 树中节点数目在范围 [2, 10^5] 内
 * - -10^9 <= Node.val <= 10^9
 * - 所有 Node.val 互不相同
 * - p != q
 * - p 和 q 均存在于给定的二叉树中
 * 
 * ==================== 解题提示 ====================
 * 
 * 【核心思想】：递归 + 后序遍历
 * 
 * 【分析】：
 * 1. 如果 p 和 q 分别在左右子树 → 当前节点就是最近公共祖先
 * 2. 如果 p 和 q 都在左子树 → 最近公共祖先在左子树中
 * 3. 如果 p 和 q 都在右子树 → 最近公共祖先在右子树中
 * 4. 如果当前节点是 p 或 q → 当前节点就是最近公共祖先
 * 
 * 【递归思路】：
 * 1. 终止条件：
 *    - root == null，返回 null
 *    - root == p 或 root == q，返回 root
 * 
 * 2. 递归左右子树：
 *    - left = lowestCommonAncestor(root.left, p, q)
 *    - right = lowestCommonAncestor(root.right, p, q)
 * 
 * 3. 根据返回值判断：
 *    - left != null && right != null → p 和 q 分别在左右子树，返回 root
 *    - left != null && right == null → p 和 q 都在左子树，返回 left
 *    - left == null && right != null → p 和 q 都在右子树，返回 right
 *    - left == null && right == null → 都不在，返回 null
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树的高度
 * 
 * ⚠️ 关键理解：
 * - 使用后序遍历（左 → 右 → 根）
 * - 从底向上返回信息
 * - 第一次遇到左右子树都有返回值的节点，就是最近公共祖先
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class LowestCommonAncestor {
    
    /**
     * 找到二叉树的最近公共祖先⭐⭐⭐ 核心题
     * 
     * 核心思路：后序遍历 + 递归
     * 
     * @param root 二叉树根节点
     * @param p 目标节点 p
     * @param q 目标节点 q
     * @return 最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 终止条件：
        // 1. root == null，返回 null
        // 2. root == p 或 root == q，返回 root（找到目标节点）
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // 递归查找左右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 判断返回值：
        // 1. left 和 right 都不为空 → p 和 q 分别在左右子树 → 当前节点是 LCA
        if (left != null && right != null) {
            return root;
        }
        
        // 2. 只有一侧有返回值 → p 和 q 都在该侧 → 返回该侧的结果
        return left != null ? left : right;
    }
}
