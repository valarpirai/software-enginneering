# Remove Node from Binary Search Tree

**Difficulty:** Medium | **LeetCode:** [Delete Node in a BST #450](https://leetcode.com/problems/delete-node-in-a-bst/)

---

## Problem

Given a BST root and a key, delete the node with that key and return the updated root.

---

## Intuition

Find the node first (binary search). Then handle three cases: (1) no children — just remove it; (2) one child — replace with the child; (3) two children — replace with the inorder successor (smallest node in the right subtree), then delete the successor from the right subtree.

---

## Approach

**Recursive BST search + deletion** — navigate to the node, apply the appropriate case, and return the updated subtree root at each level.

---

## Sample Input / Output

```
Input:
      5
     / \
    3   6
   / \   \
  2   4   7
Delete: 3

Output:
      5
     / \
    4   6
   /     \
  2       7
```

---

## Step-by-step Trace

Delete key=3 from tree above. Node 3 has two children.

| Step | Action |
|------|--------|
| 1 | Navigate: 3 < 5 → go left, find node 3 |
| 2 | Node 3 has two children → find inorder successor (min of right subtree = 4) |
| 3 | Copy successor value: node.val = 4 |
| 4 | Delete successor (4) from right subtree: node.right = delete(4-subtree, 4) |
| 5 | Node 4 has no left child → return its right (null) |

Result: node 3 replaced by 4, with 2 as left child ✓

---

## Java Solution

```java
// Time: O(h)  Space: O(h) — h = height
TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;
    if (key < root.val) {
        root.left  = deleteNode(root.left, key);
    } else if (key > root.val) {
        root.right = deleteNode(root.right, key);
    } else {
        // Found the node
        if (root.left  == null) return root.right;
        if (root.right == null) return root.left;
        // Two children: replace with inorder successor
        TreeNode successor = findMin(root.right);
        root.val   = successor.val;
        root.right = deleteNode(root.right, successor.val);
    }
    return root;
}

TreeNode findMin(TreeNode node) {
    while (node.left != null) node = node.left;
    return node;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| Key not in tree | Same tree | Nothing to delete |
| Delete root with two children | Successor becomes root | Recursive case |
| Delete leaf | Parent's pointer set to null | Simplest case |
| Delete node with one child | Child replaces node | Single-child case |

---

## Related Problems

| Problem | Link |
|---------|------|
| Insert into a BST | [LeetCode 701](https://leetcode.com/problems/insert-into-a-binary-search-tree/) |
| Search in a BST | [LeetCode 700](https://leetcode.com/problems/search-in-a-binary-search-tree/) |
| Kth Smallest Element in a BST | [LeetCode 230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) |
