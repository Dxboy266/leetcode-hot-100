package com.leetcode.contest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

/**
 * 有效三元组的最小距离 - 测试用例
 */
@DisplayName("有效三元组的最小距离")
class MinimumTripletDistanceTest {

    private final MinimumTripletDistance solution = new MinimumTripletDistance();

    // ==================== 示例测试 ====================

    @Test
    @DisplayName("示例1：全部相同的元素")
    void testExample1() {
        int[] nums = {1, 1, 1, 1};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：任意三个下标的距离都是4
        // 例如 (0,1,2): abs(0-1) + abs(1-2) + abs(2-0) = 1 + 1 + 2 = 4
    }

    @Test
    @DisplayName("示例2：没有有效三元组")
    void testExample2() {
        int[] nums = {1, 2, 3};
        assertEquals(-1, solution.minimumDistance(nums));
        // 解释：所有元素都不相同，不存在有效三元组
    }

    @Test
    @DisplayName("示例3：部分相同的元素")
    void testExample3() {
        int[] nums = {1, 1, 2, 2, 2};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：有效三元组是 (2,3,4)，距离 = 1 + 1 + 2 = 4
    }

    // ==================== 边界测试 ====================

    @Test
    @DisplayName("边界：最小长度（3个相同元素）")
    void testMinimumLengthValid() {
        int[] nums = {5, 5, 5};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：(0,1,2) 距离 = 1 + 1 + 2 = 4
    }

    @Test
    @DisplayName("边界：最小长度（3个不同元素）")
    void testMinimumLengthInvalid() {
        int[] nums = {1, 2, 3};
        assertEquals(-1, solution.minimumDistance(nums));
    }

    @Test
    @DisplayName("边界：只有两个相同元素")
    void testOnlyTwoSame() {
        int[] nums = {1, 1, 2, 3, 4};
        assertEquals(-1, solution.minimumDistance(nums));
        // 解释：1只出现2次，不足以形成三元组
    }

    // ==================== 功能测试 ====================

    @Test
    @DisplayName("功能：连续的三个相同元素")
    void testConsecutiveTriplet() {
        int[] nums = {1, 2, 3, 3, 3, 4};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：(2,3,4) 距离 = 1 + 1 + 2 = 4
    }

    @Test
    @DisplayName("功能：分散的三个相同元素")
    void testScatteredTriplet() {
        int[] nums = {1, 2, 1, 3, 1};
        assertEquals(8, solution.minimumDistance(nums));
        // 解释：值为1的下标：0, 2, 4
        // 距离 = abs(0-2) + abs(2-4) + abs(4-0) = 2 + 2 + 4 = 8
        // 简化公式：距离 = 2*(max-min) = 2*(4-0) = 8
    }

    @Test
    @DisplayName("功能：多个有效三元组，选择最小距离")
    void testMultipleTriplets() {
        int[] nums = {5, 5, 5, 5, 5};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：任意连续三个下标的距离都是4，这是最小值
    }

    @Test
    @DisplayName("功能：多种值都有三元组")
    void testMultipleValues() {
        int[] nums = {1, 1, 1, 2, 2, 2};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：(0,1,2) 和 (3,4,5) 距离都是4
    }

    @Test
    @DisplayName("功能：大间隔的三元组")
    void testLargeGap() {
        int[] nums = {1, 2, 3, 4, 1, 5, 6, 7, 1};
        assertEquals(16, solution.minimumDistance(nums));
        // 解释：值为1的下标：0, 4, 8
        // 距离 = abs(0-4) + abs(4-8) + abs(8-0) = 4 + 4 + 8 = 16
        // 简化公式：距离 = 2*(max-min) = 2*(8-0) = 16
    }

    // ==================== 特殊情况测试 ====================

    @Test
    @DisplayName("特殊：所有元素相同")
    void testAllSame() {
        int[] nums = {7, 7, 7, 7, 7, 7};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：任意连续三个的距离都是4
    }

    @Test
    @DisplayName("特殊：只有一种值出现3次，其他都是1次")
    void testOnlyOneValidValue() {
        int[] nums = {1, 2, 3, 4, 4, 4, 5, 6};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：(3,4,5) 距离 = 1 + 1 + 2 = 4
    }

    @Test
    @DisplayName("特殊：值出现4次")
    void testFourOccurrences() {
        int[] nums = {3, 3, 3, 3};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：选择任意连续三个，距离都是4
    }

    @Test
    @DisplayName("特殊：值出现5次")
    void testFiveOccurrences() {
        int[] nums = {2, 2, 2, 2, 2};
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：选择任意连续三个，距离都是4
    }

    // ==================== 性能测试 ====================

    @Test
    @DisplayName("性能：大数组（10000个元素）")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testLargeArray() {
        int[] nums = new int[10000];
        // 前5000个是1，后5000个是2
        for (int i = 0; i < 5000; i++) {
            nums[i] = 1;
        }
        for (int i = 5000; i < 10000; i++) {
            nums[i] = 2;
        }
        assertEquals(4, solution.minimumDistance(nums));
        // 解释：(0,1,2) 或 (5000,5001,5002) 距离都是4
    }

    @Test
    @DisplayName("性能：大数组（无有效三元组）")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testLargeArrayNoTriplet() {
        int[] nums = new int[10000];
        // 每个元素都不同
        for (int i = 0; i < 10000; i++) {
            nums[i] = i;
        }
        assertEquals(-1, solution.minimumDistance(nums));
    }

    // ==================== 对比测试（可选） ====================

    @Test
    @DisplayName("对比：暴力解法 vs 优化解法")
    void testBruteForceVsOptimized() {
        int[] nums = {1, 1, 1, 2, 2, 2};
        int result1 = solution.minimumDistanceBruteForce(nums);
//        int result2 = solution.minimumDistance(nums);
//        int result3 = solution.minimumDistanceOptimized(nums);
        
//        assertEquals(result1, result2, "哈希表解法应该与暴力解法结果相同");
//        assertEquals(result1, result3, "一次遍历解法应该与暴力解法结果相同");
    }
}

