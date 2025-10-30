# LeetCode Hot 100 刷题记录 (Java)

[![LeetCode](https://img.shields.io/badge/LeetCode-Hot%20100-orange.svg)](https://leetcode.cn/studyplan/top-100-liked/)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com/)
[![Progress](https://img.shields.io/badge/进度-27%2F100-brightgreen.svg)](https://github.com/yourusername/leetcode-hot-100)

## 📖 项目介绍

本仓库记录 LeetCode Hot 100 的 Java 解题代码，每道题的解题思路、复杂度分析和知识点总结都以注释形式写在代码中。

**特点：**
- ☕ 纯 Java 实现
- 📝 详细的代码注释和解题思路
- 🎯 标准的 Maven 项目结构
- ✅ 包含单元测试

## 🎯 刷题进度

| 分类 | 已完成 | 总数 |
|------|----|----|
| 数组 | 4  | 15 |
| 链表 | 11 | 14 | ⭐ 基础完成
| 哈希表 | 1  | 6  |
| 字符串 | 1  | 8  |
| 双指针 | 1  | 7  |
| 滑动窗口 | 0  | 4  |
| 栈 | 2  | 5  |
| 堆 | 0  | 4  |
| 贪心 | 0  | 6  |
| 动态规划 | 2  | 18 | 🧠 入门完成
| 回溯 | 0  | 6  |
| 二分查找 | 0  | 5  |
| 树 | 9  | 12 | 🌲 进阶完成
| 图 | 0  | 6  |

**总进度**: 27 / 100 ✨

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
│   │               │   ├── MoveZeroes.java
│   │               │   └── ...
│   │               ├── linkedlist/         # 链表相关题目
│   │               ├── hashtable/          # 哈希表相关题目
│   │               ├── string/             # 字符串相关题目
│   │               │   ├── GroupAnagrams.java
│   │               │   └── ...
│   │               ├── twopointers/        # 双指针相关题目
│   │               ├── stack/              # 栈相关题目
│   │               ├── dp/                 # 动态规划相关题目
│   │               ├── tree/               # 树相关题目
│   │               └── utils/              # 工具类（链表节点、树节点等）
│   └── test/
│       └── java/
│           └── com/
│               └── leetcode/               # 对应的测试类
│                   ├── array/
│                   └── ...
├── docs/                                   # 📝 刷题技巧总结文档
│   ├── 前缀和+哈希表模板.md
│   ├── 单调栈模板.md
│   ├── 链表处理模板.md
│   ├── 双指针技巧.md
│   ├── 哈希表分组技巧.md
│   └── ...
├── pom.xml                                 # Maven配置文件
├── README.md                               # 项目说明
└── .gitignore
```

## 🚀 快速开始

### 1. 克隆仓库
```bash
git clone git@github.com:Dxboy266/leetcode-hot-100.git
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

### 数组
- [√] [1. 两数之和](src/main/java/com/leetcode/array/TwoSum.java) - 简单
- [√] [283. 移动零](src/main/java/com/leetcode/array/MoveZeroes.java) - 简单
- [√] [128. 最长连续序列](src/main/java/com/leetcode/array/LongestConsecutiveSequence.java) - 中等
- [√] [560. 和为K的子数组](src/main/java/com/leetcode/array/SubarraySumEqualsK.java) - 中等
- [ ] [53. 最大子数组和](src/main/java/com/leetcode/array/MaxSubArray.java) - 中等
- [ ] [88. 合并两个有序数组](src/main/java/com/leetcode/array/MergeSortedArray.java) - 简单
- [ ] [169. 多数元素](src/main/java/com/leetcode/array/MajorityElement.java) - 简单
- [ ] [189. 轮转数组](src/main/java/com/leetcode/array/RotateArray.java) - 中等
- [ ] [217. 存在重复元素](src/main/java/com/leetcode/array/ContainsDuplicate.java) - 简单
- [ ] [238. 除自身以外数组的乘积](src/main/java/com/leetcode/array/ProductExceptSelf.java) - 中等
- [ ] [350. 两个数组的交集 II](src/main/java/com/leetcode/array/Intersect.java) - 简单
- [ ] [448. 找到所有数组中消失的数字](src/main/java/com/leetcode/array/FindDisappearedNumbers.java) - 简单
- [ ] [581. 最短无序连续子数组](src/main/java/com/leetcode/array/FindUnsortedSubarray.java) - 中等
- [ ] [121. 买卖股票的最佳时机](src/main/java/com/leetcode/array/MaxProfit.java) - 简单
- [ ] [122. 买卖股票的最佳时机 II](src/main/java/com/leetcode/array/MaxProfitII.java) - 中等
- [ ] [152. 乘积最大子数组](src/main/java/com/leetcode/array/MaxProduct.java) - 中等
- [ ] [11. 盛最多水的容器](src/main/java/com/leetcode/array/MaxArea.java) - 中等

### 栈
- [√] [20. 有效的括号](src/main/java/com/leetcode/stack/ValidParentheses.java) - 简单
- [√] [739. 每日温度](src/main/java/com/leetcode/stack/DailyTemperatures.java) - 中等
- [ ] [155. 最小栈](src/main/java/com/leetcode/stack/MinStack.java) - 简单
- [ ] [394. 字符串解码](src/main/java/com/leetcode/stack/DecodeString.java) - 中等
- [ ] [84. 柱状图中最大的矩形](src/main/java/com/leetcode/stack/LargestRectangleArea.java) - 困难

### 链表 ⭐ 基础完成
- [√] [2. 两数相加](src/main/java/com/leetcode/linkedlist/AddTwoNumbers.java) - 中等
- [√] [19. 删除链表的倒数第 N 个结点](src/main/java/com/leetcode/linkedlist/RemoveNthFromEnd.java) - 中等
- [√] [21. 合并两个有序链表](src/main/java/com/leetcode/linkedlist/MergeTwoSortedLists.java) - 简单
- [√] [24. 两两交换链表中的节点](src/main/java/com/leetcode/linkedlist/SwapPairs.java) - 中等
- [√] [141. 环形链表](src/main/java/com/leetcode/linkedlist/HasCycle.java) - 简单
- [√] [142. 环形链表 II](src/main/java/com/leetcode/linkedlist/DetectCycle.java) - 中等
- [√] [146. LRU 缓存](src/main/java/com/leetcode/linkedlist/LRUCache.java) - 中等
- [√] [160. 相交链表](src/main/java/com/leetcode/linkedlist/GetIntersectionNode.java) - 简单
- [√] [206. 反转链表](src/main/java/com/leetcode/linkedlist/ReverseList.java) - 简单
- [√] [234. 回文链表](src/main/java/com/leetcode/linkedlist/IsPalindrome.java) - 简单
- [√] [876. 链表的中间结点](src/main/java/com/leetcode/linkedlist/MiddleNode.java) - 简单
- [ ] [138. 随机链表的复制](src/main/java/com/leetcode/linkedlist/CopyRandomList.java) - 中等
- [ ] [148. 排序链表](src/main/java/com/leetcode/linkedlist/SortList.java) - 中等
- [ ] [23. 合并K个升序链表](src/main/java/com/leetcode/linkedlist/MergeKLists.java) - 困难

### 二叉树 🌲 进阶完成
- [√] [94. 二叉树的中序遍历](src/main/java/com/leetcode/tree/InorderTraversal.java) - 简单
- [√] [144. 二叉树的前序遍历](src/main/java/com/leetcode/tree/PreorderTraversal.java) - 简单
- [√] [101. 对称二叉树](src/main/java/com/leetcode/tree/IsSymmetric.java) - 简单
- [√] [102. 二叉树的层序遍历](src/main/java/com/leetcode/tree/LevelOrder.java) - 中等
- [√] [104. 二叉树的最大深度](src/main/java/com/leetcode/tree/MaxDepth.java) - 简单
- [√] [226. 翻转二叉树](src/main/java/com/leetcode/tree/InvertTree.java) - 简单
- [√] [98. 验证二叉搜索树](src/main/java/com/leetcode/tree/IsValidBST.java) - 中等
- [√] [236. 二叉树的最近公共祖先](src/main/java/com/leetcode/tree/LowestCommonAncestor.java) - 中等 ⭐ 核心题
- [√] [543. 二叉树的直径](src/main/java/com/leetcode/tree/DiameterOfBinaryTree.java) - 简单
- [ ] [105. 从前序与中序遍历序列构造二叉树](src/main/java/com/leetcode/tree/BuildTree.java) - 中等
- [ ] [114. 二叉树展开为链表](src/main/java/com/leetcode/tree/Flatten.java) - 中等
- [ ] [199. 二叉树的右视图](src/main/java/com/leetcode/tree/RightSideView.java) - 中等
- [ ] [230. 二叉搜索树中第K小的元素](src/main/java/com/leetcode/tree/KthSmallest.java) - 中等
- [ ] [437. 路径总和 III](src/main/java/com/leetcode/tree/PathSum.java) - 中等

### 动态规划 🧠 入门完成
- [√] [70. 爬楼梯](src/main/java/com/leetcode/dp/ClimbStairs.java) - 简单（DP数组/滚动变量/记忆化）
- [√] [53. 最大子数组和](src/main/java/com/leetcode/dp/MaxSubArray.java) - 中等（Kadane/DP/前缀和/分治/线段树）
- [√] [198. 打家劫舍](src/main/java/com/leetcode/dp/Rob.java) - 中等（DP数组/滚动变量/记忆化搜索）

### 字符串
- [√] [49. 字母异位词分组](src/main/java/com/leetcode/string/GroupAnagrams.java) - 中等
- [ ] [242. 有效的字母异位词](src/main/java/com/leetcode/string/IsAnagram.java) - 简单
- [ ] [438. 找到字符串中所有字母异位词](src/main/java/com/leetcode/string/FindAnagrams.java) - 中等
- [ ] [567. 字符串的排列](src/main/java/com/leetcode/string/CheckInclusion.java) - 中等
- [ ] [76. 最小覆盖子串](src/main/java/com/leetcode/string/MinWindow.java) - 困难
- [ ] [3. 无重复字符的最长子串](src/main/java/com/leetcode/string/LengthOfLongestSubstring.java) - 中等
- [ ] [5. 最长回文子串](src/main/java/com/leetcode/string/LongestPalindrome.java) - 中等
- [ ] [647. 回文子串](src/main/java/com/leetcode/string/CountSubstrings.java) - 中等

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
**最后更新**: 2025-10-24  
**当前连续刷题**: 13天 🔥