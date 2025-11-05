package com.leetcode.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 207. 课程表 - 测试类
 */
class CourseScheduleTest {

    private final CourseSchedule solution = new CourseSchedule();

    @Test
    void testExample1() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        assertTrue(solution.canFinish(numCourses, prerequisites));
        assertTrue(solution.canFinishDFS(numCourses, prerequisites));
        assertTrue(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testExample2() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        assertFalse(solution.canFinish(numCourses, prerequisites));
        assertFalse(solution.canFinishDFS(numCourses, prerequisites));
        assertFalse(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testNoCourses() {
        int numCourses = 1;
        int[][] prerequisites = {};
        assertTrue(solution.canFinish(numCourses, prerequisites));
        assertTrue(solution.canFinishDFS(numCourses, prerequisites));
        assertTrue(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testMultipleCourses() {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        assertTrue(solution.canFinish(numCourses, prerequisites));
        assertTrue(solution.canFinishDFS(numCourses, prerequisites));
        assertTrue(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testCycleInMiddle() {
        int numCourses = 5;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {1, 3}, {4, 3}};
        assertFalse(solution.canFinish(numCourses, prerequisites));
        assertFalse(solution.canFinishDFS(numCourses, prerequisites));
        assertFalse(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testSelfCycle() {
        int numCourses = 2;
        int[][] prerequisites = {{0, 0}};
        assertFalse(solution.canFinish(numCourses, prerequisites));
        assertFalse(solution.canFinishDFS(numCourses, prerequisites));
        assertFalse(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testLongChain() {
        int numCourses = 5;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {4, 3}};
        assertTrue(solution.canFinish(numCourses, prerequisites));
        assertTrue(solution.canFinishDFS(numCourses, prerequisites));
        assertTrue(solution.canFinishUnionFind(numCourses, prerequisites));
    }

    @Test
    void testComplexValid() {
        int numCourses = 6;
        int[][] prerequisites = {
            {1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}, {5, 4}
        };
        assertTrue(solution.canFinish(numCourses, prerequisites));
        assertTrue(solution.canFinishDFS(numCourses, prerequisites));
        assertTrue(solution.canFinishUnionFind(numCourses, prerequisites));
    }
}

