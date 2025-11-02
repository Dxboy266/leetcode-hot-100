package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 300. 最长递增子序列
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class LengthOfLISTest {

    private final LengthOfLIS solution = new LengthOfLIS();

    @Test
    public void testExample1() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        assertEquals(4, solution.lengthOfLIS(nums)); // [2,3,7,101]
//        assertEquals(4, solution.lengthOfLISBinarySearch(nums));
//        assertEquals(4, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testExample2() {
        int[] nums = {0, 1, 0, 3, 2, 3};
        assertEquals(4, solution.lengthOfLIS(nums)); // [0,1,2,3]
        assertEquals(4, solution.lengthOfLISBinarySearch(nums));
        assertEquals(4, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testExample3() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        assertEquals(1, solution.lengthOfLIS(nums));
        assertEquals(1, solution.lengthOfLISBinarySearch(nums));
        assertEquals(1, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {5};
        assertEquals(1, solution.lengthOfLIS(nums));
        assertEquals(1, solution.lengthOfLISBinarySearch(nums));
        assertEquals(1, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testStrictlyIncreasing() {
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(5, solution.lengthOfLIS(nums));
        assertEquals(5, solution.lengthOfLISBinarySearch(nums));
        assertEquals(5, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testStrictlyDecreasing() {
        int[] nums = {5, 4, 3, 2, 1};
        assertEquals(1, solution.lengthOfLIS(nums));
        assertEquals(1, solution.lengthOfLISBinarySearch(nums));
        assertEquals(1, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testMixedPattern() {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        assertEquals(6, solution.lengthOfLIS(nums)); // [1,3,4,5,6] 或其他
        assertEquals(6, solution.lengthOfLISBinarySearch(nums));
        assertEquals(6, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums = {-10, -5, -3, -1, 0, 2};
        assertEquals(6, solution.lengthOfLIS(nums));
        assertEquals(6, solution.lengthOfLISBinarySearch(nums));
        assertEquals(6, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testLargeArray() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i % 10; // [0,1,2,...,9,0,1,2,...,9,...]
        }
        assertEquals(10, solution.lengthOfLIS(nums));
        assertEquals(10, solution.lengthOfLISBinarySearch(nums));
        assertEquals(10, solution.lengthOfLISMemo(nums));
    }

    @Test
    public void testTwoElements() {
        int[] nums1 = {1, 2};
        assertEquals(2, solution.lengthOfLIS(nums1));
        assertEquals(2, solution.lengthOfLISBinarySearch(nums1));

        int[] nums2 = {2, 1};
        assertEquals(1, solution.lengthOfLIS(nums2));
        assertEquals(1, solution.lengthOfLISBinarySearch(nums2));
    }
}

