package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 
 * 题目链接：https://leetcode.cn/problems/swap-nodes-in-pairs/
 * 难度：中等
 * 标签：链表、递归
 * 
 * ==================== 题目描述 ====================
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 解释：
 *   原链表：1 → 2 → 3 → 4
 *   交换后：2 → 1 → 4 → 3
 * 
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 
 * 示例 4：
 * 输入：head = [1,2,3]
 * 输出：[2,1,3]
 * 解释：
 *   原链表：1 → 2 → 3
 *   交换后：2 → 1 → 3（最后一个节点不交换）
 * 
 * 提示：
 * - 链表中节点的数目在范围 [0, 100] 内
 * - 0 <= Node.val <= 100
 * 
 * ==================== 解题思路 ====================
 * 
 * 【方法一：迭代法（推荐）】
 * 核心思想：使用虚拟头节点，逐对交换相邻节点
 * 
 * 交换过程图解（以 1 → 2 → 3 → 4 为例）：
 * 
 * 初始状态：
 *   dummy → 1 → 2 → 3 → 4
 *   prev
 * 
 * 第一次交换：
 *   dummy → 2 → 1 → 3 → 4
 *          prev
 * 
 * 第二次交换：
 *   dummy → 2 → 1 → 4 → 3
 *                  prev
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * 
 * 【方法二：递归法】
 * 核心思想：交换前两个节点，然后递归处理剩余部分
 * 
 * 递归分解：
 *   swapPairs(1 → 2 → 3 → 4)
 *   = 2 → 1 → swapPairs(3 → 4)
 *   = 2 → 1 → 4 → 3 → swapPairs(null)
 *   = 2 → 1 → 4 → 3
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n) - 递归调用栈
 * 
 * @Author Dxboy266
 * @Date 2025-10-21
 */
public class SwapPairs {
    
    /**
     * 方法一：迭代法（虚拟头节点）
     * 
     * 核心思想：
     * 使用虚拟头节点简化操作，逐对交换相邻节点
     * 
     * 交换步骤（以 prev → 1 → 2 → 3 为例）：
     * 1. 保存 first = 1, second = 2
     * 2. first.next = second.next  →  1 → 3
     * 3. prev.next = second         →  prev → 2
     * 4. second.next = first        →  2 → 1
     * 结果：prev → 2 → 1 → 3
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairs(ListNode head) {
        // 1. 创建虚拟头节点，简化边界处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;  // prev 指向已处理部分的最后一个节点
        
        // 2. 遍历链表，每次处理两个节点
        // 确保至少有两个节点可以交换
        while (prev.next != null && prev.next.next != null) {
            // 3. 保存需要交换的两个节点
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // 4. 进行交换（关键的三步）
            // 步骤顺序很重要，不能搞错！
            first.next = second.next;   // first 指向 second 后面的节点
            prev.next = second;          // prev 指向 second
            second.next = first;         // second 指向 first
            
            // 5. 移动 prev 到下一对的前面
            prev = first;
        }
        
        // 6. 返回新的头节点
        return dummy.next;
    }

    /**
     * 方法二：递归法
     * 
     * 核心思想：
     * 交换前两个节点，然后递归处理剩余部分
     * 
     * 递归三要素：
     * 1. 递归终止条件：head == null 或 head.next == null
     * 2. 递归调用：swapPairsRecursive(second.next)
     * 3. 本层处理：交换 first 和 second
     * 
     * 递归过程（以 1 → 2 → 3 → 4 为例）：
     * swapPairs(1 → 2 → 3 → 4)
     *   ↓ 递归
     * swapPairs(3 → 4)
     *   ↓ 递归
     * swapPairs(null) ← 返回 null
     *   ↑ 回溯：交换 3 和 4
     *   ↑ 回溯：交换 1 和 2
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 递归调用栈的深度
     * 
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // 1. 递归终止条件
        // 如果没有节点或只有一个节点，无需交换
        if (head == null || head.next == null) {
            return head;
        }
        
        // 2. 保存两个需要交换的节点
        ListNode first = head;
        ListNode second = head.next;
        
        // 3. 递归处理后面的节点
        // first 的 next 应该指向递归处理后的结果
        first.next = swapPairsRecursive(second.next);
        
        // 4. 交换 first 和 second
        second.next = first;
        
        // 5. 返回新的头节点（原来的第二个节点）
        return second;
    }
}

