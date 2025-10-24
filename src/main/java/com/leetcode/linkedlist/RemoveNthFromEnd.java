package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 
 * 题目链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 难度：中等
 * 标签：链表、双指针
 * 
 * ==================== 题目描述 ====================
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 解释：
 *   原链表：1 → 2 → 3 → 4 → 5
 *   删除倒数第2个节点（4）后：1 → 2 → 3 → 5
 * 
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 解释：删除唯一的节点后，链表为空
 * 
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 解释：删除倒数第1个节点（2）后，链表为 [1]
 * 
 * 示例 4：
 * 输入：head = [1,2], n = 2
 * 输出：[2]
 * 解释：删除倒数第2个节点（1，即头节点）后，链表为 [2]
 * 
 * 提示：
 * - 链表中结点的数目为 sz
 * - 1 <= sz <= 30
 * - 0 <= Node.val <= 100
 * - 1 <= n <= sz
 * 
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 
 * ==================== 解题思路 ====================
 * 
 * 【核心技巧：快慢指针 + 虚拟头节点】⭐
 * 
 * 思路：
 * 1. 创建虚拟头节点 dummy，简化删除头节点的情况
 * 2. 快指针先走 n+1 步（保证慢指针最终停在待删除节点的前一个）
 * 3. 快慢指针同时前进，直到快指针到达末尾
 * 4. 慢指针.next = 慢指针.next.next（删除节点）
 * 5. 返回 dummy.next
 * 
 * 图解过程（以 [1,2,3,4,5], n=2 为例）：
 * 
 * 初始状态：
 *   dummy → 1 → 2 → 3 → 4 → 5 → null
 *   ↑
 *   slow, fast
 * 
 * 快指针先走 n+1=3 步：
 *   dummy → 1 → 2 → 3 → 4 → 5 → null
 *   ↑               ↑
 *   slow           fast
 * 
 * 快慢指针同时走，直到 fast == null：
 *   dummy → 1 → 2 → 3 → 4 → 5 → null
 *               ↑               ↑
 *              slow           fast
 * 
 * 删除节点：slow.next = slow.next.next
 *   dummy → 1 → 2 → 3 → 5 → null
 *               ↑       
 *              slow    
 * 
 * 时间复杂度：O(n)，只需一次遍历
 * 空间复杂度：O(1)
 * 
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class RemoveNthFromEnd {
    
    /**
     * 删除链表的倒数第 N 个节点
     * 
     * 核心思路：快慢指针 + 虚拟头节点
     * 
     * @param head 链表头节点
     * @param n 倒数第 n 个节点
     * @return 删除节点后的链表头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 方法一：快慢指针（推荐，一趟扫描）
        return removeNthFromEndTwoPointers(head, n);
        
        // 方法二：转数组（备选方案，空间O(n)）
        // return removeNthFromEndArray(head, n);
    }

    /**
     * 方法一：快慢指针 + 虚拟头节点 ⭐ 推荐
     * 
     * 核心要点：
     * 1. 使用虚拟头节点 dummy，统一处理删除头节点的情况
     * 2. 快指针先走 n+1 步（注意是 n+1，不是 n）
     * 3. 快慢指针同时前进，直到快指针到达 null
     * 4. 此时慢指针指向待删除节点的前一个节点
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        // 创建虚拟头节点，简化边界处理
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // 快指针先走 n+1 步（多走一步是为了让 slow 停在待删除节点的前一个）
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // 快慢指针同时前进
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // 删除节点：跳过 slow 的下一个节点
        slow.next = slow.next.next;
        
        return dummy.next;
    }

    /**
     * 方法二：转数组
     * 
     * 思路：
     * 1. 转为数组
     * 2. 计算要删除的索引：length - n
     * 3. 构造新数组，跳过该索引
     * 4. 重新构建链表
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private ListNode removeNthFromEndArray(ListNode head, int n) {
        // 转为数组
        int[] arr = ListNode.toArray(head);
        
        // 计算要删除的索引
        int removeIndex = arr.length - n;
        
        // 构造新数组，跳过该索引
        int[] newArr = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != removeIndex) {
                newArr[j++] = arr[i];
            }
        }
        
        // 重新构建链表
        return ListNode.fromArray(newArr);
    }

}

