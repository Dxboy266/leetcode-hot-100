package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 二叉树的前序遍历
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class PreorderTraversalTest {
    
    private final PreorderTraversal solution = new PreorderTraversal();
    
    @Test
    public void testExample1() {
        // 输入：root = [1,null,2,3]
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testExample2() {
        // 输入：root = []
        TreeNode root = null;
        
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testExample3() {
        // 输入：root = [1]
        TreeNode root = new TreeNode(1);
        
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testCompleteTree() {
        // 完全二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testLeftSkewed() {
        // 左斜树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testRightSkewed() {
        // 右斜树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, solution.preorderTraversal(root));
    }
    
    @Test
    public void testIterative() {
        // 测试迭代方法
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);
        assertEquals(expected, solution.preorderTraversalIterative(root));
    }
    
    @Test
    public void testLargeTree() {
        // 较大的树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        assertEquals(expected, solution.preorderTraversal(root));
    }
}

