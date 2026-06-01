# Balanced Binary Tree

**Difficulty:** Easy | **LeetCode:** [#110](https://leetcode.com/problems/balanced-binary-tree/)

---

## Problem

Given the root of a binary tree, determine if it is height-balanced. A tree is balanced if the heights of the left and right subtrees of every node differ by at most 1.

---

## Intuition

Check bottom-up. Compute the height of each subtree recursively. If at any node the height difference exceeds 1, the tree is unbalanced — return -1 as a sentinel. Any node receiving -1 from a child propagates -1 upward without extra work.

---

## Approach

**DFS with height sentinel** — return the actual height if balanced, or -1 if unbalanced. This avoids recomputing heights from the top down (which would be O(n²)).

---

## Sample Input / Output

```
Balanced:        Unbalanced:
      3                 1
     / \               /
    9  20             2
      /  \           /
     15   7         3

Output: true     Output: false
```

---

## Step-by-step Trace

Input: unbalanced tree — root=1, left=2, left.left=3

| Node | Left height | Right height | Diff | Return |
|------|-------------|--------------|------|--------|
| 3 | 0 | 0 | 0 | 1 |
| 2 | 1 | 0 | 1 | 2 |
| 1 | 2 | 0 | **2 > 1** | **-1** |

Return `-1` → `isBalanced` returns `false` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(h)
boolean isBalanced(TreeNode root) {
    return height(root) != -1;
}

int height(TreeNode node) {
    if (node == null) return 0;
    int left  = height(node.left);
    if (left == -1) return -1;
    int right = height(node.right);
    if (right == -1) return -1;
    if (Math.abs(left - right) > 1) return -1;
    return 1 + Math.max(left, right);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `true` | Empty tree is balanced |
| Single node | `true` | Height 1, no children |
| Perfect binary tree | `true` | All levels full |
| Linked-list shaped | `false` | Height diff grows each level |

---

## Related Problems

| Problem | Link |
|---------|------|
| Maximum Depth of Binary Tree | [LeetCode 104](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
| Diameter of Binary Tree | [LeetCode 543](https://leetcode.com/problems/diameter-of-binary-tree/) |
| Minimum Depth of Binary Tree | [LeetCode 111](https://leetcode.com/problems/minimum-depth-of-binary-tree/) |
