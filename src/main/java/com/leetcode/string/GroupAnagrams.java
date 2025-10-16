package com.leetcode.string;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 
 * 题目链接：https://leetcode.cn/problems/group-anagrams/
 * 难度：中等
 * 标签：哈希表、字符串、排序
 * 
 * ==================== 题目描述 ====================
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * 
 * 示例 1：
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * 示例 2：
 * 输入: strs = [""]
 * 输出: [[""]]
 * 
 * 示例 3：
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * 
 * 提示：
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] 仅包含小写字母
 * 
 * ==================== 解题思路 ====================
 * 
 * 方法一：排序 + 哈希表（推荐）★★★
 * - 将每个字符串排序后作为key，原始字符串作为value存入HashMap
 * - 排序后的字符串相同的原始字符串就是字母异位词
 * - 时间复杂度：O(nk log k) - n是字符串个数，k是字符串平均长度
 * - 空间复杂度：O(nk) - HashMap存储所有字符串
 * - 优点：思路清晰，实现简单
 * - 缺点：排序开销较大
 * 
 * 方法二：字符计数 + 哈希表（优化）
 * - 统计每个字符串中每个字符的出现次数，将计数结果作为key
 * - 使用数组记录字符频率，然后转换为字符串作为key
 * - 时间复杂度：O(nk) - 只需遍历一次字符串
 * - 空间复杂度：O(nk) - HashMap存储空间
 * - 优点：避免了排序，时间复杂度更好
 * - 缺点：代码稍复杂，需要处理字符计数
 * 
 * 方法三：质数映射 + 哈希表（巧妙）
 * - 为每个字母分配一个唯一的质数
 * - 将字符串中每个字母对应的质数相乘作为key
 * - 异位词的乘积相同，因为乘法满足交换律
 * - 时间复杂度：O(nk) - 遍历字符串计算乘积
 * - 空间复杂度：O(nk) - HashMap存储空间
 * - 优点：数学方法，避免排序和计数
 * - 缺点：可能溢出，需要特殊处理
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 哈希表的应用场景：
 *    - 字符串分组问题
 *    - 快速查找和归类
 *    - 将复杂对象映射为简单key
 * 
 * 2. 字符串处理技巧：
 *    - 排序：将字符串标准化
 *    - 字符计数：统计频率分布
 *    - 质数映射：数学方法处理
 * 
 * 3. 算法优化思路：
 *    - 从排序法 O(nk log k) 优化到计数法 O(nk)
 *    - 选择合适的数据结构作为key
 *    - 平衡时间复杂度和代码复杂度
 * 
 * 4. 易错点：
 *    - 空字符串的处理
 *    - 字符计数数组的大小（26个小写字母）
 *    - HashMap的key选择要唯一且一致
 * 
 * 5. 相关题目：
 *    - 242. 有效的字母异位词（简化版）
 *    - 438. 找到字符串中所有字母异位词（滑动窗口）
 *    - 567. 字符串的排列（滑动窗口）
 * 
 * @Author Dxboy266
 * @Date 2025-10-16
 */
public class GroupAnagrams {
    
