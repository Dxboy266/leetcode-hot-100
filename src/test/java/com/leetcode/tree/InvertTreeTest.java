package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * InvertTree 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class InvertTreeTest {
    private InvertTree solution;

    @BeforeEach
    void setUp() {
        solution = new InvertTree();
    }

    @Test
    void testExample1() {
        // 输入：[4,2,7,1,3,6,9]
        //       4
        //      / \
        //     2   7
        //    / \ / \
        //   1  3 6  9
        // 输出：[4,7,2,9,6,3,1]
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        
        TreeNode result = solution.invertTree(root);
        
        assertEquals(4, result.val);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(9, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(1, result.right.right.val);
    }

    @Test
    void testExample2() {
        // 输入：[2,1,3]
        //       2
        //      / \
        //     1   3
        // 输出：[2,3,1]
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        
        TreeNode result = solution.invertTree(root);
        
        assertEquals(2, result.val);
        assertEquals(3, result.left.val);
        assertEquals(1, result.right.val);
    }

    @Test
    void testExample3() {
        // 输入：[]
        // 输出：[]
        TreeNode root = null;
        TreeNode result = solution.invertTree(root);
        assertNull(result);
    }

    @Test
    void testSingleNode() {
        // 输入：[1]
        // 输出：[1]
        TreeNode root = new TreeNode(1);
        TreeNode result = solution.invertTree(root);
        
        assertEquals(1, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    void testLeftSkewedTree() {
        // 输入：[1,2,null,3]
        //     1
        //    /
        //   2
        //  /
        // 3
        // 输出：[1,null,2,null,3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        
        TreeNode result = solution.invertTree(root);
        
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.right.val);
    }

    @Test
    void testRightSkewedTree() {
        // 输入：[1,null,2,null,3]
        //   1
        //    \
        //     2
        //      \
        //       3
        // 输出：[1,2,null,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        
        TreeNode result = solution.invertTree(root);
        
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertNull(result.right);
        assertEquals(3, result.left.left.val);
    }

    @Test
    void testCompleteBinaryTree() {
        // 输入：[1,2,3,4,5,6,7]
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        TreeNode result = solution.invertTree(root);
        
        assertEquals(1, result.val);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(7, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(5, result.right.left.val);
        assertEquals(4, result.right.right.val);
    }
}

