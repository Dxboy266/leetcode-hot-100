package com.leetcode.linkedlist;

import com.leetcode.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 
 * 题目链接：https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * 难度：简单
 * 标签：链表、双指针、哈希表
 * 
 * ==================== 题目描述 ====================
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 
 * 图示两个链表在节点 c1 开始相交：
 * 
 *     a1 → a2
 *              ↘
 *                c1 → c2 → c3
 *              ↗
 *     b1 → b2 → b3
 * 
 * 题目数据保证整个链式结构中不存在环。
 * 
 * 注意：函数返回结果后，链表必须保持其原始结构。
 * 
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8（注意，如果两个链表相交则不能为 0）
 * 从各自的头节点开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点
 * 
 * 示例 2：
 * 输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2
 * 从各自的头节点开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点
 * 
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的头节点开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值
 * 这两个链表不相交，因此返回 null
 * 
 * 提示：
 * - listA 中节点数目为 m
 * - listB 中节点数目为 n
 * - 1 <= m, n <= 3 * 10^4
 * - 1 <= Node.val <= 10^5
 * - 0 <= skipA < m
 * - 0 <= skipB < n
 * - 如果 listA 和 listB 没有交点，intersectVal 为 0
 * - 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 * 
 * 进阶：你能否设计一个时间复杂度 O(m + n)、仅用 O(1) 内存的解决方案？
 * 
 * ==================== 解题思路 ====================
 * 
 * 【方法一：哈希表】（你的实现）
 * 时间复杂度：O(m + n)
 * 空间复杂度：O(m) 或 O(n)
 * 
 * 【方法二：双指针（推荐）】
 * 核心思想：让两个指针走相同的路径长度
 * 
 * 巧妙之处：
 * - 指针 A 走完链表 A 后，从链表 B 的头开始走
 * - 指针 B 走完链表 B 后，从链表 A 的头开始走
 * - 这样两个指针走的总路径长度相同
 * - 如果有相交，一定会在相交点相遇
 * - 如果不相交，最终都会变成 null
 * 
 * 路径示例：
 * 链表 A: a1 → a2 → c1 → c2 → c3
 * 链表 B: b1 → b2 → b3 → c1 → c2 → c3
 * 
 * 指针 pA: a1 → a2 → c1 → c2 → c3 → b1 → b2 → b3 → c1（相遇）
 * 指针 pB: b1 → b2 → b3 → c1 → c2 → c3 → a1 → a2 → c1（相遇）
 * 
 * 浪漫解读：
 * 你走过我走过的路，我走过你走过的路，
 * 我们终会在某处相遇 ❤️
 * 
 * 时间复杂度：O(m + n)
 * 空间复杂度：O(1)
 * 
 * @Author Dxboy266
 * @Date 2025-10-21
 */
public class GetIntersectionNode {
    
    /**
     * 方法一：哈希表法
     * 
     * 核心思想：
     * 1. 遍历链表 A，将所有节点存入 HashSet
     * 2. 遍历链表 B，检查每个节点是否在 HashSet 中
     * 3. 第一个在 HashSet 中的节点就是相交节点
     * 
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m) - 需要存储链表 A 的所有节点
     * 
     * @param headA 链表 A 的头节点
     * @param headB 链表 B 的头节点
     * @return 相交节点，如果不相交则返回 null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 1. 将链表 A 的所有节点存入 HashSet
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        
        // 2. 遍历链表 B，检查是否有节点在 set 中
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;  // 找到相交节点
            }
            headB = headB.next;
        }
        
        // 3. 没有相交节点
        return null;
    }

    /**
     * 方法二：双指针法（最优解法）⭐
     * 
     * 核心思想：让两个指针走相同的路径长度
     * 
     * 算法步骤：
     * 1. 指针 pA 从 headA 开始，指针 pB 从 headB 开始
     * 2. pA 走到末尾后，从 headB 开始
     * 3. pB 走到末尾后，从 headA 开始
     * 4. 如果有相交，两指针一定会在相交点相遇
     * 5. 如果不相交，两指针最终都会变成 null
     * 
     * 为什么会相遇？
     * - 设链表 A 独有部分长度为 a，链表 B 独有部分长度为 b，公共部分长度为 c
     * - pA 走的路径：a + c + b
     * - pB 走的路径：b + c + a
     * - 路径长度相同，所以会在相交点相遇
     * 
     * 浪漫解读：
     * 你走过我走过的路，我走过你走过的路，
     * 我们终会在某处相遇 ❤️
     * 
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1) - 只使用了两个指针
     * 
     * @param headA 链表 A 的头节点
     * @param headB 链表 B 的头节点
     * @return 相交节点，如果不相交则返回 null
     */
    public ListNode getIntersectionNodeOptimal(ListNode headA, ListNode headB) {
        // 边界情况：如果任一链表为空，不可能相交
        if (headA == null || headB == null) {
            return null;
        }
        
        // 1. 初始化两个指针
        ListNode pA = headA;
        ListNode pB = headB;
        
        // 2. 两个指针同时移动
        // 关键：当指针到达末尾时，切换到另一个链表的头部
        while (pA != pB) {
            // pA 走完 A 后，从 B 的头开始
            // 如果 pA 为 null，说明走完了一轮，切换到 headB
            pA = (pA == null) ? headB : pA.next;
            
            // pB 走完 B 后，从 A 的头开始
            // 如果 pB 为 null，说明走完了一轮，切换到 headA
            pB = (pB == null) ? headA : pB.next;
        }
        
        // 3. 返回相交节点（如果不相交，最终 pA == pB == null）
        return pA;
    }
}

