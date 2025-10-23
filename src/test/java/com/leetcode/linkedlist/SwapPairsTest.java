package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 24. 两两交换链表中的节点 - 测试用例
 */
class SwapPairsTest {
    
    private SwapPairs solution;
    
    @BeforeEach
    void setUp() {
        solution = new SwapPairs();
    }
    
    @Test
    @DisplayName("示例1: [1,2,3,4] -> [2,1,4,3]")
    void testExample1() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1, 4, 3}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例2: [] -> []")
    void testExample2() {
        ListNode head = null;
        ListNode result = solution.swapPairs(head);
        assertNull(result);
    }
    
    @Test
    @DisplayName("示例3: [1] -> [1]")
    void testExample3() {
        ListNode head = ListNode.fromArray(new int[]{1});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例4: [1,2,3] -> [2,1,3]")
    void testExample4() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1, 3}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("两个节点: [1,2] -> [2,1]")
    void testTwoNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("五个节点: [1,2,3,4,5] -> [2,1,4,3,5]")
    void testFiveNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1, 4, 3, 5}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("六个节点: [1,2,3,4,5,6] -> [2,1,4,3,6,5]")
    void testSixNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1, 4, 3, 6, 5}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("较长链表: [1,2,3,4,5,6,7,8] -> [2,1,4,3,6,5,8,7]")
    void testLongList() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{2, 1, 4, 3, 6, 5, 8, 7}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("验证节点值不变")
    void testNodeValuesNotChanged() {
        ListNode head = ListNode.fromArray(new int[]{10, 20, 30, 40});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{20, 10, 40, 30}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("负数节点: [-1,-2,-3,-4] -> [-2,-1,-4,-3]")
    void testNegativeValues() {
        ListNode head = ListNode.fromArray(new int[]{-1, -2, -3, -4});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{-2, -1, -4, -3}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("包含零: [0,1,2,3] -> [1,0,3,2]")
    void testWithZero() {
        ListNode head = ListNode.fromArray(new int[]{0, 1, 2, 3});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{1, 0, 3, 2}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("大数值: [100,99,88,77] -> [99,100,77,88]")
    void testLargeValues() {
        ListNode head = ListNode.fromArray(new int[]{100, 99, 88, 77});
        ListNode result = solution.swapPairs(head);
        assertArrayEquals(new int[]{99, 100, 77, 88}, ListNode.toArray(result));
    }
}

