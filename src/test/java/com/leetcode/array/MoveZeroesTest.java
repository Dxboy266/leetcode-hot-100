package com.leetcode.array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

/**
 * MoveZeroes 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试
 * - 边界条件测试
 * - 特殊情况测试
 */
class MoveZeroesTest {
    
    private MoveZeroes moveZeroes;
    
    @BeforeEach
    void setUp() {
        moveZeroes = new MoveZeroes();
    }
    
    /**
     * 测试用例1：基本功能 - 混合数组
     * 输入：[0,1,0,3,12]
     * 期望：[1,3,12,0,0]
     */
    @Test
    void testMoveZeroes_BasicExample() {
        // Given
        int[] nums = {0, 1, 0, 3, 12};
        int[] expected = {1, 3, 12, 0, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例2：只有一个元素且为0
     * 输入：[0]
     * 期望：[0]
     */
    @Test
    void testMoveZeroes_SingleZero() {
        // Given
        int[] nums = {0};
        int[] expected = {0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例3：只有一个元素且非零
     * 输入：[1]
     * 期望：[1]
     */
    @Test
    void testMoveZeroes_SingleNonZero() {
        // Given
        int[] nums = {1};
        int[] expected = {1};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例4：全部为0
     * 输入：[0,0,0]
     * 期望：[0,0,0]
     */
    @Test
    void testMoveZeroes_AllZeros() {
        // Given
        int[] nums = {0, 0, 0};
        int[] expected = {0, 0, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例5：全部非零
     * 输入：[1,2,3]
     * 期望：[1,2,3]
     */
    @Test
    void testMoveZeroes_AllNonZeros() {
        // Given
        int[] nums = {1, 2, 3};
        int[] expected = {1, 2, 3};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例6：零在开头
     * 输入：[0,1,2,3]
     * 期望：[1,2,3,0]
     */
    @Test
    void testMoveZeroes_ZerosAtBeginning() {
        // Given
        int[] nums = {0, 1, 2, 3};
        int[] expected = {1, 2, 3, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例7：零在结尾
     * 输入：[1,2,3,0]
     * 期望：[1,2,3,0]
     */
    @Test
    void testMoveZeroes_ZerosAtEnd() {
        // Given
        int[] nums = {1, 2, 3, 0};
        int[] expected = {1, 2, 3, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例8：包含负数
     * 输入：[0,-1,0,2,-3]
     * 期望：[-1,2,-3,0,0]
     */
    @Test
    void testMoveZeroes_WithNegativeNumbers() {
        // Given
        int[] nums = {0, -1, 0, 2, -3};
        int[] expected = {-1, 2, -3, 0, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
    }
    
    /**
     * 测试用例9：对比两种解法结果一致性
     */
    @Test
    void testMoveZeroes_TwoMethodsConsistency() {
        // Given
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums2 = {0, 1, 0, 3, 12};
        
        // When
        moveZeroes.moveZeroes(nums1);           // 方法一：双指针
        moveZeroes.moveZeroesTwoPass(nums2);    // 方法二：两次遍历
        
        // Then
        assertThat(nums1).isEqualTo(nums2);
    }
    
    /**
     * 测试用例10：测试相对顺序保持不变
     * 验证非零元素的相对顺序在移动后保持不变
     */
    @Test
    void testMoveZeroes_PreserveRelativeOrder() {
        // Given - 创建具有重复非零元素的数组
        int[] nums = {0, 2, 0, 1, 0, 3, 2, 0};
        int[] expected = {2, 1, 3, 2, 0, 0, 0, 0};
        
        // When
        moveZeroes.moveZeroes(nums);
        
        // Then
        assertThat(nums).isEqualTo(expected);
        
        // 额外验证：非零元素的相对顺序
        assertThat(nums[0]).isEqualTo(2);
        assertThat(nums[1]).isEqualTo(1);
        assertThat(nums[2]).isEqualTo(3);
        assertThat(nums[3]).isEqualTo(2);
    }
}
