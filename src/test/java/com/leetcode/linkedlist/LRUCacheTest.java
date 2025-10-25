package com.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LRUCache 测试类
 *
 * @Author Dxboy266
 * @Date 2025-10-24
 */
public class LRUCacheTest {

    @Test
    void testExample1() {
        // 示例 1：容量为 2 的缓存
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);        // 缓存是 {1=1}
        cache.put(2, 2);        // 缓存是 {1=1, 2=2}
        assertEquals(1, cache.get(1));  // 返回 1
        
        cache.put(3, 3);        // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        assertEquals(-1, cache.get(2)); // 返回 -1 (未找到)
        
        cache.put(4, 4);        // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        assertEquals(-1, cache.get(1)); // 返回 -1 (未找到)
        assertEquals(3, cache.get(3));  // 返回 3
        assertEquals(4, cache.get(4));  // 返回 4
    }

    @Test
    void testExample2() {
        // 示例 2：容量为 1 的缓存
        LRUCache cache = new LRUCache(1);
        
        cache.put(2, 1);        // 缓存是 {2=1}
        assertEquals(1, cache.get(2));  // 返回 1
    }

    @Test
    void testSingleCapacity() {
        // 测试容量为 1 的情况
        LRUCache cache = new LRUCache(1);
        
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        
        cache.put(2, 2);        // 替换 key=1
        assertEquals(-1, cache.get(1)); // key=1 已被淘汰
        assertEquals(2, cache.get(2));
    }

    @Test
    void testUpdateExistingKey() {
        // 测试更新已存在的 key
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);       // 更新 key=1 的值
        
        assertEquals(10, cache.get(1)); // 返回更新后的值
        assertEquals(2, cache.get(2));
    }

    @Test
    void testGetNonExistentKey() {
        // 测试获取不存在的 key
        LRUCache cache = new LRUCache(2);
        
        assertEquals(-1, cache.get(1)); // 空缓存
        
        cache.put(1, 1);
        assertEquals(-1, cache.get(2)); // key=2 不存在
    }

    @Test
    void testLRUEviction() {
        // 测试 LRU 淘汰策略
        LRUCache cache = new LRUCache(3);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        
        cache.get(1);           // 访问 key=1，使其成为最近使用
        cache.put(4, 4);        // 应该淘汰 key=2（最久未使用）
        
        assertEquals(-1, cache.get(2)); // key=2 已被淘汰
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void testAccessOrder() {
        // 测试访问顺序影响淘汰
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);           // 访问 key=1
        cache.put(3, 3);        // 应该淘汰 key=2
        
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    void testPutUpdateMovesToHead() {
        // 测试 put 更新操作会将节点移到头部
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);       // 更新 key=1，应该移到头部
        cache.put(3, 3);        // 应该淘汰 key=2
        
        assertEquals(-1, cache.get(2));
        assertEquals(10, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    void testLargeCapacity() {
        // 测试较大容量
        LRUCache cache = new LRUCache(5);
        
        for (int i = 1; i <= 5; i++) {
            cache.put(i, i * 10);
        }
        
        // 所有元素都应该在缓存中
        for (int i = 1; i <= 5; i++) {
            assertEquals(i * 10, cache.get(i));
        }
        
        // 添加第 6 个元素，应该淘汰 key=1
        cache.put(6, 60);
        assertEquals(-1, cache.get(1));
        assertEquals(60, cache.get(6));
    }

    @Test
    void testSequentialAccess() {
        // 测试顺序访问
        LRUCache cache = new LRUCache(3);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        
        // 按顺序访问
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
        
        // 添加新元素，应该淘汰最久未访问的（但由于刚才都访问过，淘汰最早访问的 key=1）
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
    }

    @Test
    void testZeroValues() {
        // 测试值为 0 的情况
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 0);
        cache.put(2, 0);
        
        assertEquals(0, cache.get(1));
        assertEquals(0, cache.get(2));
    }

    @Test
    void testComplexScenario() {
        // 复杂场景测试
        LRUCache cache = new LRUCache(2);
        
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);        // 更新 key=2
        cache.put(4, 1);        // 淘汰 key=1
        
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(2));
    }

    @Test
    void testGetAfterMultiplePuts() {
        // 测试多次 put 后的 get
        LRUCache cache = new LRUCache(2);
        
        cache.put(2, 1);
        cache.put(2, 2);        // 更新
        assertEquals(2, cache.get(2));
        
        cache.put(1, 1);
        cache.put(4, 1);        // 淘汰 key=2
        assertEquals(-1, cache.get(2));
    }

    @Test
    void testAlternatingOperations() {
        // 交替进行 get 和 put 操作
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        
        cache.put(3, 3);        // 淘汰最久未使用的
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void testSameKeyMultipleTimes() {
        // 测试对同一个 key 的多次操作
        LRUCache cache = new LRUCache(1);
        
        cache.put(1, 1);
        cache.put(1, 2);
        cache.put(1, 3);
        assertEquals(3, cache.get(1));
    }
}

