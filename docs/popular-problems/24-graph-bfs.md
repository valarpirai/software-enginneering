# Graph Breadth First Search

**Difficulty:** Medium | **LeetCode:** [Rotting Oranges #994](https://leetcode.com/problems/rotting-oranges/)

---

## Problem

Given a grid where `0` = empty, `1` = fresh orange, `2` = rotten orange — every minute, any fresh orange adjacent to a rotten one becomes rotten. Return the minimum minutes until no fresh oranges remain, or `-1` if impossible.

---

## Intuition

All rotten oranges spread simultaneously — this is multi-source BFS. Start with all rotten oranges in the queue. Each BFS level is one minute. Count levels until the queue is empty, then check if any fresh oranges remain.

---

## Approach

**Multi-source BFS** — enqueue all initial rotten oranges at once. Process level by level (each level = 1 minute). When a fresh neighbor is infected, add it to the queue. Count levels.

---

## Sample Input / Output

```
Input:
  grid = [[2,1,1],
          [1,1,0],
          [0,1,1]]
Output: 4
```

---

## Step-by-step Trace

Input: grid above. Initial rotten: `(0,0)`.

| Minute | Queue | Oranges infected |
|--------|-------|-----------------|
| 0 | [(0,0)] | — |
| 1 | [(0,1),(1,0)] | (0,1), (1,0) |
| 2 | [(0,2),(1,1)] | (0,2), (1,1) |
| 3 | [(2,1)] | (2,1) |
| 4 | [(2,2)] | (2,2) |

All fresh oranges infected. Return `4` ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n)
int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new ArrayDeque<>();
    int fresh = 0;

    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 2) q.offer(new int[]{i, j});
            if (grid[i][j] == 1) fresh++;
        }

    if (fresh == 0) return 0;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    int minutes = 0;

    while (!q.isEmpty()) {
        minutes++;
        for (int size = q.size(); size > 0; size--) {
            int[] cell = q.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0], c = cell[1] + d[1];
                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    grid[r][c] = 2;
                    fresh--;
                    q.offer(new int[]{r, c});
                }
            }
        }
    }
    return fresh == 0 ? minutes - 1 : -1;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| No fresh oranges | `0` | Already done |
| Fresh orange unreachable | `-1` | Isolated by empty cells |
| All rotten | `0` | Nothing to spread to |

---

## Related Problems

| Problem | Link |
|---------|------|
| Number of Islands | [LeetCode 200](https://leetcode.com/problems/number-of-islands/) |
| 01 Matrix | [LeetCode 542](https://leetcode.com/problems/01-matrix/) |
| Walls and Gates | [LeetCode 286](https://leetcode.com/problems/walls-and-gates/) |
