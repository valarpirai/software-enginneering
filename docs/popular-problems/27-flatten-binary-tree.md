# Flatten Binary Tree

**Difficulty:** Medium | **LeetCode:** [Flatten Binary Tree to Linked List #114](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)

---

## Problem

Given the root of a binary tree, flatten it into a linked list in-place following preorder traversal order. Use the `right` pointer as `next`. Set all `left` pointers to null.

---

## Intuition

In preorder (root → left → right), the rightmost node of the left subtree is always followed by the root of the right subtree. Find that node, attach the right subtree there, move the left subtree to the right, and clear the left pointer. Repeat for each node.

---

## Approach

**Iterative preorder rewiring** — for each node: find the rightmost node in the left subtree, point its `right` to the current node's right subtree, move the left subtree to the right, set left to null.

---

## Sample Input / Output

```
Input:
        1
       / \
      2   5
     / \   \
    3   4   6

Output: 1→2→3→4→5→6 (as right-linked list)
```

---

## Step-by-step Trace

| Node | Rightmost of left | Action |
|------|------------------|--------|
| 1 | 4 (rightmost of subtree 2) | 4.right=5, 1.right=2, 1.left=null |
| 2 | 3 | 3.right=4, 2.right=3, 2.left=null |
| 3 | — | no left child, move on |
| 4 | — | no left child, move on |
| 5 | — | no left child, move on |

Result: `1→2→3→4→5→6` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
void flatten(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        if (curr.left != null) {
            // find rightmost of left subtree
            TreeNode rightmost = curr.left;
            while (rightmost.right != null) rightmost = rightmost.right;
            // attach right subtree
            rightmost.right = curr.right;
            // move left subtree to right
            curr.right = curr.left;
            curr.left = null;
        }
        curr = curr.right;
    }
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | — | Nothing to flatten |
| Single node | Same node | No children |
| Right-skewed tree | Same structure | Left is always null — no rewiring |
| Left-only tree | All moved to right | Entire left chain moves right |

---

## Related Problems

| Problem | Link |
|---------|------|
| Convert BST to Sorted Doubly Linked List | [LeetCode 426](https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/) |
| Binary Tree Preorder Traversal | [LeetCode 144](https://leetcode.com/problems/binary-tree-preorder-traversal/) |
| Flatten a Multilevel Doubly Linked List | [LeetCode 430](https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/) |
