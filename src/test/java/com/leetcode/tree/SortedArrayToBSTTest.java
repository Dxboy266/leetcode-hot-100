package com.leetcode.tree;

import com.leetcode.utils.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 将有序数组转换为二叉搜索树
 *
 * @Author Dxboy266
 * @Date 2025-11-10
 */
@DisplayName("108. 将有序数组转换为二叉搜索树")
public class SortedArrayToBSTTest {

    private final SortedArrayToBST solution = new SortedArrayToBST();

    /**
     * 辅助方法：验证是否为二叉搜索树
     */
    private boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) &&
               isValidBST(root.right, root.val, max);
    }

    /**
     * 辅助方法：验证是否为平衡二叉树
     */
    private boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 辅助方法：获取中序遍历结果
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    @Test
    @DisplayName("示例1：nums = [-10,-3,0,5,9]")
    public void testExample1() {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);

        // 验证是BST
        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");

        // 验证是平衡树
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        // 验证中序遍历结果与原数组一致
        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray(),
            "中序遍历结果应与原数组一致");
    }

    @Test
    @DisplayName("示例2：nums = [1,3]")
    public void testExample2() {
        int[] nums = {1, 3};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("单个元素")
    public void testSingleElement() {
        int[] nums = {1};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertNotNull(root, "根节点不应为空");
        assertEquals(1, root.val, "根节点值应为1");
        assertNull(root.left, "左子树应为空");
        assertNull(root.right, "右子树应为空");
    }

    @Test
    @DisplayName("两个元素")
    public void testTwoElements() {
        int[] nums = {1, 2};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("奇数个元素")
    public void testOddElements() {
        int[] nums = {1, 2, 3, 4, 5};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("负数数组")
    public void testNegativeNumbers() {
        int[] nums = {-10, -5, -3, -1};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("包含正负数的数组")
    public void testMixedNumbers() {
        int[] nums = {-5, -3, 0, 3, 5, 8, 10};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");
        // 数组长度为7，中间索引为3，对应值为3
        assertEquals(3, root.val, "根节点应为中间元素3");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("较大数组")
    public void testLargeArray() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i * 2;  // 0, 2, 4, 6, ..., 198
        }

        TreeNode root = solution.sortedArrayToBST(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("方法二：选择右中间元素")
    public void testRightMiddle() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        TreeNode root = solution.sortedArrayToBSTRight(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }
    
    @Test
    @DisplayName("方法三：迭代法")
    public void testIterative() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = solution.sortedArrayToBSTIterative(nums);

        assertTrue(isValidBST(root), "应该是有效的二叉搜索树");
        assertTrue(isBalanced(root), "应该是平衡二叉树");

        List<Integer> inorder = inorderTraversal(root);
        assertArrayEquals(nums, inorder.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("验证树的高度")
    public void testTreeHeight() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = solution.sortedArrayToBST(nums);

        int height = getHeight(root);
        // 7个节点的平衡BST高度应为3
        assertEquals(3, height, "7个节点的平衡BST高度应为3");
    }

    @Test
    @DisplayName("空数组")
    public void testEmptyArray() {
        int[] nums = {};
        TreeNode root = solution.sortedArrayToBST(nums);

        assertNull(root, "空数组应返回null");
    }

    @Test
    @DisplayName("null输入")
    public void testNullInput() {
        TreeNode root = solution.sortedArrayToBST(null);

        assertNull(root, "null输入应返回null");
    }

    @Test
    @DisplayName("验证所有节点都被正确创建")
    public void testAllNodesCreated() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = solution.sortedArrayToBST(nums);

        // 统计节点数量
        int nodeCount = countNodes(root);
        assertEquals(7, nodeCount, "应该创建7个节点");
    }

    /**
     * 辅助方法：统计节点数量
     */
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}