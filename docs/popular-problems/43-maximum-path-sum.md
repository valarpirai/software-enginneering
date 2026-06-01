# Maximum Path Sum

**Difficulty:** Hard | **LeetCode:** [Binary Tree Maximum Path Sum #124](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

---

## Problem

Given a binary tree, find the path with the maximum sum. A path can start and end at any node and must travel downward through parent-child connections.

---

## Intuition

At each node, the best path through it uses: the node's value + the best gain from the left child + the best gain from the right child. A child contributes 0 if it's negative (skip it). Track the global max across all nodes, but return only the best single-branch gain upward (can't split at two nodes simultaneously).

---

## Approach

**DFS post-order** — for each node, compute the best contribution it can make to its parent (one direction only). Update a global `max` with the best path that passes through this node (can use both children).

---

## Sample Input / Output

```
Tree:
       -10
       /  \
      9   20
         /  \
        15   7

Output: 42   (path: 15→20→7)
```

---

## Step-by-step Trace

Input: tree above

| Node | Left gain | Right gain | Path through node | Contribution up |
|------|-----------|------------|-------------------|----------------|
| 9 | 0 | 0 | 9 | 9 |
| 15 | 0 | 0 | 15 | 15 |
| 7 | 0 | 0 | 7 | 7 |
| 20 | 15 | 7 | 15+20+7=**42** | 20+15=35 |
| -10 | 9 | 35 | 9+(-10)+35=34 | 35+(-10)=25 |

Global max = **42** ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(h)
int maxSum = Integer.MIN_VALUE;

int maxPathSum(TreeNode root) {
    dfs(root);
    return maxSum;
}

int dfs(TreeNode node) {
    if (node == null) return 0;
    int left  = Math.max(0, dfs(node.left));  // ignore negative gains
    int right = Math.max(0, dfs(node.right));
    maxSum = Math.max(maxSum, node.val + left + right); // path through node
    return node.val + Math.max(left, right);            // best single branch up
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| Single node `[-3]` | `-3` | Must include at least one node |
| All negative | Max single node | Best path is the least negative node |
| `[1,-2,-3,1,3,-2,null,-1]` | `3` | Need careful negative handling |

---

## Related Problems

| Problem | Link |
|---------|------|
| Path Sum | [LeetCode 112](https://leetcode.com/problems/path-sum/) |
| Path Sum II | [LeetCode 113](https://leetcode.com/problems/path-sum-ii/) |
| Diameter of Binary Tree | [LeetCode 543](https://leetcode.com/problems/diameter-of-binary-tree/) |
