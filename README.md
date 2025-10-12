# LeetCode Hot 100 刷题记录 (Java)

[![LeetCode](https://img.shields.io/badge/LeetCode-Hot%20100-orange.svg)](https://leetcode.cn/studyplan/top-100-liked/)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com/)
[![Progress](https://img.shields.io/badge/进度-0%2F100-brightgreen.svg)](https://github.com/yourusername/leetcode-hot-100)

## 📖 项目介绍

本仓库记录 LeetCode Hot 100 的 Java 解题代码，每道题的解题思路、复杂度分析和知识点总结都以注释形式写在代码中。

**特点：**
- ☕ 纯 Java 实现
- 📝 详细的代码注释和解题思路
- 🎯 标准的 Maven 项目结构
- ✅ 包含单元测试

## 🎯 刷题进度

| 分类 | 已完成 | 总数 |
|------|--------|------|
| 数组 | 0 | 15 |
| 链表 | 0 | 8 |
| 哈希表 | 0 | 6 |
| 字符串 | 0 | 8 |
| 双指针 | 0 | 7 |
| 滑动窗口 | 0 | 4 |
| 栈 | 0 | 5 |
| 堆 | 0 | 4 |
| 贪心 | 0 | 6 |
| 动态规划 | 0 | 18 |
| 回溯 | 0 | 6 |
| 二分查找 | 0 | 5 |
| 树 | 0 | 12 |
| 图 | 0 | 6 |

**总进度**: 0 / 100 ✨

## 📂 项目结构

```
leetcode-hot-100/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── leetcode/
│   │               ├── array/              # 数组相关题目
│   │               │   ├── TwoSum.java
│   │               │   └── ...
│   │               ├── linkedlist/         # 链表相关题目
│   │               ├── hashtable/          # 哈希表相关题目
│   │               ├── string/             # 字符串相关题目
│   │               ├── twopointers/        # 双指针相关题目
│   │               ├── slidingwindow/      # 滑动窗口相关题目
│   │               ├── stack/              # 栈相关题目
│   │               ├── heap/               # 堆相关题目
│   │               ├── greedy/             # 贪心算法相关题目
│   │               ├── dp/                 # 动态规划相关题目
│   │               ├── backtrack/          # 回溯相关题目
│   │               ├── binarysearch/       # 二分查找相关题目
│   │               ├── tree/               # 树相关题目
│   │               ├── graph/              # 图相关题目
│   │               └── utils/              # 工具类（链表节点、树节点等）
│   └── test/
│       └── java/
│           └── com/
│               └── leetcode/               # 对应的测试类
│                   ├── array/
│                   └── ...
├── pom.xml                                 # Maven配置文件
├── README.md                               # 项目说明
└── .gitignore
```

## 🚀 快速开始

### 1. 克隆仓库
```bash
git clone https://github.com/yourusername/leetcode-hot-100.git
cd leetcode-hot-100
```

### 2. 导入IDE
- **IDEA**: File -> Open -> 选择项目目录
- **Eclipse**: File -> Import -> Existing Maven Projects

### 3. 编译运行
```bash
# 编译
mvn clean compile

# 运行测试
mvn test

# 运行单个测试类
mvn test -Dtest=TwoSumTest
```

## 📝 代码规范

每个解题类都包含：

```java
package com.leetcode.array;

/**
 * 1. 两数之和
 * 
 * 题目链接：https://leetcode.cn/problems/two-sum/
 * 难度：简单
 * 标签：数组、哈希表
 * 
 * 题目描述：
 * [题目内容]
 * 
 * 解题思路：
 * 1. 思路一：暴力解法...
 * 2. 思路二：哈希表...
 * 
 * 知识点：
 * - 哈希表的应用
 * - 空间换时间
 * 
 * @author Your Name
 * @date 2025-10-12
 */
public class TwoSum {
    
    /**
     * 方法一：暴力解法
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        // 实现代码
    }
    
    /**
     * 方法二：哈希表（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        // 实现代码
    }
}
```

## 📊 题目列表

*开始你的刷题之旅吧！*

<!-- 
示例格式：
### 数组
- [ ] [1. 两数之和](src/main/java/com/leetcode/array/TwoSum.java) - 简单
- [ ] [53. 最大子数组和](src/main/java/com/leetcode/array/MaxSubArray.java) - 中等
-->

## 🛠️ 技术栈

- **JDK**: 8 或更高版本
- **构建工具**: Maven 3.6+
- **测试框架**: JUnit 5
- **IDE**: IntelliJ IDEA

## 📝 开发规范

- [代码贡献指南](CONTRIBUTING.md)
- [Git 提交规范](GIT_COMMIT_GUIDE.md) 

## 📚 参考资源

- [LeetCode 中国官网](https://leetcode.cn/)
- [LeetCode Hot 100](https://leetcode.cn/studyplan/top-100-liked/)
- [代码随想录](https://programmercarl.com/)

## 📮 说明

- 所有代码均可独立运行
- 每道题都包含多种解法对比
- 注释中包含详细的算法思路和复杂度分析
- 坚持每日一题，稳步提升

---

⭐ 如果对你有帮助，欢迎 Star！

**开始日期**: 2025-10-12  
**最后更新**: 2025-10-12
