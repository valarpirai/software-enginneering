# Tree Breadth First Search

**Difficulty:** Medium | **LeetCode:** [Binary Tree Level Order Traversal #102](https://leetcode.com/problems/binary-tree-level-order-traversal/)

---

## Problem

Given the root of a binary tree, return the values of nodes level by level, from left to right.

---

## Intuition

Use a queue. Process nodes level by level. At the start of each level, the queue holds exactly the nodes at that level. Record the queue size, process that many nodes, then move to the next level.

---

## Approach

**BFS with level size snapshot** — before processing each level, record `queue.size()`. That tells you exactly how many nodes belong to the current level. Enqueue their children for the next level.

---

## Sample Input / Output

```
Tree:
      3
     / \
    9  20
      /  \
     15   7

Output: [[3], [9, 20], [15, 7]]
```

---

## Step-by-step Trace

Input: tree above

| Level | Queue at start | Dequeue | Enqueue children | Level result |
|-------|---------------|---------|-----------------|--------------|
| 0 | [3] | 3 | 9, 20 | [3] |
| 1 | [9, 20] | 9, 20 | 15, 7 | [9, 20] |
| 2 | [15, 7] | 15, 7 | none | [15, 7] |

Output: `[[3], [9, 20], [15, 7]]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            level.add(node.val);
            if (node.left  != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `[]` | Empty tree |
| Single node | `[[val]]` | One level with one element |
| Skewed tree | One node per level | Degenerate case still works |

---

## Related Problems

| Problem | Link |
|---------|------|
| Binary Tree Level Order Traversal II | [LeetCode 107](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/) |
| Binary Tree Zigzag Level Order Traversal | [LeetCode 103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) |
| Minimum Depth of Binary Tree | [LeetCode 111](https://leetcode.com/problems/minimum-depth-of-binary-tree/) |
