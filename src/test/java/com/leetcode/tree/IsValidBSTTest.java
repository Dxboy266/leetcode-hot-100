package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 验证二叉搜索树
 * 
 * @Author Dxboy266
 * @Date 2025-10-27
 */
public class IsValidBSTTest {
    
    private final IsValidBST solution = new IsValidBST();
    
    @Test
    public void testExample1() {
        // 输入：root = [2,1,3]
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testExample2() {
        // 输入：root = [5,1,4,null,null,3,6]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        
        assertFalse(solution.isValidBST(root));
    }
    
    @Test
    public void testSingleNode() {
        // 单节点树
        TreeNode root = new TreeNode(1);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testLeftSubtreeViolation() {
        // 左子树违反规则
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(6); // 左子树的右子节点 > 根节点
        
        assertFalse(solution.isValidBST(root));
    }
    
    @Test
    public void testRightSubtreeViolation() {
        // 右子树违反规则
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(3); // 右子树的左子节点 < 根节点
        
        assertFalse(solution.isValidBST(root));
    }
    
    @Test
    public void testEqualValues() {
        // 相等的值（BST 不允许相等）
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        
        assertFalse(solution.isValidBST(root));
    }
    
    @Test
    public void testValidBST() {
        // 有效的 BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testMinValue() {
        // 测试最小值边界
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testLeftSkewed() {
        // 左斜树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testRightSkewed() {
        // 右斜树
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        
        assertTrue(solution.isValidBST(root));
    }
    
    @Test
    public void testRangeMethod() {
        // 测试区间限制方法
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        
        assertTrue(solution.isValidBSTRange(root));
    }
    
    @Test
    public void testRangeMethodFalse() {
        // 测试区间限制方法（false 情况）
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        
        assertFalse(solution.isValidBSTRange(root));
    }
}

