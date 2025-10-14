package com.leetcode.stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

/**
 * ValidParentheses 测试类
 * 
 * 测试用例覆盖：
 * - 基本功能测试
 * - 边界条件测试
 * - 特殊情况测试
 * - 三种解法对比测试
 */
class ValidParenthesesTest {
    
    private ValidParentheses validParentheses;
    
    @BeforeEach
    void setUp() {
        validParentheses = new ValidParentheses();
    }
    
    /**
     * 测试用例1：基本功能 - 简单匹配
     * 输入："()"
     * 期望：true
     */
    @Test
    void testIsValid_SimpleMatch() {
        // Given
        String s = "()";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isTrue();
    }
    
    /**
     * 测试用例2：基本功能 - 多种括号匹配
     * 输入："()[]{}"
     * 期望：true
     */
    @Test
    void testIsValid_MultipleTypes() {
        // Given
        String s = "()[]{}";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isTrue();
    }
    
    /**
     * 测试用例3：基本功能 - 嵌套括号
     * 输入："{[]}"
     * 期望：true
     */
    @Test
    void testIsValid_NestedBrackets() {
        // Given
        String s = "{[]}";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isTrue();
    }
    
    /**
     * 测试用例4：错误情况 - 不匹配
     * 输入："(]"
     * 期望：false
     */
    @Test
    void testIsValid_Mismatch() {
        // Given
        String s = "(]";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isFalse();
    }
    
    /**
     * 测试用例5：错误情况 - 交错括号
     * 输入："([)]"
     * 期望：false
     */
    @Test
    void testIsValid_InterleavedBrackets() {
        // Given
        String s = "([)]";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isFalse();
    }
    
    /**
     * 测试用例6：边界条件 - 空字符串
     * 输入：""
     * 期望：true
     */
    @Test
    void testIsValid_EmptyString() {
        // Given
        String s = "";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isTrue();
    }
    
    /**
     * 测试用例7：边界条件 - 奇数长度
     * 输入："("
     * 期望：false
     */
    @Test
    void testIsValid_OddLength() {
        // Given
        String s = "(";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isFalse();
    }
    
    /**
     * 测试用例8：错误情况 - 只有右括号
     * 输入：")"
     * 期望：false
     */
    @Test
    void testIsValid_OnlyRightBrackets() {
        // Given
        String s = ")";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isFalse();
    }
    
    /**
     * 测试用例9：错误情况 - 只有左括号
     * 输入："((("
     * 期望：false
     */
    @Test
    void testIsValid_OnlyLeftBrackets() {
        // Given
        String s = "(((";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isFalse();
    }
    
    /**
     * 测试用例10：复杂嵌套情况
     * 输入："((((()))))"
     * 期望：true
     */
    @Test
    void testIsValid_DeepNesting() {
        // Given
        String s = "((((()))))";
        
        // When
        boolean result = validParentheses.isValid(s);
        
        // Then
        assertThat(result).isTrue();
    }
    
    /**
     * 测试用例11：三种解法结果一致性测试
     */
    @Test
    void testAllMethodsConsistency() {
        // Given - 测试用例集合
        String[] testCases = {
            "()",           // true
            "()[]{}",       // true
            "(]",           // false
            "([)]",         // false
            "{[]}",         // true
            "",             // true
            "(",            // false
            ")",            // false
            "((()))",       // true
            "((())",        // false
            "(()))",        // false
            "([{}])",       // true
            "([{]])",       // false
            "(([[{{}}]]))"  // true
        };
        
        // When & Then - 验证三种方法结果一致
        for (String testCase : testCases) {
            boolean result1 = validParentheses.isValid(testCase);
            boolean result2 = validParentheses.isValidAscii(testCase);
            boolean result3 = validParentheses.isValidArray(testCase);
            
            assertThat(result1)
                .as("测试用例: %s", testCase)
                .isEqualTo(result2)
                .isEqualTo(result3);
        }
    }
    
    /**
     * 测试用例12：ASCII方法特定测试
     */
    @Test
    void testIsValidAscii_SpecificCases() {
        // Given
        String s1 = "()[]{}";
        String s2 = "([)]";
        
        // When
        boolean result1 = validParentheses.isValidAscii(s1);
        boolean result2 = validParentheses.isValidAscii(s2);
        
        // Then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
    
    /**
     * 测试用例13：数组方法特定测试
     */
    @Test
    void testIsValidArray_SpecificCases() {
        // Given
        String s1 = "{[]}";
        String s2 = "(]";
        
        // When
        boolean result1 = validParentheses.isValidArray(s1);
        boolean result2 = validParentheses.isValidArray(s2);
        
        // Then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
    
    /**
     * 测试用例14：边界情况 - 单个字符
     */
    @Test
    void testIsValid_SingleCharacter() {
        // Given
        String[] leftBrackets = {"(", "[", "{"};
        String[] rightBrackets = {")", "]", "}"};
        
        // When & Then
        for (String left : leftBrackets) {
            assertThat(validParentheses.isValid(left)).isFalse();
        }
        
        for (String right : rightBrackets) {
            assertThat(validParentheses.isValid(right)).isFalse();
        }
    }
    
    /**
     * 测试用例15：长字符串性能测试
     */
    @Test
    void testIsValid_LongString() {
        // Given - 创建1000个嵌套括号的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append("(");
        }
        for (int i = 0; i < 500; i++) {
            sb.append(")");
        }
        String longString = sb.toString();
        
        // When
        boolean result = validParentheses.isValid(longString);
        
        // Then
        assertThat(result).isTrue();
    }
}
