package com.leetcode.greedy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 846. 一手顺子 - 测试用例
 */
class HandOfStraightsTest {
    
    private HandOfStraights solution;
    
    @BeforeEach
    void setUp() {
        solution = new HandOfStraights();
    }
    
    // ========== 方法一：TreeMap 测试 ==========
    
    @Test
    void testExample1() {
        // 示例 1：可以分成 [1,2,3], [2,3,4], [6,7,8]
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testExample2() {
        // 示例 2：无法分成大小为 4 的组
        int[] hand = {1, 2, 3, 4, 5};
        int groupSize = 4;
        assertFalse(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testSingleGroup() {
        // 测试只有一组的情况
        int[] hand = {1, 2, 3, 4, 5};
        int groupSize = 5;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testGroupSizeOne() {
        // 测试 groupSize = 1 的情况（任何牌都可以）
        int[] hand = {1, 5, 3, 9, 2};
        int groupSize = 1;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testCannotDivide() {
        // 测试牌的总数不能被 groupSize 整除
        int[] hand = {1, 2, 3, 4, 5, 6, 7};
        int groupSize = 3;
        assertFalse(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testDuplicateCards() {
        // 测试有重复牌的情况
        int[] hand = {1, 1, 2, 2, 3, 3};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testGapInSequence() {
        // 测试序列中有间隙的情况
        int[] hand = {1, 2, 3, 5, 6, 7};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testAllSameCards() {
        // 测试所有牌都相同的情况
        int[] hand = {1, 1, 1, 1};
        int groupSize = 2;
        assertFalse(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testLargeNumbers() {
        // 测试大数字
        int[] hand = {1000000000, 1000000001, 1000000002};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testMultipleGroups() {
        // 测试多组连续的牌
        int[] hand = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testComplexCase() {
        // 测试复杂情况：多组重复的连续牌
        int[] hand = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int groupSize = 5;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testUnorderedInput() {
        // 测试完全无序的输入
        int[] hand = {8, 10, 12, 7, 9, 11};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    // ========== 方法二：排序 + HashMap 测试 ==========
    
    @Test
    void testSortMethod_Example1() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        assertTrue(solution.isNStraightHandSort(hand, groupSize));
    }
    
    @Test
    void testSortMethod_Example2() {
        int[] hand = {1, 2, 3, 4, 5};
        int groupSize = 4;
        assertFalse(solution.isNStraightHandSort(hand, groupSize));
    }
    
    @Test
    void testSortMethod_DuplicateCards() {
        int[] hand = {1, 1, 2, 2, 3, 3};
        int groupSize = 3;
        assertTrue(solution.isNStraightHandSort(hand, groupSize));
    }
    
    @Test
    void testSortMethod_GapInSequence() {
        int[] hand = {1, 2, 3, 5, 6, 7};
        int groupSize = 3;
        assertFalse(solution.isNStraightHandSort(hand, groupSize));
    }
    
    @Test
    void testSortMethod_AllSameCards() {
        int[] hand = {1, 1, 1, 1};
        int groupSize = 2;
        assertFalse(solution.isNStraightHandSort(hand, groupSize));
    }
    
    @Test
    void testSortMethod_ComplexCase() {
        int[] hand = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int groupSize = 5;
        assertTrue(solution.isNStraightHandSort(hand, groupSize));
    }
    
    // ========== 边界测试 ==========
    
    @Test
    void testMinimumInput() {
        // 测试最小输入：只有一张牌
        int[] hand = {1};
        int groupSize = 1;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
    
    @Test
    void testZeroCards() {
        // 测试包含 0 的情况
        int[] hand = {0, 1, 2};
        int groupSize = 3;
        assertTrue(solution.isNStraightHand(hand, groupSize));
    }
}

