package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * 
 * 题目链接：https://leetcode.cn/problems/lru-cache/
 * 难度：中等
 * 标签：设计、哈希表、链表、双向链表
 * 
 * ==================== 题目描述 ====================
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 
 * 实现 LRUCache 类：
 * - LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
 * - void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value；
 *   如果不存在，则向缓存中插入该组 key-value。
 *   如果插入操作导致关键字数量超过 capacity，则应该逐出最久未使用的关键字。
 * 
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 
 * 示例 1：
 * 输入：
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * 解释：
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * 
 * 示例 2：
 * 输入：
 * ["LRUCache", "put", "get"]
 * [[1], [2, 1], [2]]
 * 输出：
 * [null, null, 1]
 * 
 * 解释：
 * LRUCache lRUCache = new LRUCache(1);
 * lRUCache.put(2, 1); // 缓存是 {2=1}
 * lRUCache.get(2);    // 返回 1
 * 
 * 提示：
 * - 1 <= capacity <= 3000
 * - 0 <= key <= 10000
 * - 0 <= value <= 10^5
 * - 最多调用 2 * 10^5 次 get 和 put
 * 
 * ==================== 解题思路 ====================
 * 
 * 【核心数据结构：哈希表 + 双向链表】⭐⭐⭐
 * 
 * LRU 缓存的核心需求：
 * 1. get(key) 和 put(key, value) 都要 O(1) 时间复杂度
 * 2. 需要记录访问顺序，淘汰最久未使用的元素
 * 
 * 解决方案：
 * - **哈希表**：实现 O(1) 的查找
 * - **双向链表**：实现 O(1) 的插入和删除，维护访问顺序
 * 
 * 链表顺序约定：
 * - 头部（head.next）：最近使用的元素
 * - 尾部（tail.prev）：最久未使用的元素
 * 
 * 操作逻辑：
 * 1. **get(key)**：
 *    - 如果 key 存在，将节点移到链表头部（标记为最近使用）
 *    - 返回节点的值
 * 
 * 2. **put(key, value)**：
 *    - 如果 key 已存在，更新值并移到头部
 *    - 如果 key 不存在：
 *      a. 创建新节点，插入到头部
 *      b. 如果超过容量，删除尾部节点（最久未使用）
 * 
 * 图解（容量为 2）：
 * 
 * 初始状态：
 *   head ⇄ tail
 * 
 * put(1, 1)：
 *   head ⇄ [1:1] ⇄ tail
 * 
 * put(2, 2)：
 *   head ⇄ [2:2] ⇄ [1:1] ⇄ tail
 * 
 * get(1)：（访问1，移到头部）
 *   head ⇄ [1:1] ⇄ [2:2] ⇄ tail
 * 
 * put(3, 3)：（容量满，删除尾部的2，插入3到头部）
 *   head ⇄ [3:3] ⇄ [1:1] ⇄ tail
 * 
 * 时间复杂度：O(1) - get 和 put 操作
 * 空间复杂度：O(capacity) - 哈希表和链表的空间
 * 
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class LRUCache {
    
    /**
     * 双向链表节点
     */
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node() {}
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // 哈希表：key -> Node，实现 O(1) 查找
    private Map<Integer, Node> map;
    
    // 双向链表的虚拟头尾节点
    private Node head;
    private Node tail;
    
    // 缓存容量
    private int capacity;
    
    // 当前缓存大小
    private int size;

    /**
     * 初始化 LRU 缓存
     * 
     * @param capacity 缓存容量
     */
    public LRUCache(int capacity) {
        // 初始化成员变量
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>(capacity);
        
        // 创建虚拟头尾节点
        this.head = new Node();
        this.tail = new Node();
        
        // 连接头尾节点
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * 获取缓存中 key 对应的值
     * 
     * @param key 键
     * @return 如果存在返回值，否则返回 -1
     */
    public int get(int key) {
        Node node = map.get(key);
        
        // 如果 key 不存在，返回 -1
        if (node == null) {
            return -1;
        }
        
        // 将访问的节点移到头部（标记为最近使用）
        moveToHead(node);
        return node.value;
    }
    
    /**
     * 向缓存中添加或更新 key-value
     * 
     * @param key 键
     * @param value 值
     */
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if (node != null) {
            // key 已存在，更新值并移到头部
            node.value = value;
            moveToHead(node);
        } else {
            // key 不存在，创建新节点
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            size++;
            
            // 如果超过容量，删除尾部节点（最久未使用）
            if (size > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
                size--;
            }
        }
    }
    
    /**
     * 将节点添加到链表头部
     * 
     * @param node 要添加的节点
     */
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * 从链表中删除节点
     * 
     * @param node 要删除的节点
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * 将节点移到链表头部
     * 
     * @param node 要移动的节点
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    /**
     * 删除链表尾部节点
     * 
     * @return 被删除的节点
     */
    private Node removeTail() {
        Node removed = tail.prev;
        removeNode(removed);
        return removed;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */