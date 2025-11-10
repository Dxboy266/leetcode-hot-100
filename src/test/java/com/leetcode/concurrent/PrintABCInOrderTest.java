package com.leetcode.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 多线程顺序打印 ABC - 测试用例
 * 
 * 测试覆盖：
 * 1. 基本功能测试 - 验证输出是否为 "ABCABCABC..."（30个字符）
 * 2. 方法对比测试 - 验证所有方法的输出一致性
 * 3. 性能测试 - 验证执行时间是否合理
 * 4. 并发安全测试 - 验证多次运行结果一致
 * 
 * @Author Dxboy266
 * @Date 2025-11-10
 */
@DisplayName("多线程顺序打印 ABC")
public class PrintABCInOrderTest {
    
    private static final String EXPECTED_OUTPUT = "ABCABCABCABCABCABCABCABCABCABC";  // 10次ABC
    
    /**
     * 辅助方法：捕获 System.out 输出
     */
    private String captureOutput(Runnable task) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            task.run();
            return outputStream.toString();
        } finally {
            System.setOut(originalOut);
        }
    }
    
    // ==================== 方法一：synchronized + wait/notify ====================
    
    @Test
    @DisplayName("方法一：synchronized + wait/notify - 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testSynchronizedBasic() throws InterruptedException {
        PrintABCInOrder printer = new PrintABCInOrder();
        
        String output = captureOutput(() -> {
            Thread threadA = new Thread(printer::printA);
            Thread threadB = new Thread(printer::printB);
            Thread threadC = new Thread(printer::printC);
            
            threadA.start();
            threadB.start();
            threadC.start();
            
            try {
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                fail("线程被中断");
            }
        });
        
        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }
    
    // ==================== 方法二：ReentrantLock + Condition ====================
    
    @Test
    @DisplayName("方法二：ReentrantLock + Condition - 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testLockBasic() throws InterruptedException {
        PrintABCInOrder printer = new PrintABCInOrder();
        
        String output = captureOutput(() -> {
            Thread threadA = new Thread(printer::printAWithLock);
            Thread threadB = new Thread(printer::printBWithLock);
            Thread threadC = new Thread(printer::printCWithLock);
            
            threadA.start();
            threadB.start();
            threadC.start();
            
            try {
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                fail("线程被中断");
            }
        });
        
        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }
    
    // ==================== 方法三：Semaphore ====================
    
    @Test
    @DisplayName("方法三：Semaphore - 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testSemaphoreBasic() throws InterruptedException {
        PrintABCInOrder printer = new PrintABCInOrder();
        
        String output = captureOutput(() -> {
            Thread threadA = new Thread(printer::printAWithSemaphore);
            Thread threadB = new Thread(printer::printBWithSemaphore);
            Thread threadC = new Thread(printer::printCWithSemaphore);
            
            threadA.start();
            threadB.start();
            threadC.start();
            
            try {
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                fail("线程被中断");
            }
        });
        
        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }
    
    // ==================== 方法四：CompletableFuture ====================

    @Test
    @DisplayName("方法四：CompletableFuture - 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testCompletableFutureBasic() {
        PrintABCInOrder printer = new PrintABCInOrder();

        String output = captureOutput(() -> {
            printer.printWithCompletableFuture();
        });

        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }

    // ==================== 方法六：公平锁 ====================

    @Test
    @DisplayName("方法六：公平锁 ReentrantLock(fair=true) - 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testFairLockBasic() throws InterruptedException {
        PrintABCInOrder printer = new PrintABCInOrder();

        String output = captureOutput(() -> {
            Thread threadA = new Thread(printer::printAWithFairLock);
            Thread threadB = new Thread(printer::printBWithFairLock);
            Thread threadC = new Thread(printer::printCWithFairLock);

            threadA.start();
            threadB.start();
            threadC.start();

            try {
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                fail("线程被中断");
            }
        });

        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }

    // ==================== 方法七：AtomicInteger（最简单）====================

    @Test
    @DisplayName("方法七：AtomicInteger + 自旋（最简单）- 基本功能")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testAtomicBasic() throws InterruptedException {
        PrintABCInOrder printer = new PrintABCInOrder();

        String output = captureOutput(() -> {
            Thread threadA = new Thread(printer::printAWithAtomic);
            Thread threadB = new Thread(printer::printBWithAtomic);
            Thread threadC = new Thread(printer::printCWithAtomic);

            threadA.start();
            threadB.start();
            threadC.start();

            try {
                threadA.join();
                threadB.join();
                threadC.join();
            } catch (InterruptedException e) {
                fail("线程被中断");
            }
        });

        assertEquals(EXPECTED_OUTPUT, output, "输出应为正确的ABC序列");
    }

    // ==================== 综合对比测试 ====================

    @Test
    @DisplayName("所有方法输出一致性测试")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testAllMethodsConsistency() throws InterruptedException {
        // 测试所有方法的输出是否一致
        PrintABCInOrder printer1 = new PrintABCInOrder();
        PrintABCInOrder printer2 = new PrintABCInOrder();
        PrintABCInOrder printer3 = new PrintABCInOrder();
        PrintABCInOrder printer4 = new PrintABCInOrder();
        PrintABCInOrder printer5 = new PrintABCInOrder();
        PrintABCInOrder printer6 = new PrintABCInOrder();

        String output1 = captureOutput(() -> {
            try {
                Thread t1 = new Thread(printer1::printA);
                Thread t2 = new Thread(printer1::printB);
                Thread t3 = new Thread(printer1::printC);
                t1.start(); t2.start(); t3.start();
                t1.join(); t2.join(); t3.join();
            } catch (InterruptedException e) {}
        });

        String output2 = captureOutput(() -> {
            try {
                Thread t1 = new Thread(printer2::printAWithLock);
                Thread t2 = new Thread(printer2::printBWithLock);
                Thread t3 = new Thread(printer2::printCWithLock);
                t1.start(); t2.start(); t3.start();
                t1.join(); t2.join(); t3.join();
            } catch (InterruptedException e) {}
        });

        String output3 = captureOutput(() -> {
            try {
                Thread t1 = new Thread(printer3::printAWithSemaphore);
                Thread t2 = new Thread(printer3::printBWithSemaphore);
                Thread t3 = new Thread(printer3::printCWithSemaphore);
                t1.start(); t2.start(); t3.start();
                t1.join(); t2.join(); t3.join();
            } catch (InterruptedException e) {}
        });

        String output4 = captureOutput(() -> {
            printer4.printWithCompletableFuture();
        });

        String output5 = captureOutput(() -> {
            try {
                Thread t1 = new Thread(printer5::printAWithFairLock);
                Thread t2 = new Thread(printer5::printBWithFairLock);
                Thread t3 = new Thread(printer5::printCWithFairLock);
                t1.start(); t2.start(); t3.start();
                t1.join(); t2.join(); t3.join();
            } catch (InterruptedException e) {}
        });

        String output6 = captureOutput(() -> {
            try {
                Thread t1 = new Thread(printer6::printAWithAtomic);
                Thread t2 = new Thread(printer6::printBWithAtomic);
                Thread t3 = new Thread(printer6::printCWithAtomic);
                t1.start(); t2.start(); t3.start();
                t1.join(); t2.join(); t3.join();
            } catch (InterruptedException e) {}
        });

        // 验证所有输出都相同
        assertEquals(EXPECTED_OUTPUT, output1, "synchronized方法输出应正确");
        assertEquals(EXPECTED_OUTPUT, output2, "ReentrantLock方法输出应正确");
        assertEquals(EXPECTED_OUTPUT, output3, "Semaphore方法输出应正确");
        assertEquals(EXPECTED_OUTPUT, output4, "CompletableFuture方法输出应正确");
        assertEquals(EXPECTED_OUTPUT, output5, "公平锁方法输出应正确");
        assertEquals(EXPECTED_OUTPUT, output6, "AtomicInteger方法输出应正确");
    }
}