    /**
     * 方法一：排序 + 哈希表（推荐）★★★
     * 时间复杂度：O(nk log k) - n个字符串，每个字符串排序需要O(k log k)
     * 空间复杂度：O(nk) - HashMap存储所有字符串
     * 
     * 思路：
     * 1. 将每个字符串排序后作为key
     * 2. 原始字符串作为value存入对应的List中
     * 3. 排序后的字符串相同的原始字符串就是字母异位词
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 使用HashMap存储分组结果：key是排序后的字符串，value是原始字符串列表
        Map<String, List<String>> map = new HashMap<>();
        
        // 遍历每个字符串
        for (String str : strs) {
            // 将字符串转换为字符数组并排序
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            
            // 将排序后的字符数组转换为字符串作为key
            String sortedStr = new String(charArray);
            
            // 获取或创建对应的字符串列表
            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);  // 将原始字符串加入列表
            
            // 更新HashMap
            map.put(sortedStr, list);
        }
        
        // 返回所有分组结果
        return new ArrayList<>(map.values());
    }
    
    /**
     * 方法二：字符计数 + 哈希表（优化）
     * 时间复杂度：O(nk) - n个字符串，每个字符串遍历一次
     * 空间复杂度：O(nk) - HashMap存储空间
     * 
     * 思路：
     * 1. 统计每个字符串中每个字符的出现次数
     * 2. 将字符计数结果作为key（转换为字符串形式）
     * 3. 字符计数相同的字符串就是字母异位词
     */
    public List<List<String>> groupAnagramsCount(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // 创建字符计数数组（26个小写字母）
            int[] count = new int[26];
            
            // 统计每个字符的出现次数
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // 将计数数组转换为字符串作为key
            // 格式：a1b2c0...（字符+次数）
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append((char) ('a' + i));  // 字符
                sb.append(count[i]);          // 次数
            }
            String key = sb.toString();
            
            // 将原始字符串加入对应的分组
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * 方法三：质数映射 + 哈希表（有溢出风险，不推荐）
     * 时间复杂度：O(nk) - 遍历字符串计算乘积
     * 空间复杂度：O(nk) - HashMap存储空间
     * 
     * ⚠️ 警告：此方法存在整数溢出问题，不推荐在实际项目中使用！
     * 
     * 溢出问题：
     * 1. 当字符串较长或包含大量重复字符时，质数乘积会溢出
     * 2. 即使使用long类型，101^50 也会严重溢出
     * 3. 溢出会导致错误的key值，影响分组结果
     * 
     * 思路：
     * 1. 为每个字母分配一个唯一的质数
     * 2. 将字符串中每个字母对应的质数相乘
     * 3. 异位词的乘积相同，因为乘法满足交换律
     */
    public List<List<String>> groupAnagramsPrime(String[] strs) {
        // 为26个小写字母分配质数
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 
                       53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        
        Map<Long, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            long key = 1;
            
            // 计算字符串的质数乘积（⚠️ 可能溢出）
            for (char c : str.toCharArray()) {
                key *= primes[c - 'a'];
                // 简单的溢出检查（不够完善）
                if (key < 0) {
                    throw new ArithmeticException("整数溢出！字符串过长或包含过多重复字符");
                }
            }
            
            // 将原始字符串加入对应的分组
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        
        return new ArrayList<>(map.values());
    }
    
    
    /**
     * 补充说明：三种方法对比
     * 
     * 1. 时间复杂度对比：
     *    - 方法一：O(nk log k) - 排序开销
     *    - 方法二：O(nk) - 只需遍历
     *    - 方法三：O(nk) - 只需遍历（⚠️ 有溢出风险）
     * 
     * 2. 空间复杂度：
     *    - 三种方法都是O(nk)，主要消耗在HashMap存储
     * 
     * 3. 实现难度：
     *    - 方法一：最简单，易理解
     *    - 方法二：中等，需要处理字符计数
     *    - 方法三：较难，需要理解质数映射，且有溢出风险
     * 
     * 4. 安全性：
     *    - 方法一：✅ 安全，无溢出风险
     *    - 方法二：✅ 安全，无溢出风险
     *    - 方法三：❌ 有溢出风险，不推荐
     * 
     * 5. 适用场景：
     *    - 方法一：通用场景，面试首选
     *    - 方法二：性能要求高的场景
     *    - 方法三：❌ 不推荐，有溢出风险
     * 
     * 6. 面试建议：
     *    - 首选方法一：展示清晰的思路
     *    - 可选方法二：展示优化思维
     *    - 避免方法三：说明溢出问题
     * 
     * 7. 溢出问题详解：
     *    - 质数映射方法的根本缺陷
     *    - 为什么字符计数方法更安全
     *    - 实际项目中的最佳实践
     * 
     * 8. 实际应用：
     *    - 文本分析和处理
     *    - 搜索引擎的词汇归类
     *    - 拼写检查系统
     *    - 词汇相似度计算
     */
}
