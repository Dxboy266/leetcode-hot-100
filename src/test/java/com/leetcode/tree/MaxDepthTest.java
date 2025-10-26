package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * MaxDepth 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class MaxDepthTest {
    private MaxDepth solution;

    @BeforeEach
    void setUp() {
        solution = new MaxDepth();
    }

    @Test
    void testExample1() {
        // 输入：[3,9,20,null,null,15,7]
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        // 输出：3
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testExample2() {
        // 输入：[1,null,2]
        // 输出：2
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        
        assertEquals(2, solution.maxDepth(root));
    }

    @Test
    void testExample3() {
        // 输入：[]
        // 输出：0
        TreeNode root = null;
        assertEquals(0, solution.maxDepth(root));
    }

    @Test
    void testExample4() {
        // 输入：[0]
        // 输出：1
        TreeNode root = new TreeNode(0);
        assertEquals(1, solution.maxDepth(root));
    }

    @Test
    void testLeftSkewedTree() {
        // 输入：[1,2,null,3,null,4]
        //     1
        //    /
        //   2
        //  /
        // 3
        // /
        //4
        // 输出：4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    void testRightSkewedTree() {
        // 输入：[1,null,2,null,3,null,4]
        //   1
        //    \
        //     2
        //      \
        //       3
        //        \
        //         4
        // 输出：4
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        
        assertEquals(4, solution.maxDepth(root));
    }

    @Test
    void testCompleteBinaryTree() {
        // 输入：[1,2,3,4,5,6,7]
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        // 输出：3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        assertEquals(3, solution.maxDepth(root));
    }

    @Test
    void testUnbalancedTree() {
        // 输入：[1,2,3,4,null,null,null,5]
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        //  /
        // 5
        // 输出：4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        
        assertEquals(4, solution.maxDepth(root));
    }
}

