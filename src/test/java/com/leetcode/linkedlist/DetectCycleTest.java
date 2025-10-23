package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 142. 环形链表 II - 测试用例
 */
class DetectCycleTest {
    
    private DetectCycle solution;
    
    @BeforeEach
    void setUp() {
        solution = new DetectCycle();
    }
    
    /**
     * 创建带环的链表
     * @param values 节点值数组
     * @param pos 环入口的位置（-1 表示无环）
     * @return 链表头节点
     */
    private ListNode createCycleList(int[] values, int pos) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        // 创建节点
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleEntry = null;
        
        // 记录环入口节点
        if (pos == 0) {
            cycleEntry = head;
        }
        
        // 构建链表
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            if (i == pos) {
                cycleEntry = current;
            }
        }
        
        // 创建环
        if (pos != -1 && cycleEntry != null) {
            current.next = cycleEntry;
        }
        
        return head;
    }
    
    /**
     * 获取链表中指定位置的节点
     */
    private ListNode getNodeAtPosition(ListNode head, int pos) {
        ListNode current = head;
        for (int i = 0; i < pos && current != null; i++) {
            current = current.next;
        }
        return current;
    }
    
    @Test
    @DisplayName("示例1: [3,2,0,-4], pos=1")
    void testExample1() {
        ListNode head = createCycleList(new int[]{3, 2, 0, -4}, 1);
        ListNode cycleEntry = getNodeAtPosition(head, 1);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(2, result.val);
    }
    
    @Test
    @DisplayName("示例2: [1,2], pos=0")
    void testExample2() {
        ListNode head = createCycleList(new int[]{1, 2}, 0);
        ListNode cycleEntry = head;
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(1, result.val);
    }
    
    @Test
    @DisplayName("示例3: [1], pos=-1（无环）")
    void testExample3() {
        ListNode head = createCycleList(new int[]{1}, -1);
        
        ListNode result = solution.detectCycle(head);
        assertNull(result);
    }
    
    @Test
    @DisplayName("空链表")
    void testEmptyList() {
        ListNode result = solution.detectCycle(null);
        assertNull(result);
    }
    
    @Test
    @DisplayName("单节点无环")
    void testSingleNodeNoCycle() {
        ListNode head = new ListNode(1);
        
        ListNode result = solution.detectCycle(head);
        assertNull(result);
    }
    
    @Test
    @DisplayName("单节点有环（自环）")
    void testSingleNodeWithCycle() {
        ListNode head = new ListNode(1);
        head.next = head;  // 自环
        
        ListNode result = solution.detectCycle(head);
        assertSame(head, result);
    }
    
    @Test
    @DisplayName("两个节点无环")
    void testTwoNodesNoCycle() {
        ListNode head = createCycleList(new int[]{1, 2}, -1);
        
        ListNode result = solution.detectCycle(head);
        assertNull(result);
    }
    
    @Test
    @DisplayName("环在链表末尾: [1,2,3,4,5], pos=4")
    void testCycleAtEnd() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5}, 4);
        ListNode cycleEntry = getNodeAtPosition(head, 4);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(5, result.val);
    }
    
    @Test
    @DisplayName("环在链表头部: [1,2,3,4,5], pos=0")
    void testCycleAtHead() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5}, 0);
        
        ListNode result = solution.detectCycle(head);
        assertSame(head, result);
        assertEquals(1, result.val);
    }
    
    @Test
    @DisplayName("环在链表中间: [1,2,3,4,5,6], pos=2")
    void testCycleInMiddle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6}, 2);
        ListNode cycleEntry = getNodeAtPosition(head, 2);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(3, result.val);
    }
    
    @Test
    @DisplayName("较长链表无环: [1,2,3,4,5,6,7,8,9,10]")
    void testLongListNoCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, -1);
        
        ListNode result = solution.detectCycle(head);
        assertNull(result);
    }
    
    @Test
    @DisplayName("较长链表有环: [1,2,3,4,5,6,7,8,9,10], pos=5")
    void testLongListWithCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        ListNode cycleEntry = getNodeAtPosition(head, 5);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(6, result.val);
    }
    
    @Test
    @DisplayName("环很小（两个节点）: [1,2,3,4,5], pos=3")
    void testSmallCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5}, 3);
        ListNode cycleEntry = getNodeAtPosition(head, 3);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(4, result.val);
    }
    
    @Test
    @DisplayName("环很大: [1,2,3,4,5,6,7,8,9,10], pos=1")
    void testLargeCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1);
        ListNode cycleEntry = getNodeAtPosition(head, 1);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(2, result.val);
    }
    
    @Test
    @DisplayName("负数节点值: [-1,-2,-3,-4], pos=1")
    void testNegativeValues() {
        ListNode head = createCycleList(new int[]{-1, -2, -3, -4}, 1);
        ListNode cycleEntry = getNodeAtPosition(head, 1);
        
        ListNode result = solution.detectCycle(head);
        assertSame(cycleEntry, result);
        assertEquals(-2, result.val);
    }
}

