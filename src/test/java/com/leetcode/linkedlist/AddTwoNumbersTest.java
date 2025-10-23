package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AddTwoNumbers 测试类
 */
@DisplayName("2. 两数相加")
class AddTwoNumbersTest {
    
    private AddTwoNumbers solution;
    
    @BeforeEach
    void setUp() {
        solution = new AddTwoNumbers();
    }
    
    @Test
    @DisplayName("示例1: [2,4,3] + [5,6,4] = [7,0,8]")
    void testExample1() {
        ListNode l1 = ListNode.fromArray(new int[]{2, 4, 3});
        ListNode l2 = ListNode.fromArray(new int[]{5, 6, 4});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{7, 0, 8}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例2: [0] + [0] = [0]")
    void testExample2() {
        ListNode l1 = ListNode.fromArray(new int[]{0});
        ListNode l2 = ListNode.fromArray(new int[]{0});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("示例3: [9,9,9,9,9,9,9] + [9,9,9,9] = [8,9,9,9,0,0,0,1]")
    void testExample3() {
        ListNode l1 = ListNode.fromArray(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2 = ListNode.fromArray(new int[]{9, 9, 9, 9});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{8, 9, 9, 9, 0, 0, 0, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("l1 长度大于 l2")
    void testL1LongerThanL2() {
        ListNode l1 = ListNode.fromArray(new int[]{9, 9, 9});
        ListNode l2 = ListNode.fromArray(new int[]{1});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0, 0, 0, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("l2 长度大于 l1")
    void testL2LongerThanL1() {
        ListNode l1 = ListNode.fromArray(new int[]{1});
        ListNode l2 = ListNode.fromArray(new int[]{9, 9, 9});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0, 0, 0, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("无进位相加")
    void testNoCarry() {
        ListNode l1 = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode l2 = ListNode.fromArray(new int[]{4, 5, 6});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{5, 7, 9}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("全程进位")
    void testAllCarry() {
        ListNode l1 = ListNode.fromArray(new int[]{5, 5, 5});
        ListNode l2 = ListNode.fromArray(new int[]{5, 5, 5});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0, 1, 1, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("单节点链表")
    void testSingleNode() {
        ListNode l1 = ListNode.fromArray(new int[]{5});
        ListNode l2 = ListNode.fromArray(new int[]{3});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{8}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("单节点进位")
    void testSingleNodeWithCarry() {
        ListNode l1 = ListNode.fromArray(new int[]{7});
        ListNode l2 = ListNode.fromArray(new int[]{8});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{5, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("相同长度，最后一位进位")
    void testSameLengthLastCarry() {
        ListNode l1 = ListNode.fromArray(new int[]{1, 8});
        ListNode l2 = ListNode.fromArray(new int[]{0, 2});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{1, 0, 1}, ListNode.toArray(result));
    }
    
    @Test
    @DisplayName("多位数相加: 99 + 1 = 100")
    void testMultipleDigits() {
        ListNode l1 = ListNode.fromArray(new int[]{9, 9}); // 99
        ListNode l2 = ListNode.fromArray(new int[]{1});    // 1
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0, 0, 1}, ListNode.toArray(result)); // 100
    }
    
    @Test
    @DisplayName("中间有0: 102 + 89 = 191")
    void testWithZeroInMiddle() {
        ListNode l1 = ListNode.fromArray(new int[]{2, 0, 1}); // 102
        ListNode l2 = ListNode.fromArray(new int[]{9, 8});    // 89
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{1, 9, 1}, ListNode.toArray(result)); // 191
    }
    
    @Test
    @DisplayName("复杂进位: 807 + 99 = 906")
    void testComplexCarry() {
        ListNode l1 = ListNode.fromArray(new int[]{7, 0, 8}); // 807
        ListNode l2 = ListNode.fromArray(new int[]{9, 9});    // 99
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{6, 0, 9}, ListNode.toArray(result)); // 906
    }
    
    @Test
    @DisplayName("大数相加: 12345 + 6789 = 19134")
    void testLargeNumbers() {
        ListNode l1 = ListNode.fromArray(new int[]{5, 4, 3, 2, 1}); // 12345
        ListNode l2 = ListNode.fromArray(new int[]{9, 8, 7, 6});    // 6789
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{4, 3, 1, 9, 1}, ListNode.toArray(result)); // 19134
    }
    
    @Test
    @DisplayName("全是9: 999 + 999 = 1998")
    void testAllNines() {
        ListNode l1 = ListNode.fromArray(new int[]{9, 9, 9});
        ListNode l2 = ListNode.fromArray(new int[]{9, 9, 9});
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{8, 9, 9, 1}, ListNode.toArray(result));
    }
}
