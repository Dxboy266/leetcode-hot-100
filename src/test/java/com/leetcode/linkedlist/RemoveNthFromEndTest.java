package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 19. 删除链表的倒数第 N 个结点 - 测试用例
 */
class RemoveNthFromEndTest {
    
    private RemoveNthFromEnd solution;
    
    @BeforeEach
    void setUp() {
        solution = new RemoveNthFromEnd();
    }
    
    @Test
    @DisplayName("示例1: [1,2,3,4,5], n=2 -> [1,2,3,5]")
    void testExample1() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 2, 3, 5}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例2: [1], n=1 -> []")
    void testExample2() {
        ListNode head = ListNode.fromArray(new int[]{1});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例3: [1,2], n=1 -> [1]")
    void testExample3() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例4: [1,2], n=2 -> [2]")
    void testExample4() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{2}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("删除倒数第1个（尾节点）: [1,2,3,4,5], n=1 -> [1,2,3,4]")
    void testRemoveLastNode() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1, 2, 3, 4}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("删除倒数第5个（头节点）: [1,2,3,4,5], n=5 -> [2,3,4,5]")
    void testRemoveHeadNode() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNthFromEnd(head, 5);
        assertArrayEquals(new int[]{2, 3, 4, 5}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("删除中间节点: [1,2,3,4,5], n=3 -> [1,2,4,5]")
    void testRemoveMiddleNode() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.removeNthFromEnd(head, 3);
        assertArrayEquals(new int[]{1, 2, 4, 5}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("三个节点删除倒数第2个: [1,2,3], n=2 -> [1,3]")
    void testThreeNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 3}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("三个节点删除头节点: [1,2,3], n=3 -> [2,3]")
    void testThreeNodesRemoveHead() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.removeNthFromEnd(head, 3);
        assertArrayEquals(new int[]{2, 3}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("三个节点删除尾节点: [1,2,3], n=1 -> [1,2]")
    void testThreeNodesRemoveTail() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1, 2}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("长链表删除倒数第10个: [1,...,20], n=10 -> [1,...,10,12,...,20]")
    void testLongList() {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = i + 1;
        }
        ListNode head = ListNode.fromArray(arr);
        ListNode result = solution.removeNthFromEnd(head, 10);
        
        int[] expected = new int[19];
        for (int i = 0; i < 10; i++) {
            expected[i] = i + 1;
        }
        for (int i = 10; i < 19; i++) {
            expected[i] = i + 2;
        }
        assertArrayEquals(expected, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("删除倒数第4个: [1,2,3,4,5,6], n=4 -> [1,2,4,5,6]")
    void testSixNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result = solution.removeNthFromEnd(head, 4);
        assertArrayEquals(new int[]{1, 2, 4, 5, 6}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("删除倒数第1个（两个节点）: [1,2], n=1 -> [1]")
    void testTwoNodesRemoveLast() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("四个节点删除倒数第2个: [1,2,3,4], n=2 -> [1,2,4]")
    void testFourNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 2, 4}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("负数值: [-1,-2,-3,-4,-5], n=3 -> [-1,-2,-4,-5]")
    void testNegativeValues() {
        ListNode head = ListNode.fromArray(new int[]{-1, -2, -3, -4, -5});
        ListNode result = solution.removeNthFromEnd(head, 3);
        assertArrayEquals(new int[]{-1, -2, -4, -5}, ListNode.toArray(result));
    }
}

