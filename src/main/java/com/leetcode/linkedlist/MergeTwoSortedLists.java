package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

/**
 * 21. 合并两个有序链表
 * 
 * 题目链接：https://leetcode.cn/problems/merge-two-sorted-lists/
 * 难度：简单
 * 标签：链表、递归
 * 
 * ==================== 题目描述 ====================
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * - 两个链表的节点数目范围是 [0, 50]
 * - -100 <= Node.val <= 100
 * - l1 和 l2 均按 非递减顺序 排列
 * 
 * ==================== 解题思路 ====================
 * 
 * 方法一：迭代法（推荐）★★★
 * - 使用虚拟头节点简化边界处理
 * - 比较两个链表当前节点值，选择较小的节点
 * - 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
 * - 空间复杂度：O(1) - 只使用常数空间
 * - 优点：思路清晰，易于理解，空间效率高
 * - 缺点：代码稍长，需要处理边界情况
 * 
 * 方法二：递归法（优雅）
 * - 递归地比较两个链表的头节点
 * - 选择较小的节点作为合并后链表的头节点
 * - 递归处理剩余部分
 * - 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
 * - 空间复杂度：O(m+n) - 递归调用栈空间
 * - 优点：代码简洁优雅，逻辑清晰
 * - 缺点：空间复杂度较高，可能栈溢出
 * 
 * 方法三：原地合并（空间优化）
 * - 不创建新节点，直接修改原链表
 * - 使用双指针遍历两个链表
 * - 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
 * - 空间复杂度：O(1) - 只使用常数空间
 * - 优点：空间效率最高
 * - 缺点：会修改原链表，可能不符合题意
 * 
 * ==================== 知识点总结 ====================
 * 
 * 1. 链表操作技巧：
 *    - 虚拟头节点：简化边界处理
 *    - 双指针：同时遍历两个链表
 *    - 节点拼接：直接改变指针指向
 * 
 * 2. 算法优化思路：
 *    - 迭代法：时间O(m+n)，空间O(1)，适合内存敏感场景
 *    - 递归法：时间O(m+n)，空间O(m+n)，代码简洁
 *    - 原地合并：最高空间效率，但会修改原数据
 * 
 * 3. 易错点：
 *    - 空链表的处理
 *    - 链表遍历结束后的剩余节点
 *    - 虚拟头节点的正确使用
 *    - 指针移动的顺序
 * 
 * 4. 相关题目：
 *    - 23. 合并K个升序链表（分治法）
 *    - 1669. 合并两个链表（指定位置合并）
 *    - 面试题 02.05. 链表求和（数字相加）
 *    - 148. 排序链表（归并排序）
 * 
 * @Author Dxboy266
 * @Date 2025-10-14
 */
public class MergeTwoSortedLists {
    
    /**
     * 方法一：迭代法（推荐）★★★
     * 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
     * 空间复杂度：O(1) - 只使用常数空间
     * 
     * 思路：
     * 1. 使用虚拟头节点简化边界处理
     * 2. 比较两个链表当前节点值，选择较小的节点
     * 3. 移动指针到下一个节点
     * 4. 处理剩余节点
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建虚拟头节点，简化边界处理
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;
        
        // 同时遍历两个链表
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // 处理剩余节点
        current.next = (list1 == null) ? list2 : list1;
        
        return dummyHead.next;
    }
    
    /**
     * 方法二：递归法（优雅）
     * 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
     * 空间复杂度：O(m+n) - 递归调用栈空间
     * 
     * 思路：
     * 1. 递归地比较两个链表的头节点
     * 2. 选择较小的节点作为合并后链表的头节点
     * 3. 递归处理剩余部分
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // 递归终止条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        // 比较两个链表的头节点
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
    
    /**
     * 方法三：原地合并（空间优化）
     * 时间复杂度：O(m+n) - 需要遍历两个链表的所有节点
     * 空间复杂度：O(1) - 只使用常数空间
     * 
     * 注意：此方法会修改原链表，可能不符合题意要求
     * 
     * 思路：
     * 1. 不创建新节点，直接修改原链表
     * 2. 使用双指针遍历两个链表
     * 3. 将节点重新链接
     */
    public ListNode mergeTwoListsInPlace(ListNode list1, ListNode list2) {
        // 处理空链表情况
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // 确定头节点
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        
        ListNode current = head;
        
        // 合并两个链表
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // 处理剩余节点
        current.next = (list1 == null) ? list2 : list1;
        
        return head;
    }
    
    /**
     * 补充说明：三种方法对比
     * 
     * 1. 时间复杂度：
     *    - 所有方法都是 O(m+n)，需要遍历两个链表的所有节点
     * 
     * 2. 空间复杂度：
     *    - 迭代法：O(1) - 最优
     *    - 递归法：O(m+n) - 最差
     *    - 原地合并：O(1) - 最优
     * 
     * 3. 代码复杂度：
     *    - 迭代法：中等，需要处理边界情况
     *    - 递归法：最简单，逻辑清晰
     *    - 原地合并：中等，需要确定头节点
     * 
     * 4. 实用性：
     *    - 迭代法：最实用，适合生产环境
     *    - 递归法：适合面试展示递归思维
     *    - 原地合并：适合内存极度受限的场景
     * 
     * 5. 面试建议：
     *    - 首选迭代法：展示扎实的链表操作能力
     *    - 可选递归法：展示递归思维和代码简洁性
     *    - 可选原地合并：展示空间优化思维
     * 
     * 6. 实际应用：
     *    - 数据库查询结果合并
     *    - 文件归并排序
     *    - 多路归并算法
     *    - 分布式系统中的数据合并
     */
}
