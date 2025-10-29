package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 二叉树的最近公共祖先
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class LowestCommonAncestorTest {
    
    private final LowestCommonAncestor solution = new LowestCommonAncestor();
    
    @Test
    public void testExample1() {
        // 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        root.left = node5;
        root.right = node1;
        node5.left = new TreeNode(6);
        node5.right = new TreeNode(2);
        node5.right.left = new TreeNode(7);
        node5.right.right = new TreeNode(4);
        node1.left = new TreeNode(0);
        node1.right = new TreeNode(8);
        
        TreeNode result = solution.lowestCommonAncestor(root, node5, node1);
        assertEquals(3, result.val);
    }
    
    @Test
    public void testExample2() {
        // 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        root.left = node5;
        root.right = new TreeNode(1);
        node5.left = new TreeNode(6);
        node5.right = new TreeNode(2);
        node5.right.left = new TreeNode(7);
        node5.right.right = node4;
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        
        TreeNode result = solution.lowestCommonAncestor(root, node5, node4);
        assertEquals(5, result.val);
    }
    
    @Test
    public void testExample3() {
        // 输入：root = [1,2], p = 1, q = 2
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.left = node2;
        
        TreeNode result = solution.lowestCommonAncestor(root, root, node2);
        assertEquals(1, result.val);
    }
    
    @Test
    public void testBothInLeftSubtree() {
        // p 和 q 都在左子树
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node5;
        root.right = new TreeNode(1);
        node5.left = node6;
        node5.right = new TreeNode(2);
        
        TreeNode result = solution.lowestCommonAncestor(root, node5, node6);
        assertEquals(5, result.val);
    }
    
    @Test
    public void testBothInRightSubtree() {
        // p 和 q 都在右子树
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node0 = new TreeNode(0);
        root.left = new TreeNode(5);
        root.right = node1;
        node1.left = node0;
        node1.right = new TreeNode(8);
        
        TreeNode result = solution.lowestCommonAncestor(root, node1, node0);
        assertEquals(1, result.val);
    }
    
    @Test
    public void testSiblings() {
        // p 和 q 是兄弟节点
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        root.left = node5;
        root.right = node1;
        
        TreeNode result = solution.lowestCommonAncestor(root, node5, node1);
        assertEquals(3, result.val);
    }
    
    @Test
    public void testParentChild() {
        // p 是 q 的父节点
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node5;
        root.right = new TreeNode(1);
        node5.left = node6;
        
        TreeNode result = solution.lowestCommonAncestor(root, node5, node6);
        assertEquals(5, result.val);
    }
    
    @Test
    public void testDeepTree() {
        // 较深的树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root.left.left = node4;
        root.left.right = new TreeNode(5);
        root.left.left.left = node7;
        
        TreeNode result = solution.lowestCommonAncestor(root, node4, node7);
        assertEquals(4, result.val);
    }
    
    @Test
    public void testRootAsAncestor() {
        // 根节点作为祖先
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;
        node2.left = new TreeNode(4);
        node3.right = new TreeNode(5);
        
        TreeNode result = solution.lowestCommonAncestor(root, root.left.left, root.right.right);
        assertEquals(1, result.val);
    }
}
