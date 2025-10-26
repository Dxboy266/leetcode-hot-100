package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * InorderTraversal 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class InorderTraversalTest {
    private InorderTraversal solution;

    @BeforeEach
    void setUp() {
        solution = new InorderTraversal();
    }

    @Test
    void testExample1() {
        // 输入：[1,null,2,3]
        //   1
        //    \
        //     2
        //    /
        //   3
        // 输出：[1,3,2]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(1, 3, 2);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testExample2() {
        // 输入：[]
        // 输出：[]
        TreeNode root = null;
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testExample3() {
        // 输入：[1]
        // 输出：[1]
        TreeNode root = new TreeNode(1);
        List<Integer> expected = Collections.singletonList(1);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testCompleteBinaryTree() {
        // 输入：[1,2,3,4,5,6,7]
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        // 输出：[4,2,5,1,6,3,7]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testLeftSkewedTree() {
        // 输入：[1,2,null,3]
        //     1
        //    /
        //   2
        //  /
        // 3
        // 输出：[3,2,1]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(3, 2, 1);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testRightSkewedTree() {
        // 输入：[1,null,2,null,3]
        //   1
        //    \
        //     2
        //      \
        //       3
        // 输出：[1,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.inorderTraversal(root));
    }

    @Test
    void testIterativeMethod() {
        // 测试迭代方法
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 3);
        assertEquals(expected, solution.inorderTraversalIterative(root));
    }

    @Test
    void testNegativeValues() {
        // 测试负数值
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(1);
        
        List<Integer> expected = Arrays.asList(-1, 0, 1);
        assertEquals(expected, solution.inorderTraversal(root));
    }
}

