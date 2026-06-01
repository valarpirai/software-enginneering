# Tree Depth First Search

**Difficulty:** Easy | **LeetCode:** [Binary Tree Inorder Traversal #94](https://leetcode.com/problems/binary-tree-inorder-traversal/)

---

## Problem

Given the root of a binary tree, return all node values visited in depth-first order. Three variants: inorder (left→root→right), preorder (root→left→right), postorder (left→right→root).

---

## Intuition

DFS dives as deep as possible before backtracking. Recursion handles this naturally — the call stack acts as the DFS stack. The only difference between the three traversals is when you visit the current node relative to its children.

---

## Approach

**Recursion** — base case: null node returns immediately. Recursive case: visit left subtree, visit node, visit right subtree (for inorder).

---

## Sample Input / Output

```
Tree:
      4
     / \
    2   6
   / \ / \
  1  3 5  7

Inorder output:   [1, 2, 3, 4, 5, 6, 7]
Preorder output:  [4, 2, 1, 3, 6, 5, 7]
Postorder output: [1, 3, 2, 5, 7, 6, 4]
```

---

## Step-by-step Trace — Inorder

Input: tree above

| Call | Node | Action |
|------|------|--------|
| inorder(4) | 4 | go left → inorder(2) |
| inorder(2) | 2 | go left → inorder(1) |
| inorder(1) | 1 | left null, **visit 1**, right null |
| back to 2 | 2 | **visit 2**, go right → inorder(3) |
| inorder(3) | 3 | left null, **visit 3**, right null |
| back to 4 | 4 | **visit 4**, go right → inorder(6) |
| inorder(6) | 6 | go left → inorder(5) |
| inorder(5) | 5 | left null, **visit 5**, right null |
| back to 6 | 6 | **visit 6**, go right → inorder(7) |
| inorder(7) | 7 | **visit 7** |

Output: `[1, 2, 3, 4, 5, 6, 7]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(h) — h = tree height

// Inorder: Left → Root → Right
void inorder(TreeNode node, List<Integer> result) {
    if (node == null) return;
    inorder(node.left, result);
    result.add(node.val);
    inorder(node.right, result);
}

// Preorder: Root → Left → Right
void preorder(TreeNode node, List<Integer> result) {
    if (node == null) return;
    result.add(node.val);
    preorder(node.left, result);
    preorder(node.right, result);
}

// Postorder: Left → Right → Root
void postorder(TreeNode node, List<Integer> result) {
    if (node == null) return;
    postorder(node.left, result);
    postorder(node.right, result);
    result.add(node.val);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `[]` | Empty tree |
| Single node `[1]` | `[1]` | No children |
| Left-skewed tree | Visits in order | Works — recursion depth = n |

---

## Related Problems

| Problem | Link |
|---------|------|
| Binary Tree Preorder Traversal | [LeetCode 144](https://leetcode.com/problems/binary-tree-preorder-traversal/) |
| Binary Tree Postorder Traversal | [LeetCode 145](https://leetcode.com/problems/binary-tree-postorder-traversal/) |
| Path Sum | [LeetCode 112](https://leetcode.com/problems/path-sum/) |
