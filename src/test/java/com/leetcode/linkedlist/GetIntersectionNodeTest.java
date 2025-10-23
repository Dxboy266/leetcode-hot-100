package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 160. 相交链表 - 测试用例
 */
class GetIntersectionNodeTest {
    
    private GetIntersectionNode solution;
    
    @BeforeEach
    void setUp() {
        solution = new GetIntersectionNode();
    }
    
    @Test
    @DisplayName("示例1: 在节点8处相交")
    void testExample1() {
        // 创建相交部分：8 → 4 → 5
        ListNode intersect = new ListNode(8);
        intersect.next = new ListNode(4);
        intersect.next.next = new ListNode(5);
        
        // 创建链表A：4 → 1 → (8 → 4 → 5)
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersect;
        
        // 创建链表B：5 → 6 → 1 → (8 → 4 → 5)
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersect;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersect, result);
        assertEquals(8, result.val);
    }
    
    @Test
    @DisplayName("示例2: 在节点2处相交")
    void testExample2() {
        // 创建相交部分：2 → 4
        ListNode intersect = new ListNode(2);
        intersect.next = new ListNode(4);
        
        // 创建链表A：1 → 9 → 1 → (2 → 4)
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        headA.next.next.next = intersect;
        
        // 创建链表B：3 → (2 → 4)
        ListNode headB = new ListNode(3);
        headB.next = intersect;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersect, result);
        assertEquals(2, result.val);
    }
    
    @Test
    @DisplayName("示例3: 不相交")
    void testExample3() {
        // 链表A：2 → 6 → 4
        ListNode headA = ListNode.fromArray(new int[]{2, 6, 4});
        
        // 链表B：1 → 5
        ListNode headB = ListNode.fromArray(new int[]{1, 5});
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);
    }
    
    @Test
    @DisplayName("空链表A")
    void testNullListA() {
        ListNode headA = null;
        ListNode headB = ListNode.fromArray(new int[]{1, 2, 3});
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);
    }
    
    @Test
    @DisplayName("空链表B")
    void testNullListB() {
        ListNode headA = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode headB = null;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);
    }
    
    @Test
    @DisplayName("两个空链表")
    void testBothNull() {
        ListNode result = solution.getIntersectionNode(null, null);
        assertNull(result);
    }
    
    @Test
    @DisplayName("单节点相交")
    void testSingleNodeIntersection() {
        ListNode intersect = new ListNode(1);
        
        ListNode result = solution.getIntersectionNode(intersect, intersect);
        assertSame(intersect, result);
    }
    
    @Test
    @DisplayName("完全相同的链表（从头开始相交）")
    void testSameList() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        
        ListNode result = solution.getIntersectionNode(head, head);
        assertSame(head, result);
    }
    
    @Test
    @DisplayName("不同长度不相交")
    void testDifferentLengthNoIntersection() {
        ListNode headA = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        ListNode headB = ListNode.fromArray(new int[]{10, 11});
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);
    }
    
    @Test
    @DisplayName("A长B短，在末尾相交")
    void testLongShortIntersectAtEnd() {
        // 相交部分：5
        ListNode intersect = new ListNode(5);
        
        // A：1 → 2 → 3 → 4 → (5)
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = intersect;
        
        // B：(5)
        ListNode headB = intersect;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersect, result);
    }
    
    @Test
    @DisplayName("A短B长，在末尾相交")
    void testShortLongIntersectAtEnd() {
        // 相交部分：5
        ListNode intersect = new ListNode(5);
        
        // A：(5)
        ListNode headA = intersect;
        
        // B：1 → 2 → 3 → 4 → (5)
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(2);
        headB.next.next = new ListNode(3);
        headB.next.next.next = new ListNode(4);
        headB.next.next.next.next = intersect;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersect, result);
    }
    
    @Test
    @DisplayName("相同长度，中间相交")
    void testSameLengthIntersectMiddle() {
        // 相交部分：3 → 4 → 5
        ListNode intersect = new ListNode(3);
        intersect.next = new ListNode(4);
        intersect.next.next = new ListNode(5);
        
        // A：1 → 2 → (3 → 4 → 5)
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = intersect;
        
        // B：6 → 7 → (3 → 4 → 5)
        ListNode headB = new ListNode(6);
        headB.next = new ListNode(7);
        headB.next.next = intersect;
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersect, result);
    }
    
    @Test
    @DisplayName("相交节点值相同但不是同一个节点对象")
    void testSameValueDifferentNode() {
        // A：1 → 2 → 3
        ListNode headA = ListNode.fromArray(new int[]{1, 2, 3});
        
        // B：1 → 2 → 3（值相同但是不同的节点对象）
        ListNode headB = ListNode.fromArray(new int[]{1, 2, 3});
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);  // 不是同一个对象，所以不相交
    }
}

