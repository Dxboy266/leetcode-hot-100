package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 二叉树的直径
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class DiameterOfBinaryTreeTest {
    
    private final DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
    
    @Test
    public void testExample1() {
        // 输入：root = [1,2,3,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        assertEquals(3, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testExample2() {
        // 输入：root = [1,2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        
        assertEquals(1, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testSingleNode() {
        // 单节点树
        TreeNode root = new TreeNode(1);
        
        assertEquals(0, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testLeftSkewed() {
        // 左斜树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        
        assertEquals(3, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testRightSkewed() {
        // 右斜树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        
        assertEquals(3, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testBalancedTree() {
        // 平衡树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        assertEquals(4, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testDiameterNotThroughRoot() {
        // 直径不经过根节点
        // 树结构：
        //       1
        //      /
        //     2
        //    / \
        //   3   4
        //  / \
        // 5   6
        // 最长路径：5→3→2→4 或 6→3→2→4，长度为3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        
        assertEquals(3, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testCompleteTree() {
        // 完全二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        
        assertEquals(4, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testDeepLeftSubtree() {
        // 左子树很深
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(6);
        
        assertEquals(5, solution.diameterOfBinaryTree(root));
    }
    
    @Test
    public void testSymmetricTree() {
        // 对称树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        assertEquals(4, solution.diameterOfBinaryTree(root));
    }
}

