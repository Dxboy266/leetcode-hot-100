package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HasCycle 测试类
 */
@DisplayName("141. 环形链表")
class HasCycleTest {
    
    private HasCycle solution;
    
    @BeforeEach
    void setUp() {
        solution = new HasCycle();
    }
    
    /**
     * 创建带环的链表
     * @param values 节点值数组
     * @param pos 环的入口位置（-1 表示无环）
     * @return 链表头节点
     */
    private ListNode createCycleList(int[] values, int pos) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleEntry = null;
        
        if (pos == 0) {
            cycleEntry = head;
        }
        
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
    
    @Test
    @DisplayName("示例1: [3,2,0,-4], pos=1 -> true")
    void testExample1() {
        ListNode head = createCycleList(new int[]{3, 2, 0, -4}, 1);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("示例2: [1,2], pos=0 -> true")
    void testExample2() {
        ListNode head = createCycleList(new int[]{1, 2}, 0);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("示例3: [1], pos=-1 -> false")
    void testExample3() {
        ListNode head = createCycleList(new int[]{1}, -1);
        assertFalse(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("空链表 -> false")
    void testEmptyList() {
        assertFalse(solution.hasCycle(null));
    }
    
    @Test
    @DisplayName("单节点无环: [1] -> false")
    void testSingleNodeNoCycle() {
        ListNode head = new ListNode(1);
        assertFalse(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("单节点有环: [1], pos=0 -> true")
    void testSingleNodeWithCycle() {
        ListNode head = new ListNode(1);
        head.next = head; // 指向自己
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("两节点无环: [1,2] -> false")
    void testTwoNodesNoCycle() {
        ListNode head = createCycleList(new int[]{1, 2}, -1);
        assertFalse(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("两节点有环在头: [1,2], pos=0 -> true")
    void testTwoNodesWithCycleAtHead() {
        ListNode head = createCycleList(new int[]{1, 2}, 0);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("两节点有环在尾: [1,2], pos=1 -> true")
    void testTwoNodesWithCycleAtTail() {
        ListNode head = createCycleList(new int[]{1, 2}, 1);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("长链表无环: [1,2,3,4,5,6,7,8,9,10] -> false")
    void testLongListNoCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, -1);
        assertFalse(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("长链表有环在中间: [1,2,3,4,5,6,7,8,9,10], pos=5 -> true")
    void testLongListWithCycleInMiddle() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("环在头部: [1,2,3,4,5], pos=0 -> true")
    void testCycleAtHead() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5}, 0);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("环在尾部: [1,2,3,4,5], pos=4 -> true")
    void testCycleAtTail() {
        ListNode head = createCycleList(new int[]{1, 2, 3, 4, 5}, 4);
        assertTrue(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("三节点无环: [1,2,3] -> false")
    void testThreeNodesNoCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3}, -1);
        assertFalse(solution.hasCycle(head));
    }
    
    @Test
    @DisplayName("三节点有环: [1,2,3], pos=1 -> true")
    void testThreeNodesWithCycle() {
        ListNode head = createCycleList(new int[]{1, 2, 3}, 1);
        assertTrue(solution.hasCycle(head));
    }
}


