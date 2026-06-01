# Lowest Common Ancestor

**Difficulty:** Medium | **LeetCode:** [Lowest Common Ancestor of a Binary Tree #236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

---

## Problem

Given a binary tree and two nodes `p` and `q`, find their lowest common ancestor (LCA) — the deepest node that has both `p` and `q` as descendants (a node can be a descendant of itself).

---

## Intuition

Search recursively. If the current node is `p` or `q`, return it — this node is the LCA or part of the path. If both left and right subtrees return non-null, the current node is the LCA. If only one returns non-null, that result bubbles up.

---

## Approach

**DFS post-order** — recurse to leaves first. Return the node if it matches `p` or `q`. The LCA is where both left and right return non-null.

---

## Sample Input / Output

```
Tree:
          3
         / \
        5   1
       / \ / \
      6  2 0  8
        / \
       7   4

LCA(5, 1) = 3
LCA(5, 4) = 5   (5 is an ancestor of itself)
```

---

## Step-by-step Trace

Input: find LCA(5, 4)

| Node | Left returns | Right returns | Return |
|------|-------------|---------------|--------|
| 6 | null | null | null |
| 7 | null | null | null |
| 4 | null | null | **4** (matches q) |
| 2 | null (7→null) | 4 | 4 |
| 5 | null (6→null) | 4 | **5** (matches p, and right=4≠null but 5 is p) |
| 1 | null | null | null |
| 3 | 5 | null | 5 |

LCA = `5` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(h)
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left  = lowestCommonAncestor(root.left,  p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) return root; // root is LCA
    return left != null ? left : right;             // bubble up the found node
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| One node is ancestor of other | The ancestor node | A node is a descendant of itself |
| Both nodes are the root | Root | Root is LCA of everything |
| `p == q` | `p` | LCA of a node with itself is itself |

---

## Related Problems

| Problem | Link |
|---------|------|
| Lowest Common Ancestor of a BST | [LeetCode 235](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) |
| Smallest Common Region | [LeetCode 1257](https://leetcode.com/problems/smallest-common-region/) |
| Path Sum III | [LeetCode 437](https://leetcode.com/problems/path-sum-iii/) |
