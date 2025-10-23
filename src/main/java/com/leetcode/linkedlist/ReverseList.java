package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

/**
 * 206. 反转链表
 * 
 * 题目链接：https://leetcode.cn/problems/reverse-linked-list/
 * 难度：简单
 * 标签：链表、递归
 * 
 * ==================== 题目描述 ====================
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 * 
 * 提示：
 * - 链表中节点的数目范围是 [0, 5000]
 * - -5000 <= Node.val <= 5000
 * 
 * ==================== 解题思路 ====================
 * 
 * 【方法一：迭代法（双指针）】
 * 核心思想：遍历链表，逐个反转节点的指针方向
 * 
 * 过程图解：
 *   原链表：1 → 2 → 3 → null
 *   
 *   步骤1：null ← 1   2 → 3 → null
 *   步骤2：null ← 1 ← 2   3 → null
 *   步骤3：null ← 1 ← 2 ← 3
 * 
 * 关键点：
 * 1. 使用三个指针：prev（前驱）、curr（当前）、next（后继）
 * 2. next 用于临时保存下一个节点，防止链表断裂
 * 3. 每次循环做三件事：
 *    - 保存下一个节点
 *    - 反转当前节点的指针
 *    - 移动 prev 和 curr
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * 
 * 【方法二：递归法】
 * 核心思想：先递归到链表末尾，再从后往前反转
 * 
 * 递归过程：
 *   reverseList(1 → 2 → 3 → null)
 *     ↓ 递归
 *   reverseList(2 → 3 → null)
 *     ↓ 递归
 *   reverseList(3 → null)
 *     ↓ 递归
 *   reverseList(null) ← 返回
 *     ↑ 回溯时反转：3 ← 2
 *     ↑ 回溯时反转：3 ← 2 ← 1
 * 
 * 关键操作：
 * - head.next.next = head  (让下一个节点指回当前节点)
 * - head.next = null       (断开原指针，避免循环)
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n) - 递归调用栈
 * 
 * @Author Dxboy266
 * @Date 2025-10-21
 */
public class ReverseList {
    
    /**
     * 方法一：迭代法（双指针）
     * 
     * 核心思想：使用三个指针遍历链表，逐个反转节点的指针方向
     * 
     * 算法步骤：
     * 1. 初始化 prev = null, curr = head
     * 2. 遍历链表，每次循环：
     *    a. 保存下一个节点：next = curr.next
     *    b. 反转当前指针：curr.next = prev
     *    c. 移动指针：prev = curr, curr = next
     * 3. 返回 prev（新的头节点）
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param head 链表头节点
     * @return 反转后的链表头节点
     */
    public ListNode reverseList(ListNode head) {
        // 1. 初始化指针
        ListNode prev = null;  // 前一个节点（初始为 null）
        ListNode curr = head;  // 当前节点
        
        // 2. 遍历链表
        while (curr != null) {
            // a. 保存下一个节点（防止链表断裂）
            ListNode next = curr.next;
            
            // b. 反转当前节点的指针（核心操作）
            curr.next = prev;
            
            // c. 移动指针到下一个位置
            prev = curr;  // prev 后移
            curr = next;  // curr 后移
        }
        
        // 3. 返回新的头节点（原链表的最后一个节点）
        return prev;
    }

    /**
     * 方法二：递归法
     * 
     * 核心思想：先递归到链表末尾，再从后往前反转
     * 
     * 递归三要素：
     * 1. 递归终止条件：head == null 或 head.next == null
     * 2. 递归调用：reverseListRecursive(head.next)
     * 3. 本层处理：反转当前节点和下一个节点的指针
     * 
     * 关键理解：
     * - 假设后面的链表已经反转好了（递归的信仰）
     * - 只需要处理当前节点和下一个节点的关系
     * - head.next.next = head 是精髓所在
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 递归调用栈的深度
     * 
     * @param head 链表头节点
     * @return 反转后的链表头节点
     */
    public ListNode reverseListRecursive(ListNode head) {
        // 1. 递归终止条件
        // - 空链表，直接返回 null
        // - 单节点，直接返回该节点
        if (head == null || head.next == null) {
            return head;
        }
        
        // 2. 递归反转后面的链表
        // 假设后面的链表已经反转好了，newHead 是新的头节点
        // 例如：1 → 2 → 3 → null，递归后变成 1 → 2 ← 3，newHead = 3
        ListNode newHead = reverseListRecursive(head.next);
        
        // 3. 反转当前节点和下一个节点的指针
        // head.next 是下一个节点
        // head.next.next 是下一个节点的 next 指针
        // 让下一个节点指回当前节点（核心操作）
        head.next.next = head;
        
        // 4. 断开当前节点的原 next 指针，避免形成环
        head.next = null;
        
        // 5. 返回新的头节点（一直是最后一个节点）
        return newHead;
    }
}

