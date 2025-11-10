package com.leetcode.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * 多线程顺序打印 ABC
 *
 * 题目描述：
 * 设计并实现一个多线程程序，要求使用三个线程，分别打印字母 "A"、"B" 和 "C"。
 * 这三个线程需要按照严格的顺序循环打印，即依次打印 "A"、"B"、"C"，然后再从 "A" 开始循环，
 * 直到整个序列打印指定的次数。
 *
 * 具体要求：
 * 1. 程序启动后，启动三个线程，其中：
 *    - 线程1 负责打印字符 "A"
 *    - 线程2 负责打印字符 "B"
 *    - 线程3 负责打印字符 "C"
 * 2. 每个线程在其对应的输出位置打印一个字符
 * 3. 三个线程应协调工作，确保打印顺序严格为 "A" -> "B" -> "C" -> "A" -> "B" -> "C" -> …
 * 4. 输出总次数为 30 次字符，也就是循环打印 10 次完整的 "ABC" 序列
 *
 * 示例输出：
 * ABCABCABCABCABCABCABCABCABCABC
 *
 * 核心考点：
 * - 线程同步与协调
 * - 多线程顺序控制
 * - 线程间通信机制
 *
 * 常见解法：
 * 1. synchronized + wait/notify
 * 2. ReentrantLock + Condition
 * 3. Semaphore 信号量
 * 4. CompletableFuture 链式调用
 * 5. CyclicBarrier 循环栅栏
 *
 * @Author Dxboy266
 * @Date 2025-11-10
 */
public class PrintABCInOrder {

