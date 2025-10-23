package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReverseList 测试类
 */
@DisplayName("206. 反转链表")
class ReverseListTest {
    
    private ReverseList solution;
    
    @BeforeEach
    void setUp() {
        solution = new ReverseList();
    }
    
    @Test
    @DisplayName("示例1: [1,2,3,4,5] -> [5,4,3,2,1]")
    void testExample1() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例1递归: [1,2,3,4,5] -> [5,4,3,2,1]")
    void testExample1Recursive() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.reverseListRecursive(head);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例2: [1,2] -> [2,1]")
    void testExample2() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例3: [] -> []")
    void testExample3() {
        ListNode head = null;
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("单节点链表: [1] -> [1]")
    void testSingleNode() {
        ListNode head = ListNode.fromArray(new int[]{1});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("两个节点: [1,2] -> [2,1]")
    void testTwoNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("三个节点: [1,2,3] -> [3,2,1]")
    void testThreeNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{3, 2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("长链表: [1,2,3,4,5,6,7,8,9,10]")
    void testLongList() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("负数节点: [-1,-2,-3]")
    void testNegativeValues() {
        ListNode head = ListNode.fromArray(new int[]{-1, -2, -3});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{-3, -2, -1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("包含0: [0,1,2]")
    void testWithZero() {
        ListNode head = ListNode.fromArray(new int[]{0, 1, 2});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{2, 1, 0}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("相同值: [1,1,1,1]")
    void testSameValues() {
        ListNode head = ListNode.fromArray(new int[]{1, 1, 1, 1});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{1, 1, 1, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("大数值: [5000,4000,3000]")
    void testLargeValues() {
        ListNode head = ListNode.fromArray(new int[]{5000, 4000, 3000});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{3000, 4000, 5000}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("交替值: [1,-1,2,-2,3,-3]")
    void testAlternatingValues() {
        ListNode head = ListNode.fromArray(new int[]{1, -1, 2, -2, 3, -3});
        ListNode result = solution.reverseList(head);
        assertArrayEquals(new int[]{-3, 3, -2, 2, -1, 1}, ListNode.toArray(result));
    }
}

