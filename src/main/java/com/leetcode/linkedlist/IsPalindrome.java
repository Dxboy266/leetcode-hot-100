package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

import java.util.Stack;

/**
 * 234. 回文链表
 * 
 * 题目链接：https://leetcode.cn/problems/palindrome-linked-list/
 * 难度：简单
 * 标签：链表、双指针、栈
 * 
 * ==================== 题目描述 ====================
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
 * 如果是，返回 true ；否则，返回 false 。
 * 
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 解释：
 *   1 → 2 → 2 → 1
 *   前半部分：1 → 2
 *   后半部分：2 → 1（反转后也是 2 → 1）
 * 
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 * 解释：
 *   1 → 2 不是回文
 * 
 * 示例 3：
 * 输入：head = [1]
 * 输出：true
 * 解释：单节点链表是回文
 * 
 * 示例 4：
 * 输入：head = [1,2,3,2,1]
 * 输出：true
 * 解释：
 *   1 → 2 → 3 → 2 → 1
 *   是回文链表
 * 
 * 提示：
 * - 链表中节点数目在范围 [1, 10^5] 内
 * - 0 <= Node.val <= 9
 * 
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * 
 * ==================== 解题思路 ====================
 * 
 * 【方法一：转数组 + 双指针】
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 
 * 【方法二：栈】
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * 
 * 【方法三：快慢指针 + 反转后半部分】⭐ 推荐
 * 核心思想：
 * 1. 用快慢指针找到链表中点
 * 2. 反转后半部分链表
 * 3. 比较前半部分和反转后的后半部分
 * 4. （可选）恢复链表
 * 
 * 过程图解（以 [1,2,3,2,1] 为例）：
 * 原链表：1 → 2 → 3 → 2 → 1
 * 
 * 步骤1：快慢指针找中点
 *   slow 到达 3，fast 到达末尾
 * 
 * 步骤2：反转后半部分（从 slow 开始）
 *   前半：1 → 2 → 3
 *   后半：1 ← 2（反转后）
 * 
 * 步骤3：双指针比较
 *   前半：1 → 2 → 3
 *   后半：1 ← 2
 *   比较：1==1, 2==2 ✓
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * 
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class IsPalindrome {

    /**
     * 判断链表是否为回文链表
     * 
     * 核心思路：快慢指针 + 反转后半部分
     * 
     * @param head 链表头节点
     * @return 是否为回文链表
     */
    public boolean isPalindrome(ListNode head) {
        // 方法一：数组实现（空间O(n)）
        // return isPalindromeArray(head);
        
        // 方法二：栈实现（空间O(n)）
        // return isPalindromeStack(head);
        
        // 方法三：快慢指针 + 反转（空间O(1)）⭐ 推荐
        return isPalindromeTwoPointers(head);
    }
    
    /**
     * 方法一：转数组 + 双指针
     * 
     * 思路：
     * 1. 将链表转为数组
     * 2. 用双指针从两端向中间比较
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isPalindromeArray(ListNode head) {
        if (head == null) {
            return false;
        }
        
        // 转为数组
        int[] array = ListNode.toArray(head);
        
        // 双指针判断
        int l = 0, r = array.length - 1;
        while (l < r) {
            if (array[l] != array[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /**
     * 方法二：栈
     * 
     * 思路：
     * 1. 第一次遍历：将所有节点值入栈
     * 2. 第二次遍历：依次弹栈比较
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isPalindromeStack(ListNode head) {
        if (head == null) {
            return false;
        }
        
        // 将所有值入栈
        Stack<Integer> stack = ListNode.toStack(head);
        
        // 依次弹栈比较
        while (head != null) {
            if (stack.pop() != head.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 方法三：快慢指针 + 反转后半部分 ⭐ 推荐
     * 
     * 核心思路：
     * 1. 用快慢指针找到链表中点
     * 2. 反转后半部分链表
     * 3. 同时遍历前半部分和反转后的后半部分，比较值是否相等
     * 
     * 图解过程（以 [1,2,3,2,1] 为例）：
     * 
     * 步骤1：快慢指针找中点
     *   slow: 1 → 2 → 3
     *   fast: 1 → 3 → null（走完）
     *   结果：slow 停在中点 3
     * 
     * 步骤2：反转后半部分（从 slow 开始）
     *   原链表后半：3 → 2 → 1
     *   反转后：1 ← 2 ← 3（pre = 1）
     * 
     * 步骤3：双指针比较
     *   前半：1 → 2 → ...
     *   后半：1 → 2（已反转）
     *   比较：1==1 ✓, 2==2 ✓
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isPalindromeTwoPointers(ListNode head) {
        if (head == null) {
            return false;
        }
        
        // 步骤1：快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;       // 慢指针走一步
            fast = fast.next.next;  // 快指针走两步
        }
        // 循环结束后，slow 指向中点（或中点偏右）
        
        // 步骤2：反转后半部分链表
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;  // 保存下一个节点
            slow.next = pre;            // 反转指针
            pre = slow;                 // pre 前进
            slow = next;                // slow 前进
        }
        // 循环结束后，pre 指向反转后的后半部分头节点
        
        // 步骤3：双指针比较前半部分和反转后的后半部分
        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        
        return true;
    }
}