    /**
     * 方法一：synchronized + wait/notify（经典方法）⭐⭐⭐
     *
     * 核心思想：
     * 1. 使用一个共享的 flag 变量控制当前应该打印哪个字母
     * 2. 每个线程在打印前检查 flag 是否等于自己的编号
     * 3. 如果不是，调用 wait() 等待
     * 4. 打印完成后，更新 flag 并调用 notifyAll() 唤醒其他线程
     *
     * 优点：简单直观，JDK 内置支持
     * 缺点：notifyAll() 会唤醒所有线程，效率较低
     *
     * 优化：使用通用的 print(char c) 方法，避免代码重复 ⭐⭐⭐⭐⭐
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private int flag = 1;  // 1-A, 2-B, 3-C
    private final int PRINT_COUNT = 10;  // 每个字母打印10次

    /**
     * 通用打印方法（推荐）⭐⭐⭐⭐⭐
     *
     * 优点：
     * - 代码复用，避免重复
     * - 更加优雅和简洁
     * - 易于扩展（如果要打印更多字母）
     *
     * @param c 要打印的字符（'A', 'B', 'C'）
     */
    public synchronized void print(char c) {
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 计算当前字符对应的 flag 值：'A'->1, 'B'->2, 'C'->3
            int targetFlag = c - 'A' + 1;

            while (flag != targetFlag) {
                try {
                    wait();  // 不是当前字符的回合，等待
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.print(c);
            flag = (flag % 3) + 1;  // 切换到下一个：1->2, 2->3, 3->1
            notifyAll();  // 唤醒所有等待的线程
        }
    }

    // 为了兼容旧的测试代码，保留这三个方法
    public void printA() {
        print('A');
    }

    public void printB() {
        print('B');
    }

    public void printC() {
        print('C');
    }

    /**
     * 方法二：ReentrantLock + Condition（推荐）⭐⭐⭐⭐
     *
     * 核心思想：
     * 1. 使用 ReentrantLock 替代 synchronized
     * 2. 为每个线程创建一个 Condition，实现精准唤醒
     * 3. 避免 notifyAll() 的性能损耗
     *
     * 优点：精准唤醒，性能更好
     * 缺点：代码稍复杂
     */
    private final Lock lock = new ReentrantLock();
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();
    private int state = 1;  // 1-A, 2-B, 3-C

    public void printAWithLock() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            lock.lock();
            try {
                while (state != 1) {
                    conditionA.await();  // A等待
                }
                System.out.print("A");
                state = 2;
                conditionB.signal();  // 精准唤醒B
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printBWithLock() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            lock.lock();
            try {
                while (state != 2) {
                    conditionB.await();  // B等待
                }
                System.out.print("B");
                state = 3;
                conditionC.signal();  // 精准唤醒C
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printCWithLock() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            lock.lock();
            try {
                while (state != 3) {
                    conditionC.await();  // C等待
                }
                System.out.print("C");
                state = 1;
                conditionA.signal();  // 精准唤醒A
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 方法三：Semaphore 信号量（巧妙方法）⭐⭐⭐
     *
     * 核心思想：
     * 1. 为每个线程创建一个信号量
     * 2. A的信号量初始为1（可以执行），B和C的信号量初始为0（不能执行）
     * 3. 每个线程打印完后，释放下一个线程的信号量
     *
     * 优点：代码简洁，思路清晰
     * 缺点：需要创建多个信号量对象
     */
    private final Semaphore semaphoreA = new Semaphore(1);  // A初始可执行
    private final Semaphore semaphoreB = new Semaphore(0);  // B初始不可执行
    private final Semaphore semaphoreC = new Semaphore(0);  // C初始不可执行

    public void printAWithSemaphore() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                semaphoreA.acquire();  // 获取A的许可
                System.out.print("A");
                semaphoreB.release();  // 释放B的许可
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printBWithSemaphore() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                semaphoreB.acquire();  // 获取B的许可
                System.out.print("B");
                semaphoreC.release();  // 释放C的许可
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printCWithSemaphore() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                semaphoreC.acquire();  // 获取C的许可
                System.out.print("C");
                semaphoreA.release();  // 释放A的许可（循环）
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 方法四：CompletableFuture 链式调用（现代方法）⭐⭐⭐⭐
     *
     * 核心思想：
     * 1. 使用 CompletableFuture 的链式调用实现顺序执行
     * 2. thenRun() 确保顺序执行
     * 3. 通过循环构建调用链
     *
     * 优点：代码优雅，符合函数式编程风格
     * 缺点：理解成本较高，不适合初学者
     */
    public void printWithCompletableFuture() {
        // 创建一个已完成的 Future 作为起点
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);

        // 构建 ABC 循环调用链
        for (int i = 0; i < PRINT_COUNT; i++) {
            future = future
                .thenRun(() -> System.out.print("A"))
                .thenRun(() -> System.out.print("B"))
                .thenRun(() -> System.out.print("C"));
        }

        // 等待所有任务完成
        future.join();
    }

    /**
     * 方法五：CyclicBarrier 循环栅栏（适合多轮同步）⭐⭐
     *
     * 核心思想：
     * 1. 使用 CyclicBarrier 让三个线程在每轮打印后同步
     * 2. 通过额外的标志位控制打印顺序
     *
     * 优点：适合需要多轮同步的场景
     * 缺点：对于简单的顺序打印，有些过度设计
     */
    private volatile int turn = 0;  // 0-A, 1-B, 2-C
    private final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        // 每轮完成后重置 turn
        turn = 0;
    });

    public void printAWithBarrier() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                while (turn != 0) {
                    Thread.yield();  // 让出CPU
                }
                System.out.print("A");
                turn = 1;
                barrier.await();  // 等待其他线程
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printBWithBarrier() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                while (turn != 1) {
                    Thread.yield();
                }
                System.out.print("B");
                turn = 2;
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printCWithBarrier() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            try {
                while (turn != 2) {
                    Thread.yield();
                }
                System.out.print("C");
                turn = 0;  // 重置为A
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 测试方法：synchronized + wait/notify
     */
    public static void testSynchronized() throws InterruptedException {
        System.out.println("=== 方法一：synchronized + wait/notify ===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printA, "Thread-A");
        Thread threadB = new Thread(printer::printB, "Thread-B");
        Thread threadC = new Thread(printer::printC, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 测试方法：ReentrantLock + Condition
     */
    public static void testLock() throws InterruptedException {
        System.out.println("\n=== 方法二：ReentrantLock + Condition ===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printAWithLock, "Thread-A");
        Thread threadB = new Thread(printer::printBWithLock, "Thread-B");
        Thread threadC = new Thread(printer::printCWithLock, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 测试方法：Semaphore
     */
    public static void testSemaphore() throws InterruptedException {
        System.out.println("\n=== 方法三：Semaphore 信号量 ===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printAWithSemaphore, "Thread-A");
        Thread threadB = new Thread(printer::printBWithSemaphore, "Thread-B");
        Thread threadC = new Thread(printer::printCWithSemaphore, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 测试方法：CompletableFuture
     */
    public static void testCompletableFuture() {
        System.out.println("\n=== 方法四：CompletableFuture 链式调用 ===");
        PrintABCInOrder printer = new PrintABCInOrder();
        printer.printWithCompletableFuture();
        System.out.println("\n完成！");
    }

    /**
     * 测试方法：CyclicBarrier
     */
    public static void testBarrier() throws InterruptedException {
        System.out.println("\n=== 方法五：CyclicBarrier 循环栅栏 ===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printAWithBarrier, "Thread-A");
        Thread threadB = new Thread(printer::printBWithBarrier, "Thread-B");
        Thread threadC = new Thread(printer::printCWithBarrier, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 方法六：公平锁 ReentrantLock(fair=true) ⭐⭐⭐⭐⭐
     *
     * 核心思想：
     * 1. 使用公平锁（fair=true）保证线程按照请求锁的顺序获取锁
     * 2. 只用一个 Condition，配合 signalAll() 唤醒所有等待线程
     * 3. 公平锁可以避免线程饥饿问题
     *
     * 优点：
     * - 公平性：线程按照请求顺序获取锁，避免饥饿
     * - 代码简洁：只需一个 Condition
     * - 可预测性：执行顺序更加可预测
     *
     * 缺点：
     * - 性能略低于非公平锁（需要维护等待队列）
     * - signalAll() 会唤醒所有线程（但公平锁保证按顺序获取）
     *
     * 适用场景：
     * - 需要严格保证顺序的场景
     * - 避免线程饥饿的场景
     *
     * ⚠️ 注意：
     * - 如果省略 Condition，只用 fairLock，无法实现等待机制
     * - 必须至少有一个 Condition 来实现 await/signal
     * - 可以用一个 Condition + signalAll()，而不是三个 Condition
     */
    private final Lock fairLock = new ReentrantLock(true);  // 公平锁
    private final Condition fairCondition = fairLock.newCondition();  // 只用一个 Condition
    private int fairState = 1;  // 1-A, 2-B, 3-C

    /**
     * 公平锁通用打印方法（推荐）⭐⭐⭐⭐⭐
     *
     * @param c 要打印的字符（'A', 'B', 'C'）
     */
    public void printWithFairLock(char c) {
        for (int i = 0; i < PRINT_COUNT; i++) {
            fairLock.lock();
            try {
                int targetState = c - 'A' + 1;
                while (fairState != targetState) {
                    fairCondition.await();  // 等待
                }
                System.out.print(c);
                fairState = (fairState % 3) + 1;
                fairCondition.signalAll();  // 唤醒所有等待的线程
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                fairLock.unlock();
            }
        }
    }

    // 为了兼容旧的测试代码，保留这三个方法
    public void printAWithFairLock() {
        printWithFairLock('A');
    }

    public void printBWithFairLock() {
        printWithFairLock('B');
    }

    public void printCWithFairLock() {
        printWithFairLock('C');
    }

    /**
     * 方法七：AtomicInteger + 自旋（最简单的方法）⭐⭐⭐⭐⭐
     *
     * 核心思想：
     * 1. 使用 AtomicInteger 作为状态标志
     * 2. 每个线程自旋等待轮到自己
     * 3. 打印后更新状态
     *
     * 优点：
     * - 代码最简单，易于理解
     * - 无需显式加锁
     * - 无需 wait/notify 机制
     *
     * 缺点：
     * - 自旋会消耗 CPU 资源
     * - 不适合等待时间长的场景
     *
     * 适用场景：
     * - 等待时间短的场景
     * - 对代码简洁性要求高的场景
     */
    private final java.util.concurrent.atomic.AtomicInteger atomicState =
        new java.util.concurrent.atomic.AtomicInteger(1);

    public void printAWithAtomic() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 自旋等待轮到A
            while (atomicState.get() != 1) {
                Thread.yield();  // 让出CPU，避免空转
            }
            System.out.print("A");
            atomicState.set(2);  // 切换到B
        }
    }

    public void printBWithAtomic() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 自旋等待轮到B
            while (atomicState.get() != 2) {
                Thread.yield();
            }
            System.out.print("B");
            atomicState.set(3);  // 切换到C
        }
    }

    public void printCWithAtomic() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 自旋等待轮到C
            while (atomicState.get() != 3) {
                Thread.yield();
            }
            System.out.print("C");
            atomicState.set(1);  // 切换回A
        }
    }

    // ==================== 测试方法 ====================

    /**
     * 测试方法六：公平锁
     */
    public static void testFairLock() throws InterruptedException {
        System.out.println("=== 方法六：公平锁 ReentrantLock(fair=true) ===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printAWithFairLock, "Thread-A");
        Thread threadB = new Thread(printer::printBWithFairLock, "Thread-B");
        Thread threadC = new Thread(printer::printCWithFairLock, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 测试方法七：AtomicInteger（最简单）
     */
    public static void testAtomic() throws InterruptedException {
        System.out.println("=== 方法七：AtomicInteger + 自旋（最简单）===");
        PrintABCInOrder printer = new PrintABCInOrder();

        Thread threadA = new Thread(printer::printAWithAtomic, "Thread-A");
        Thread threadB = new Thread(printer::printBWithAtomic, "Thread-B");
        Thread threadC = new Thread(printer::printCWithAtomic, "Thread-C");

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("\n完成！");
    }

    /**
     * 主方法：运行所有测试
     */
    public static void main(String[] args) throws InterruptedException {
//        testSynchronized();
//        testLock();
//        testSemaphore();
//        testCompletableFuture();
//        testBarrier();
//        testFairLock();
        testAtomic();  // 最简单的方法
    }
}
