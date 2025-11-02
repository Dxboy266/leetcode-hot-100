package com.leetcode.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试用例 - 322. 零钱兑换
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class CoinChangeTest {

    private final CoinChange solution = new CoinChange();

    @Test
    public void testExample1() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        assertEquals(3, solution.coinChange(coins, amount)); // 5 + 5 + 1
        assertEquals(3, solution.coinChangeMemo(coins, amount));
//        assertEquals(3, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testExample2() {
        int[] coins = {2};
        int amount = 3;
        assertEquals(-1, solution.coinChange(coins, amount)); // 无法凑成
        assertEquals(-1, solution.coinChangeMemo(coins, amount));
        assertEquals(-1, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testExample3() {
        int[] coins = {1};
        int amount = 0;
        assertEquals(0, solution.coinChange(coins, amount));
        assertEquals(0, solution.coinChangeMemo(coins, amount));
        assertEquals(0, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testSingleCoin() {
        int[] coins = {1};
        int amount = 5;
        assertEquals(5, solution.coinChange(coins, amount));
        assertEquals(5, solution.coinChangeMemo(coins, amount));
        assertEquals(5, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testMultipleCoins() {
        int[] coins = {1, 3, 4};
        int amount = 6;
        assertEquals(2, solution.coinChange(coins, amount)); // 3 + 3
        assertEquals(2, solution.coinChangeMemo(coins, amount));
        assertEquals(2, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testLargeAmount() {
        int[] coins = {1, 5, 10, 25};
        int amount = 100;
        assertEquals(4, solution.coinChange(coins, amount)); // 25 * 4
        assertEquals(4, solution.coinChangeMemo(coins, amount));
        assertEquals(4, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testImpossible() {
        int[] coins = {3, 5};
        int amount = 1;
        assertEquals(-1, solution.coinChange(coins, amount));
        assertEquals(-1, solution.coinChangeMemo(coins, amount));
        assertEquals(-1, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testGreedyFails() {
        // 贪心策略会失败的案例
        int[] coins = {1, 3, 4};
        int amount = 6;
        // 贪心会选 4 + 1 + 1 = 3 枚，但最优是 3 + 3 = 2 枚
        assertEquals(2, solution.coinChange(coins, amount));
        assertEquals(2, solution.coinChangeMemo(coins, amount));
        assertEquals(2, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testLargeCoins() {
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;
        assertEquals(20, solution.coinChange(coins, amount));
        assertEquals(20, solution.coinChangeMemo(coins, amount));
        assertEquals(20, solution.coinChangeBFS(coins, amount));
    }

    @Test
    public void testAmountOne() {
        int[] coins = {1, 2, 5};
        int amount = 1;
        assertEquals(1, solution.coinChange(coins, amount));
        assertEquals(1, solution.coinChangeMemo(coins, amount));
        assertEquals(1, solution.coinChangeBFS(coins, amount));
    }
}

