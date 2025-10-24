package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

/**
 * 876. 链表的中间结点
 * 
 * 题目链接：https://leetcode.cn/problems/middle-of-the-linked-list/
 * 难度：简单
 * 标签：链表、双指针
 * 
 * ==================== 题目描述 ====================
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3
 * 
 * 示例 2：
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点（值为 4）
 * 
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 解释：单节点链表，返回头节点
 * 
 * 提示：
 * - 链表的结点数范围是 [1, 100]
 * - 1 <= Node.val <= 100
 * 
 * ==================== 解题思路 ====================
 * 
 * 【快慢指针】⭐ 经典技巧
 * 
 * 核心思想：
 * - 快指针每次走 2 步
 * - 慢指针每次走 1 步
 * - 当快指针到达末尾时，慢指针刚好在中点
 * 
 * 图解过程（以 [1,2,3,4,5] 为例）：
 * 
 * 初始状态：
 *   1 → 2 → 3 → 4 → 5 → null
 *   ↑
 *   slow, fast
 * 
 * 第 1 步：
 *   1 → 2 → 3 → 4 → 5 → null
 *       ↑       ↑
 *      slow    fast
 * 
 * 第 2 步：
 *   1 → 2 → 3 → 4 → 5 → null
 *           ↑           ↑
 *          slow       fast
 * 
 * fast.next == null，循环结束，slow 指向中点 3
 * 
 * 奇数节点：fast.next == null 时停止
 * 偶数节点：fast == null 时停止，slow 指向第二个中点
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * 
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class MiddleNode {
    
    /**
     * 找到链表的中间节点
     * 
     * 核心技巧：快慢指针
     * 
     * @param head 链表头节点
     * @return 中间节点（如果有两个中间节点，返回第二个）
     */
    public ListNode middleNode(ListNode head) {
        // 边界情况：空链表或单节点
        if (head == null || head.next == null) {
            return head;
        }
        
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        
        // 快指针每次走 2 步，慢指针每次走 1 步
        while (fast != null && fast.next != null) {
            slow = slow.next;       // 慢指针走 1 步
            fast = fast.next.next;  // 快指针走 2 步
        }
        
        // 当快指针到达末尾时，慢指针指向中点
        // - 奇数节点：fast.next == null，slow 指向唯一的中点
        // - 偶数节点：fast == null，slow 指向第二个中点
        return slow;
    }
}

