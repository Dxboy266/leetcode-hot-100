package com.leetcode.stack;

import java.util.*;

/**
 * 20. 有效的括号
 * 
 * 题目链接：https://leetcode.cn/problems/valid-parentheses/
 * 难度：简单
 * 标签：栈、字符串
 * 
 * ==================== 题目描述 ====================
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 3. 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 * 
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 * 
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 * 
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 * 
 * ==================== 解题思路 ====================
 * 
 * 方法一：栈 + HashMap（推荐）★★★
 * - 使用栈来存储左括号
 * - 使用HashMap存储右括号到左括号的映射
 * - 遇到左括号时入栈，遇到右括号时检查栈顶元素
 * - 时间复杂度：O(n) - 遍历字符串一次
 * - 空间复杂度：O(n) - 最坏情况下栈存储所有左括号
 * - 优点：思路清晰，易于理解和实现
 * - 缺点：需要额外的HashMap空间
 * 
 * 方法二：栈 + ASCII码值（巧妙）
 * - 利用ASCII码值的关系：')' - '(' = 1，']' - '[' = 2，'}' - '{' = 2
 * - 直接比较ASCII码值判断是否匹配
 * - 时间复杂度：O(n) - 遍历字符串一次
 * - 空间复杂度：O(n) - 栈空间
 * - 优点：代码简洁，不需要额外映射
 * - 缺点：依赖ASCII码特性，可读性稍差
 * 
 * 方法三：数组模拟栈（空间优化）
 * - 使用数组模拟栈操作，在原字符数组上操作
 * - 用指针j记录栈顶位置
 * - 时间复杂度：O(n) - 遍历字符串一次
 * - 空间复杂度：O(1) - 只使用常数空间（不算输出空间）
 * - 优点：空间效率最高
 * - 缺点：代码逻辑稍复杂
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 栈的应用场景：
 *    - 括号匹配问题
 *    - 表达式求值
 *    - 递归转迭代
 *    - 浏览器前进后退
 * 
 * 2. 算法优化思路：
 *    - 方法一：经典栈解法，通用性强
 *    - 方法二：利用字符特性，代码简洁
 *    - 方法三：空间优化，适合内存敏感场景
 * 
 * 3. 易错点：
 *    - 空字符串的处理
 *    - 字符串长度为奇数的情况
 *    - 栈为空时遇到右括号
 *    - 遍历结束后栈不为空
 * 
 * 4. 相关题目：
 *    - 22. 括号生成（回溯）
 *    - 32. 最长有效括号（动态规划）
 *    - 301. 删除无效的括号（回溯）
 *    - 678. 有效的括号字符串（贪心）
 * 
 * @Author Dxboy266
 * @Date 2025-10-14
 */
public class ValidParentheses {
    
    /**
     * 方法一：栈 + HashMap（推荐）★★★
     * 时间复杂度：O(n) - 遍历字符串一次
     * 空间复杂度：O(n) - 栈和HashMap空间
     * 
     * 思路：
     * 1. 使用HashMap存储右括号到左括号的映射
     * 2. 遍历字符串，遇到左括号入栈
     * 3. 遇到右括号时检查栈顶元素是否匹配
     * 4. 最后检查栈是否为空
     */
    public boolean isValid(String s) {
        // 字符串长度为奇数，直接返回false
        if (s.length() % 2 == 1) {
            return false;
        }

        // 创建右括号到左括号的映射
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        // 使用栈处理
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // 当遇到右括号时，检查栈顶元素是否为对应的左括号
                if (stack.isEmpty() || !stack.peek().equals(map.get(c))) {
                    return false;
                }
                stack.pop();
            } else {
                // 当遇到左括号时，将其压入栈顶
                stack.push(c);
            }
        }

        // 判断栈是否为空，为空说明所有括号都有匹配
        return stack.isEmpty();
    }
    
    /**
     * 方法二：栈 + ASCII码值（巧妙）
     * 时间复杂度：O(n) - 遍历字符串一次
     * 空间复杂度：O(n) - 栈空间
     * 
     * 思路：
     * 1. 利用ASCII码值关系：')' - '(' = 1，']' - '[' = 2，'}' - '{' = 2
     * 2. 遇到右括号时检查与栈顶元素的ASCII差值
     * 3. 如果差值为1或2，说明匹配，弹出栈顶元素
     */
    public boolean isValidAscii(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 检查是否与栈顶元素匹配
            if (!stack.isEmpty() && 
                (c - stack.peek() == 1 || c - stack.peek() == 2)) {
                stack.pop();
                continue;
            }
            // 不匹配则入栈
            stack.push((int) c);
        }
        return stack.isEmpty();
    }
    
    /**
     * 方法三：数组模拟栈（空间优化）
     * 时间复杂度：O(n) - 遍历字符串一次
     * 空间复杂度：O(1) - 只使用常数空间（不算输出空间）
     * 
     * 思路：
     * 1. 使用原字符数组模拟栈操作
     * 2. 用指针j记录栈顶位置
     * 3. 遇到左括号时入栈（j++），遇到匹配的右括号时出栈（j--）
     * 4. 最后检查j是否为0
     */
    public boolean isValidArray(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        
        // 创建左括号到右括号的映射
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        char[] charArray = s.toCharArray();
        int j = 0; // 栈顶指针
        
        for (int i = 0; i < charArray.length; i++) {
            if (map.containsKey(charArray[i])) {
                // 如果是左括号，入栈
                charArray[j++] = charArray[i];
            } else if (j > 0 && map.get(charArray[j - 1]) == charArray[i]) {
                // 如果是右括号，且与栈顶左括号匹配，出栈
                j--;
            } else {
                // 只有右括号且不匹配，直接返回false
                return false;
            }
        }
        
        return j == 0;
    }
    
    /**
     * 补充说明：三种方法对比
     * 
     * 1. 可读性：
     *    - 方法一：最高，逻辑清晰易懂
     *    - 方法二：中等，需要理解ASCII码特性
     *    - 方法三：较低，需要理解数组模拟栈
     * 
     * 2. 空间效率：
     *    - 方法一：O(n) - 栈 + HashMap
     *    - 方法二：O(n) - 栈
     *    - 方法三：O(1) - 常数空间
     * 
     * 3. 通用性：
     *    - 方法一：最高，适用于各种括号匹配问题
     *    - 方法二：中等，依赖ASCII码特性
     *    - 方法三：较低，需要修改原数组
     * 
     * 4. 面试建议：
     *    - 首选方法一：展示扎实的栈应用能力
     *    - 可选方法二：展示对字符特性的理解
     *    - 可选方法三：展示空间优化思维
     * 
     * 5. 实际应用：
     *    - 编译器语法检查
     *    - 代码编辑器括号匹配
     *    - 表达式解析器
     *    - 配置文件解析
     */
}
