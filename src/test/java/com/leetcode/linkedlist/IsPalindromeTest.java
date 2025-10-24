package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 234. 回文链表 - 测试用例
 */
class IsPalindromeTest {
    
    private IsPalindrome solution;
    
    @BeforeEach
    void setUp() {
        solution = new IsPalindrome();
    }
    
    @Test
    @DisplayName("示例1: [1,2,2,1] -> true")
    void testExample1() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("示例2: [1,2] -> false")
    void testExample2() {
        ListNode head = ListNode.fromArray(new int[]{1, 2});
        assertFalse(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("示例3: [1] -> true")
    void testExample3() {
        ListNode head = ListNode.fromArray(new int[]{1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("示例4: [1,2,3,2,1] -> true")
    void testExample4() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("空链表 -> true")
    void testEmptyList() {
        assertFalse(solution.isPalindrome(null));
    }
    
    @Test
    @DisplayName("两个相同节点: [1,1] -> true")
    void testTwoSameNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("三个节点回文: [1,2,1] -> true")
    void testThreeNodespalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("三个节点非回文: [1,2,3] -> false")
    void testThreeNodesNotPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        assertFalse(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("四个节点非回文: [1,2,3,4] -> false")
    void testFourNodesNotPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        assertFalse(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("长链表回文: [1,2,3,4,3,2,1] -> true")
    void testLongPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 3, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("长链表非回文: [1,2,3,4,5,6,7] -> false")
    void testLongNotPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertFalse(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("全是相同值: [1,1,1,1] -> true")
    void testAllSameValues() {
        ListNode head = ListNode.fromArray(new int[]{1, 1, 1, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("偶数节点回文: [1,2,3,3,2,1] -> true")
    void testEvenNodesPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 3, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("奇数节点回文: [1,2,3,4,3,2,1] -> true")
    void testOddNodesPalindrome() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 3, 2, 1});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("大数值: [9,8,7,8,9] -> true")
    void testLargeValues() {
        ListNode head = ListNode.fromArray(new int[]{9, 8, 7, 8, 9});
        assertTrue(solution.isPalindrome(head));
    }
    
    @Test
    @DisplayName("零值: [0,0,0] -> true")
    void testZeroValues() {
        ListNode head = ListNode.fromArray(new int[]{0, 0, 0});
        assertTrue(solution.isPalindrome(head));
    }
}

