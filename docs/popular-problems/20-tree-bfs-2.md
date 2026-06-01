# Tree Breadth First Search II

**Difficulty:** Medium | **LeetCode:** [Binary Tree Level Order Traversal II #107](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)

---

## Problem

Given the root of a binary tree, return the level order traversal from bottom to top (leaves first, root last).

---

## Intuition

Same as regular BFS level order, but reverse the result at the end. Collect levels top-to-bottom, then flip the list.

---

## Approach

**BFS + reverse** — run standard level order traversal. After collecting all levels, reverse the outer list. `Collections.reverse()` is O(levels) — negligible.

---

## Sample Input / Output

```
Tree:
      3
     / \
    9  20
      /  \
     15   7

Output: [[15, 7], [9, 20], [3]]
```

---

## Step-by-step Trace

Input: tree above

| Level | Nodes collected |
|-------|----------------|
| 0 | [3] |
| 1 | [9, 20] |
| 2 | [15, 7] |

After `Collections.reverse()`: `[[15, 7], [9, 20], [3]]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
List<List<Integer>> levelOrderBottom(TreeNode root) {
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
    Collections.reverse(result);
    return result;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `[]` | Empty tree |
| Single node | `[[val]]` | One level |
| Skewed tree | Reversed single-node levels | Works correctly |

---

## Related Problems

| Problem | Link |
|---------|------|
| Binary Tree Level Order Traversal | [LeetCode 102](https://leetcode.com/problems/binary-tree-level-order-traversal/) |
| Binary Tree Zigzag Level Order Traversal | [LeetCode 103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) |
| Average of Levels in Binary Tree | [LeetCode 637](https://leetcode.com/problems/average-of-levels-in-binary-tree/) |
