package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * MiddleNode 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class MiddleNodeTest {
    private MiddleNode solution;

    @BeforeEach
    void setUp() {
        solution = new MiddleNode();
    }

    @Test
    void testExample1() {
        // 输入：[1,2,3,4,5]
        // 输出：[3,4,5]（中点是 3）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.middleNode(head);
        assertEquals(3, result.val);
        assertEquals("[3, 4, 5]", result.toString());
    }

    @Test
    void testExample2() {
        // 输入：[1,2,3,4,5,6]
        // 输出：[4,5,6]（有两个中点 3 和 4，返回第二个）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result = solution.middleNode(head);
        assertEquals(4, result.val);
        assertEquals("[4, 5, 6]", result.toString());
    }

    @Test
    void testExample3() {
        // 输入：[1]
        // 输出：[1]（单节点）
        ListNode head = ListNode.fromArray(new int[]{1});
        ListNode result = solution.middleNode(head);
        assertEquals(1, result.val);
        assertEquals("[1]", result.toString());
    }

    @Test
    void testTwoNodes() {
        // 输入：[1,2]
        // 输出：[2]（两个节点，返回第二个）
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.middleNode(head);
        assertEquals(2, result.val);
        assertEquals("[2]", result.toString());
    }

    @Test
    void testThreeNodes() {
        // 输入：[1,2,3]
        // 输出：[2,3]（中点是 2）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.middleNode(head);
        assertEquals(2, result.val);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    void testFourNodes() {
        // 输入：[1,2,3,4]
        // 输出：[3,4]（有两个中点 2 和 3，返回第二个）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        ListNode result = solution.middleNode(head);
        assertEquals(3, result.val);
        assertEquals("[3, 4]", result.toString());
    }

    @Test
    void testSevenNodes() {
        // 输入：[1,2,3,4,5,6,7]
        // 输出：[4,5,6,7]（中点是 4）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode result = solution.middleNode(head);
        assertEquals(4, result.val);
        assertEquals("[4, 5, 6, 7]", result.toString());
    }

    @Test
    void testEightNodes() {
        // 输入：[1,2,3,4,5,6,7,8]
        // 输出：[5,6,7,8]（有两个中点 4 和 5，返回第二个）
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        ListNode result = solution.middleNode(head);
        assertEquals(5, result.val);
        assertEquals("[5, 6, 7, 8]", result.toString());
    }

    @Test
    void testSameValues() {
        // 输入：[1,1,1,1,1]（所有值相同）
        // 输出：[1,1,1]（中点是第 3 个节点）
        ListNode head = ListNode.fromArray(new int[]{1, 1, 1, 1, 1});
        ListNode result = solution.middleNode(head);
        assertEquals(1, result.val);
        // 验证是第 3 个节点
        ListNode temp = head;
        temp = temp.next.next; // 第 3 个节点
        assertEquals(temp, result);
    }

    @Test
    void testLargeValues() {
        // 输入：[100,99,98,97,96]
        // 输出：[98,97,96]
        ListNode head = ListNode.fromArray(new int[]{100, 99, 98, 97, 96});
        ListNode result = solution.middleNode(head);
        assertEquals(98, result.val);
        assertEquals("[98, 97, 96]", result.toString());
    }
}

