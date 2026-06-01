# Reverse Binary Tree

**Difficulty:** Easy | **LeetCode:** [Invert Binary Tree #226](https://leetcode.com/problems/invert-binary-tree/)

---

## Problem

Given the root of a binary tree, invert it — swap every node's left and right children. Return the root.

---

## Intuition

To invert a tree, swap the left and right children at every node. Do this recursively — invert the left subtree, invert the right subtree, then swap them.

---

## Approach

**Recursion (DFS postorder)** — recurse to the leaves first, then swap children on the way back up. Alternatively, use BFS and swap at every level.

---

## Sample Input / Output

```
Input:          Output:
      4               4
     / \             / \
    2   7    →      7   2
   / \ / \         / \ / \
  1  3 6  9       9  6 3  1
```

---

## Step-by-step Trace

Input: tree `[4, 2, 7, 1, 3, 6, 9]`

| Call | Node | Action |
|------|------|--------|
| invert(4) | 4 | recurse left, recurse right, then swap |
| invert(2) | 2 | recurse left→1, recurse right→3, swap → node 2 children: [3,1] |
| invert(1) | 1 | leaf → return |
| invert(3) | 3 | leaf → return |
| invert(7) | 7 | recurse left→6, recurse right→9, swap → node 7 children: [9,6] |
| invert(6) | 6 | leaf → return |
| invert(9) | 9 | leaf → return |
| back to 4 | 4 | swap children → left=7, right=2 |

Result: `[4, 7, 2, 9, 6, 3, 1]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(h) — h = tree height
TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode left  = invertTree(root.left);
    TreeNode right = invertTree(root.right);
    root.left  = right;
    root.right = left;
    return root;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `null` | Empty tree |
| Single node | Same node | No children to swap |
| Only left child | Right becomes left | One-sided swap |

---

## Related Problems

| Problem | Link |
|---------|------|
| Symmetric Tree | [LeetCode 101](https://leetcode.com/problems/symmetric-tree/) |
| Same Tree | [LeetCode 100](https://leetcode.com/problems/same-tree/) |
| Maximum Depth of Binary Tree | [LeetCode 104](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
