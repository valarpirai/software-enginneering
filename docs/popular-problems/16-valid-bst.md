# Valid Binary Search Tree

**Difficulty:** Medium | **LeetCode:** [Validate Binary Search Tree #98](https://leetcode.com/problems/validate-binary-search-tree/)

---

## Problem

Given the root of a binary tree, determine if it is a valid BST. A valid BST means: every node in the left subtree is strictly less than the node, and every node in the right subtree is strictly greater.

---

## Intuition

Checking only direct children is not enough. A node deep in the right subtree must be greater than all ancestors above it. Pass a valid range `(min, max)` down the tree. At each node, check the value falls within the range, then tighten the range for each child.

---

## Approach

**DFS with min/max bounds** — root has bounds `(-∞, +∞)`. Going left narrows the upper bound. Going right narrows the lower bound. Any violation returns false immediately.

---

## Sample Input / Output

```
Valid BST:          Invalid BST:
      5                   5
     / \                 / \
    1   4               1   4
       / \                 / \
      3   6               3   6
                 (3 < 5 violates the rule)
Output: false
```

---

## Step-by-step Trace

Input: invalid tree above (root=5, right=4, right.left=3)

| Node | min bound | max bound | val | Valid? |
|------|-----------|-----------|-----|--------|
| 5 | -∞ | +∞ | 5 | Yes |
| 1 | -∞ | 5 | 1 | Yes |
| 4 | 5 | +∞ | 4 | **No — 4 < min(5) → return false** |

---

## Java Solution

```java
// Time: O(n)  Space: O(h)
boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;
    return validate(node.left,  min, node.val) &&
           validate(node.right, node.val, max);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `true` | Empty tree is valid |
| Single node | `true` | No children to violate |
| `[Integer.MIN_VALUE]` | `true` | Use `Long` bounds to avoid overflow |
| `[2, 2, 2]` | `false` | Equal values not allowed in BST |

---

## Related Problems

| Problem | Link |
|---------|------|
| Kth Smallest Element in a BST | [LeetCode 230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) |
| Recover Binary Search Tree | [LeetCode 99](https://leetcode.com/problems/recover-binary-search-tree/) |
| Insert into a Binary Search Tree | [LeetCode 701](https://leetcode.com/problems/insert-into-a-binary-search-tree/) |
