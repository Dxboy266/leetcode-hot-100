package com.leetcode.utils;

import java.util.Stack;

/**
 * 链表节点定义
 * 用于链表相关的LeetCode题目
 */
public class ListNode {
    public int val;
    public ListNode next;
    
    public ListNode() {}
    
    public ListNode(int val) {
        this.val = val;
    }
    
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    
    /**
     * 工具方法：从数组创建链表
     */
    public static ListNode fromArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    
    /**
     * 工具方法：将链表转换为数组
     */
    public static int[] toArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        // 计算长度
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        // 转换为数组
        int[] arr = new int[length];
        curr = head;
        for (int i = 0; i < length; i++) {
            arr[i] = curr.val;
            curr = curr.next;
        }
        return arr;
    }

    public static Stack<Integer> toStack(ListNode head) {
        if (head == null) {
            return new Stack<>();
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        return stack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode curr = this;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

