package com.leetcode.string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * GroupAnagrams 测试类
 */
class GroupAnagramsTest {
    
    private final GroupAnagrams solution = new GroupAnagrams();
    
    /**
     * 辅助方法：比较两个分组结果是否相同（忽略顺序）
     */
    private boolean isEqualGroup(List<List<String>> actual, List<List<String>> expected) {
        if (actual.size() != expected.size()) {
            return false;
        }
        
        // 将每个分组转换为Set进行比较
        Set<Set<String>> actualSets = new HashSet<>();
        Set<Set<String>> expectedSets = new HashSet<>();
        
        for (List<String> group : actual) {
            actualSets.add(new HashSet<>(group));
        }
        
        for (List<String> group : expected) {
            expectedSets.add(new HashSet<>(group));
        }
        
        return actualSets.equals(expectedSets);
    }
    
    @Test
    void testExample1() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("bat"),
            Arrays.asList("nat", "tan"),
            Arrays.asList("ate", "eat", "tea")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "测试用例1失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testExample2() {
        String[] strs = {""};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "测试用例2失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testExample3() {
        String[] strs = {"a"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("a")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "测试用例3失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testSingleGroup() {
        String[] strs = {"abc", "bca", "cab", "acb", "bac", "cba"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("abc", "bca", "cab", "acb", "bac", "cba")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "单组测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testMultipleGroups() {
        String[] strs = {"listen", "silent", "enlist", "hello", "world", "rowld"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("listen", "silent", "enlist"),
            Arrays.asList("hello"),
            Arrays.asList("world", "rowld")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "多组测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testEmptyArray() {
        String[] strs = {};
        
        List<List<String>> expected = new ArrayList<>();
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "空数组测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testSameWord() {
        String[] strs = {"a", "a", "a"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("a", "a", "a")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "相同单词测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testDifferentLengths() {
        String[] strs = {"a", "ab", "ba", "abc", "cab", "bca"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("a"),
            Arrays.asList("ab", "ba"),
            Arrays.asList("abc", "cab", "bca")
        );
        
        List<List<String>> result = solution.groupAnagrams(strs);
        assertTrue(isEqualGroup(result, expected), 
            "不同长度测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    // 测试其他方法
    @Test
    void testGroupAnagramsCount() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("bat"),
            Arrays.asList("nat", "tan"),
            Arrays.asList("ate", "eat", "tea")
        );
        
        List<List<String>> result = solution.groupAnagramsCount(strs);
        assertTrue(isEqualGroup(result, expected), 
            "字符计数方法测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    @Test
    void testGroupAnagramsPrime() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> expected = Arrays.asList(
            Arrays.asList("bat"),
            Arrays.asList("nat", "tan"),
            Arrays.asList("ate", "eat", "tea")
        );
        
        List<List<String>> result = solution.groupAnagramsPrime(strs);
        assertTrue(isEqualGroup(result, expected), 
            "质数映射方法测试失败: 期望 " + expected + ", 实际 " + result);
    }
    
    
    @Test
    void testPrimeOverflowRisk() {
        // 测试可能导致溢出的情况
        String[] strs = {"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"};
        // 这个测试可能会抛出异常，说明质数映射方法有溢出风险
        assertThrows(ArithmeticException.class, () -> {
            solution.groupAnagramsPrime(strs);
        }, "质数映射方法应该检测到溢出并抛出异常");
    }
    
    @Test
    void testCountHandlesLongStrings() {
        // 测试字符计数方法处理长字符串
        String[] strs = {"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "a"};
        
        List<List<String>> result = solution.groupAnagramsCount(strs);
        
        // 应该成功处理，不会抛出异常
        assertEquals(2, result.size(), "字符计数方法应该能处理长字符串");
    }
    
    @Test
    void testAllMethodsConsistency() {
        String[] strs = {"listen", "silent", "enlist", "hello", "world", "rowld"};
        
        List<List<String>> result1 = solution.groupAnagrams(strs);
        List<List<String>> result2 = solution.groupAnagramsCount(strs);
        List<List<String>> result3 = solution.groupAnagramsPrime(strs);
        
        assertTrue(isEqualGroup(result1, result2), 
            "方法1和方法2结果不一致");
        assertTrue(isEqualGroup(result1, result3), 
            "方法1和方法3结果不一致");
        assertTrue(isEqualGroup(result2, result3), 
            "方法2和方法3结果不一致");
    }
}
