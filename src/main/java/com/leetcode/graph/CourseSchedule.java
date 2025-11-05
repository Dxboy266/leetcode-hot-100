package com.leetcode.graph;

import java.util.*;

/**
 * 207. 课程表
 * 
 * 题目链接：https://leetcode.cn/problems/course-schedule/
 * 难度：中等
 * 标签：深度优先搜索、广度优先搜索、图、拓扑排序
 *
 * ==================== 题目描述 ====================
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * 
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，需要先完成课程 0 ；
 *      并且学习课程 0 之前，还应先完成课程 1 。这是不可能的。
 *
 * 提示：
 * - 1 <= numCourses <= 2000
 * - 0 <= prerequisites.length <= 5000
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - prerequisites[i] 中的所有课程对 互不相同
 *
 * ==================== 解题思路 ====================
 * 本质：判断有向图中是否存在环
 * 
 * 两种经典解法：
 * 1. BFS + 拓扑排序（推荐）
 *    - 统计每个节点的入度
 *    - 将入度为 0 的节点加入队列
 *    - 不断从队列中取出节点，将其邻接节点的入度减 1
 *    - 如果邻接节点入度变为 0，加入队列
 *    - 最后判断是否所有节点都被访问过
 * 
 * 2. DFS + 三色标记
 *    - 白色(0)：未访问
 *    - 灰色(1)：正在访问（在当前 DFS 路径上）
 *    - 黑色(2)：已完成访问
 *    - 如果访问到灰色节点，说明存在环
 *
 * @Author Dxboy266
 * @Date 2025-11-02
 */
public class CourseSchedule {

    /**
     * 解法一：BFS + 拓扑排序（Kahn算法）⭐ 推荐
     * 
     * 核心思想：
     * - 拓扑排序：对有向无环图(DAG)的所有顶点进行线性排序
     * - 如果图中存在环，则无法完成拓扑排序
     * 
     * 算法步骤：
     * 1. 统计每个节点的入度
     * 2. 将所有入度为 0 的节点加入队列
     * 3. 从队列中取出节点，访问计数 +1
     * 4. 将该节点的所有邻接节点的入度 -1
     * 5. 如果邻接节点入度变为 0，加入队列
     * 6. 重复 3-5，直到队列为空
     * 7. 判断访问计数是否等于总节点数
     * 
     * 时间复杂度：O(V + E)，V 为课程数，E 为先修关系数
     * 空间复杂度：O(V + E)
     * 
     * @param numCourses 课程总数
     * @param prerequisites 先修课程关系
     * @return 是否可以完成所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 构建图的邻接表和入度数组
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        // 初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 构建图：[课程, 先修课程] -> 先修课程指向课程
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int preCourse = pre[1];
            graph.get(preCourse).add(course);  // preCourse -> course
            inDegree[course]++;  // course 的入度 +1
        }
        
        // 2. 将所有入度为 0 的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // 3. BFS 拓扑排序
        int visitedCount = 0;  // 记录访问过的课程数
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visitedCount++;
            
            // 遍历当前课程的所有后续课程
            for (int nextCourse : graph.get(course)) {
                inDegree[nextCourse]--;
                // 如果后续课程的入度变为 0，加入队列
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // 4. 判断是否所有课程都被访问过
        return visitedCount == numCourses;
    }

    /**
     * 解法二：DFS + 三色标记法
     * 
     * 核心思想：
     * - 使用三种颜色标记节点状态
     * - 0 (白色)：未访问
     * - 1 (灰色)：正在访问（在当前 DFS 路径上）
     * - 2 (黑色)：已完成访问
     * - 如果 DFS 过程中遇到灰色节点，说明存在环
     * 
     * 时间复杂度：O(V + E)
     * 空间复杂度：O(V + E)
     * 
     * @param numCourses 课程总数
     * @param prerequisites 先修课程关系
     * @return 是否可以完成所有课程
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 1. 构建图的邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int preCourse = pre[1];
            graph.get(preCourse).add(course);
        }
        
        // 2. 初始化状态数组
        // 0: 未访问, 1: 正在访问, 2: 已完成访问
        int[] visited = new int[numCourses];
        
        // 3. 对每个节点进行 DFS
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(graph, visited, i)) {
                    return false;  // 存在环
                }
            }
        }
        
        return true;  // 无环
    }
    
    /**
     * DFS 判断是否存在环
     * 
     * @param graph 邻接表
     * @param visited 访问状态数组
     * @param course 当前课程
     * @return 是否存在环
     */
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        // 标记当前节点为正在访问
        visited[course] = 1;
        
        // 遍历所有后续课程
        for (int nextCourse : graph.get(course)) {
            if (visited[nextCourse] == 0) {
                // 未访问过，继续 DFS
                if (hasCycle(graph, visited, nextCourse)) {
                    return true;
                }
            } else if (visited[nextCourse] == 1) {
                // 正在访问，说明存在环
                return true;
            }
            // visited[nextCourse] == 2，已完成访问，不需要再访问
        }
        
        // 标记当前节点为已完成访问
        visited[course] = 2;
        return false;
    }

    /**
     * 解法三：并查集（了解即可）
     * 
     * 思路：
     * - 逆向思维：从没有先修课程的课程开始
     * - 使用并查集记录可以学习的课程集合
     * 
     * 注意：这个方法不太适合这道题，因为并查集更适合无向图
     * BFS 和 DFS 才是标准解法
     * 
     * 时间复杂度：O(E * α(V))，α 是反阿克曼函数
     * 空间复杂度：O(V)
     */
    public boolean canFinishUnionFind(int numCourses, int[][] prerequisites) {
        // 统计入度
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
        }
        
        // 找出所有入度为 0 的课程
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            
            // 减少依赖当前课程的其他课程的入度
            for (int[] pre : prerequisites) {
                if (pre[1] == course) {
                    inDegree[pre[0]]--;
                    if (inDegree[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }
        
        return count == numCourses;
    }
}

