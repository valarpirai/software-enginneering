# Graph Depth First Search

**Difficulty:** Medium | **LeetCode:** [Number of Islands #200](https://leetcode.com/problems/number-of-islands/)

---

## Problem

Given an `m × n` grid of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and formed by connecting adjacent lands horizontally or vertically.

---

## Intuition

Every unvisited land cell ('1') is the start of an island. DFS from it, marking every connected land cell as visited. Count how many times you start a DFS.

---

## Approach

**DFS flood fill** — iterate every cell. When you find an unvisited '1', increment the island count and DFS to sink (mark as '0') all connected land cells.

---

## Sample Input / Output

```
Input:
  grid = [['1','1','0','0','0'],
          ['1','1','0','0','0'],
          ['0','0','1','0','0'],
          ['0','0','0','1','1']]

Output: 3
```

---

## Step-by-step Trace

Input: grid above

| Step | Cell | Action | Count |
|------|------|--------|-------|
| 1 | (0,0)='1' | Start DFS → sink (0,0),(0,1),(1,0),(1,1) | 1 |
| 2 | (0,1)='0' | already sunk | 1 |
| 3 | (2,2)='1' | Start DFS → sink (2,2) | 2 |
| 4 | (3,3)='1' | Start DFS → sink (3,3),(3,4) | 3 |

Return `3` ✓

---

## Java Solution

```java
// Time: O(m×n)  Space: O(m×n) — recursion stack
int numIslands(char[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++)
        for (int j = 0; j < grid[0].length; j++)
            if (grid[i][j] == '1') {
                dfs(grid, i, j);
                count++;
            }
    return count;
}

void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
        return;
    grid[i][j] = '0'; // mark visited by sinking
    dfs(grid, i+1, j);
    dfs(grid, i-1, j);
    dfs(grid, i, j+1);
    dfs(grid, i, j-1);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| All water | `0` | No islands |
| All land | `1` | One big island |
| Single cell '1' | `1` | Minimal island |
| Diagonal land cells | each is separate island | Only horizontal/vertical count |

---

## Related Problems

| Problem | Link |
|---------|------|
| Max Area of Island | [LeetCode 695](https://leetcode.com/problems/max-area-of-island/) |
| Surrounded Regions | [LeetCode 130](https://leetcode.com/problems/surrounded-regions/) |
| Pacific Atlantic Water Flow | [LeetCode 417](https://leetcode.com/problems/pacific-atlantic-water-flow/) |
