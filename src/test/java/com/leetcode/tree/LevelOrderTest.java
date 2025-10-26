package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LevelOrder 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-26
 */
public class LevelOrderTest {
    private LevelOrder solution;

    @BeforeEach
    void setUp() {
        solution = new LevelOrder();
    }

    @Test
    void testExample1() {
        // 输入：[3,9,20,null,null,15,7]
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        // 输出：[[3],[9,20],[15,7]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(3),
            Arrays.asList(9, 20),
            Arrays.asList(15, 7)
        );
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testExample2() {
        // 输入：[1]
        // 输出：[[1]]
        TreeNode root = new TreeNode(1);
        
        List<List<Integer>> expected = Collections.singletonList(
            Collections.singletonList(1)
        );
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testExample3() {
        // 输入：[]
        // 输出：[]
        TreeNode root = null;
        
        List<List<Integer>> expected = Collections.emptyList();
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testCompleteBinaryTree() {
        // 输入：[1,2,3,4,5,6,7]
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        // 输出：[[1],[2,3],[4,5,6,7]]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(1),
            Arrays.asList(2, 3),
            Arrays.asList(4, 5, 6, 7)
        );
        assertEquals(expected, solution.levelOrder(root));
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
        // 输出：[[1],[2],[3],[4]]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(1),
            Collections.singletonList(2),
            Collections.singletonList(3),
            Collections.singletonList(4)
        );
        assertEquals(expected, solution.levelOrder(root));
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
        // 输出：[[1],[2],[3],[4]]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(1),
            Collections.singletonList(2),
            Collections.singletonList(3),
            Collections.singletonList(4)
        );
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testUnbalancedTree() {
        // 输入：[1,2,3,4,null,null,5]
        //       1
        //      / \
        //     2   3
        //    /     \
        //   4       5
        // 输出：[[1],[2,3],[4,5]]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(1),
            Arrays.asList(2, 3),
            Arrays.asList(4, 5)
        );
        assertEquals(expected, solution.levelOrder(root));
    }

    @Test
    void testNegativeValues() {
        // 测试负数值
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(-2);
        
        List<List<Integer>> expected = Arrays.asList(
            Collections.singletonList(0),
            Arrays.asList(-1, 1),
            Collections.singletonList(-2)
        );
        assertEquals(expected, solution.levelOrder(root));
    }
}

