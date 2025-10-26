package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IsSymmetric 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class IsSymmetricTest {
    private IsSymmetric solution;

    @BeforeEach
    void setUp() {
        solution = new IsSymmetric();
    }

    @Test
    void testExample1() {
        // 输入：[1,2,2,3,4,4,3]
        //         1
        //        / \
        //       2   2
        //      / \ / \
        //     3  4 4  3
        // 输出：true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testExample2() {
        // 输入：[1,2,2,null,3,null,3]
        //         1
        //        / \
        //       2   2
        //        \   \
        //         3   3
        // 输出：false
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testExample3() {
        // 输入：[1]
        // 输出：true
        TreeNode root = new TreeNode(1);
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testTwoNodes() {
        // 输入：[1,2,2]
        //     1
        //    / \
        //   2   2
        // 输出：true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testAsymmetricValues() {
        // 输入：[1,2,3]
        //     1
        //    / \
        //   2   3
        // 输出：false
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        
        assertFalse(solution.isSymmetric(root));
    }

    @Test
    void testAsymmetricStructure() {
        // 输入：[1,2,2,3,null,null,3]
        //         1
        //        / \
        //       2   2
        //      /     \
        //     3       3
        // 输出：true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testComplexSymmetric() {
        // 输入：[1,2,2,3,4,4,3,5,6,7,8,8,7,6,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(8);
        root.right.left.right = new TreeNode(7);
        root.right.right.left = new TreeNode(6);
        root.right.right.right = new TreeNode(5);
        
        assertTrue(solution.isSymmetric(root));
    }

    @Test
    void testLeftOnly() {
        // 输入：[1,2,null]
        //     1
        //    /
        //   2
        // 输出：false
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        
        assertFalse(solution.isSymmetric(root));
    }
}

