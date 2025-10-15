package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

/**
 * MergeTwoSortedLists 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试
 * - 边界条件测试
 * - 特殊情况测试
 * - 三种解法对比测试
 */
class MergeTwoSortedListsTest {
    
    private MergeTwoSortedLists mergeTwoSortedLists;
    
    @BeforeEach
    void setUp() {
        mergeTwoSortedLists = new MergeTwoSortedLists();
    }
    
    /**
     * 测试用例1：基本功能 - 两个非空链表
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 期望：[1,1,2,3,4,4]
     */
    @Test
    void testMergeTwoLists_BasicCase() {
        // Given
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {1, 1, 2, 3, 4, 4};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例2：边界条件 - 两个空链表
     * 输入：l1 = [], l2 = []
     * 期望：[]
     */
    @Test
    void testMergeTwoLists_BothEmpty() {
        // Given
        ListNode list1 = null;
        ListNode list2 = null;
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        assertThat(result).isNull();
    }
    
    /**
     * 测试用例3：边界条件 - 一个空链表
     * 输入：l1 = [], l2 = [0]
     * 期望：[0]
     */
    @Test
    void testMergeTwoLists_OneEmpty() {
        // Given
        ListNode list1 = null;
        ListNode list2 = new ListNode(0);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {0};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例4：特殊情况 - 单个节点
     * 输入：l1 = [1], l2 = [2]
     * 期望：[1,2]
     */
    @Test
    void testMergeTwoLists_SingleNodes() {
        // Given
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {1, 2};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例5：特殊情况 - 重复元素
     * 输入：l1 = [1,1,1], l2 = [1,1,1]
     * 期望：[1,1,1,1,1,1]
     */
    @Test
    void testMergeTwoLists_DuplicateElements() {
        // Given
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(1);
        list1.next.next = new ListNode(1);
        
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(1);
        list2.next.next = new ListNode(1);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {1, 1, 1, 1, 1, 1};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例6：特殊情况 - 一个链表完全小于另一个
     * 输入：l1 = [1,2,3], l2 = [4,5,6]
     * 期望：[1,2,3,4,5,6]
     */
    @Test
    void testMergeTwoLists_OneCompletelySmaller() {
        // Given
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        
        ListNode list2 = new ListNode(4);
        list2.next = new ListNode(5);
        list2.next.next = new ListNode(6);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {1, 2, 3, 4, 5, 6};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例7：递归法测试
     */
    @Test
    void testMergeTwoListsRecursive_BasicCase() {
        // Given
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoListsRecursive(list1, list2);
        
        // Then
        int[] expected = {1, 1, 2, 3, 4, 4};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例8：原地合并法测试
     */
    @Test
    void testMergeTwoListsInPlace_BasicCase() {
        // Given
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoListsInPlace(list1, list2);
        
        // Then
        int[] expected = {1, 1, 2, 3, 4, 4};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例9：三种解法结果一致性测试
     */
    @Test
    void testAllMethodsConsistency() {
        // Given - 测试用例集合
        int[][] testCases1 = {
            new int[]{1, 2, 4},
            new int[]{1, 3, 4},
            new int[]{1},
            new int[]{2},
            new int[]{},
            new int[]{1, 2, 3},
            new int[]{4, 5, 6}
        };
        
        int[][] testCases2 = {
            new int[]{1, 3, 4},
            new int[]{1, 2, 4},
            new int[]{2},
            new int[]{1},
            new int[]{0},
            new int[]{4, 5, 6},
            new int[]{1, 2, 3}
        };
        
        // When & Then - 验证三种方法结果一致
        for (int i = 0; i < testCases1.length; i++) {
            // 为每种方法创建独立的链表副本
            ListNode list1_iter = createList(testCases1[i]);
            ListNode list2_iter = createList(testCases2[i]);
            
            ListNode list1_rec = createList(testCases1[i]);
            ListNode list2_rec = createList(testCases2[i]);
            
            ListNode list1_place = createList(testCases1[i]);
            ListNode list2_place = createList(testCases2[i]);
            
            // 执行三种方法
            ListNode result1 = mergeTwoSortedLists.mergeTwoLists(list1_iter, list2_iter);
            ListNode result2 = mergeTwoSortedLists.mergeTwoListsRecursive(list1_rec, list2_rec);
            ListNode result3 = mergeTwoSortedLists.mergeTwoListsInPlace(list1_place, list2_place);
            
            // 验证结果一致
            assertThat(convertToList(result1))
                .as("测试用例 %d: 迭代法 vs 递归法", i + 1)
                .isEqualTo(convertToList(result2));
            
            assertThat(convertToList(result1))
                .as("测试用例 %d: 迭代法 vs 原地合并法", i + 1)
                .isEqualTo(convertToList(result3));
        }
    }
    
    /**
     * 测试用例10：边界条件 - 负数元素
     * 输入：l1 = [-1,0,1], l2 = [-2,-1,2]
     * 期望：[-2,-1,-1,0,1,2]
     */
    @Test
    void testMergeTwoLists_NegativeElements() {
        // Given
        ListNode list1 = new ListNode(-1);
        list1.next = new ListNode(0);
        list1.next.next = new ListNode(1);
        
        ListNode list2 = new ListNode(-2);
        list2.next = new ListNode(-1);
        list2.next.next = new ListNode(2);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {-2, -1, -1, 0, 1, 2};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例11：长链表性能测试
     */
    @Test
    void testMergeTwoLists_LongLists() {
        // Given - 创建两个长链表
        ListNode list1 = createList(generateArray(1, 100, 2)); // 1,3,5,...,99
        ListNode list2 = createList(generateArray(2, 100, 2)); // 2,4,6,...,100
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = generateArray(1, 100, 1); // 1,2,3,...,100
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    /**
     * 测试用例12：空链表与单节点
     */
    @Test
    void testMergeTwoLists_EmptyAndSingle() {
        // Given
        ListNode list1 = null;
        ListNode list2 = new ListNode(5);
        
        // When
        ListNode result = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        
        // Then
        int[] expected = {5};
        assertThat(convertToList(result)).isEqualTo(expected);
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 将链表转换为数组
     */
    private int[] convertToList(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        
        java.util.List<Integer> list = new java.util.ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * 从数组创建链表
     */
    private ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * 生成指定范围的数组
     */
    private int[] generateArray(int start, int end, int step) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        for (int i = start; i <= end; i += step) {
            list.add(i);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
