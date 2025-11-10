package com.leetcode.tree;

import com.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 
 * 题目链接：https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 * 难度：简单
 * 标签：树、二叉搜索树、数组、分治、二叉树
 * 
 * ==================== 题目描述 ====================
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 
 * 平衡二叉搜索树。
 * 
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *       0                    0
 *      / \                  / \
 *    -3   9               -10  5
 *    /   /                  \   \
 *  -10  5                   -3   9
 * 
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * 
 * 提示：
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums 按 严格递增 顺序排列
 * 
 * ==================== 解题思路 ====================
 * 
 * 【核心思想】：分治 + 递归
 * 
 * 【关键理解】：
 * 1. BST 的中序遍历是有序的
 * 2. 有序数组的中间元素作为根节点，可以保证左右子树平衡
 * 3. 递归构建左右子树
 * 
 * 【解题步骤】：
 * 1. 选择数组中间元素作为根节点
 * 2. 递归构建左子树：使用左半部分数组 [left, mid-1]
 * 3. 递归构建右子树：使用右半部分数组 [mid+1, right]
 * 4. 返回根节点
 * 
 * 【为什么选择中间元素？】
 * - 中间元素可以保证左右子树节点数量相差不超过1
 * - 满足平衡二叉树的定义：左右子树高度差不超过1
 * 
 * 【递归三要素】：
 * 1. 终止条件：left > right，返回 null
 * 2. 递归调用：构建左右子树
 * 3. 返回值：当前子树的根节点
 * 
 * 时间复杂度：O(n)，每个节点访问一次
 * 空间复杂度：O(log n)，递归栈深度为树的高度
 * 
 * ⚠️ 注意：
 * - 数组已经有序，不需要排序
 * - 选择中间元素时，可以选择 mid 或 mid+1，结果可能不同但都正确
 * - 本题答案不唯一，只要满足平衡BST即可
 * 
 * @Author Dxboy266
 * @Date 2025-11-10
 */
public class SortedArrayToBST {
    
    /**
     * 方法一：递归（中序遍历的逆过程）⭐⭐⭐ 推荐
     * 
     * 核心思路：
     * 1. 选择中间元素作为根节点
     * 2. 递归构建左右子树
     * 
     * @param nums 有序数组
     * @return 平衡二叉搜索树的根节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }
    
    /**
     * 递归辅助函数：构建BST
     * 
     * @param nums 有序数组
     * @param left 左边界（包含）
     * @param right 右边界（包含）
     * @return 当前子树的根节点
     */
    private TreeNode buildBST(int[] nums, int left, int right) {
        // 1. 递归终止条件：left > right，返回 null
        if (left > right) {
            return null;
        }
        
        // 2. 选择中间元素作为根节点
        // 如果有两个中间元素，选择左边的（也可以选择右边的）
        int mid = left + (right - left) / 2;  // 防止溢出
        TreeNode root = new TreeNode(nums[mid]);
        
        // 3. 递归构建左子树：使用 [left, mid-1]
        root.left = buildBST(nums, left, mid - 1);
        
        // 4. 递归构建右子树：使用 [mid+1, right]
        root.right = buildBST(nums, mid + 1, right);
        
        // 5. 返回根节点
        return root;
    }
    
    /**
     * 方法二：迭代法
     * 
     * 核心思路：
     * 使用队列模拟递归过程，分别维护节点队列和对应的区间队列
     * 
     * @param nums 有序数组
     * @return 平衡二叉搜索树的根节点
     */
    public TreeNode sortedArrayToBSTIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        // 初始化根节点
        TreeNode root = new TreeNode(-1);
        
        // 使用三个队列分别存储节点、左边界和右边界
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> leftQueue = new LinkedList<>();
        Queue<Integer> rightQueue = new LinkedList<>();
        
        // 初始化队列
        nodeQueue.offer(root);
        leftQueue.offer(0);
        rightQueue.offer(nums.length - 1);
        
        // 迭代处理
        while (!nodeQueue.isEmpty()) {
            // 取出当前节点和对应区间
            TreeNode currNode = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            
            // 计算中间位置并设置节点值
            int mid = left + (right - left) / 2;
            currNode.val = nums[mid];
            
            // 处理左子树
            if (left <= mid - 1) {
                currNode.left = new TreeNode(-1); // 先创建占位节点
                nodeQueue.offer(currNode.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }
            
            // 处理右子树
            if (right >= mid + 1) {
                currNode.right = new TreeNode(-1); // 先创建占位节点
                nodeQueue.offer(currNode.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }
        }
        
        return root;
    }
    
    /**
     * 方法三：选择右中间元素作为根节点
     * 
     * 与方法一的区别：当有两个中间元素时，选择右边的
     * 
     * @param nums 有序数组
     * @return 平衡二叉搜索树的根节点
     */
    public TreeNode sortedArrayToBSTRight(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBSTRight(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBSTRight(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        // 选择右中间元素
        int mid = left + (right - left + 1) / 2;  // +1 选择右边的中间元素
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = buildBSTRight(nums, left, mid - 1);
        root.right = buildBSTRight(nums, mid + 1, right);
        
        return root;
    }
}