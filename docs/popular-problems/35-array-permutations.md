# Array Permutations

**Difficulty:** Medium | **LeetCode:** [Permutations #46](https://leetcode.com/problems/permutations/)

---

## Problem

Given an array of distinct integers, return all possible permutations.

---

## Intuition

A permutation places every element exactly once. At each position, pick any unused element. Mark it used, fill the rest, then unmark (backtrack) to try the next option.

---

## Approach

**Backtracking with used-array** — track which elements are used. When the current list has all n elements, record the permutation. Otherwise, try each unused element at the current position.

---

## Sample Input / Output

```
Input:  nums = [1, 2, 3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

---

## Step-by-step Trace

Input: `nums = [1, 2, 3]` (partial — first branch only)

| current | used | Choose | Recurse |
|---------|------|--------|---------|
| [] | [F,F,F] | 1 | [1] |
| [1] | [T,F,F] | 2 | [1,2] |
| [1,2] | [T,T,F] | 3 | [1,2,3] ✓ |
| [1,2,3] | — | done | add to result |
| [1,2] | [T,T,F] | backtrack | remove 3 |
| [1] | [T,F,F] | 3 | [1,3] |
| [1,3] | [T,F,T] | 2 | [1,3,2] ✓ |

---

## Java Solution

```java
// Time: O(n × n!)  Space: O(n)
List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
    return result;
}

void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        used[i] = true;
        current.add(nums[i]);
        backtrack(nums, used, current, result);
        current.remove(current.size() - 1);
        used[i] = false;
    }
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1]` | `[[1]]` | Single element — one permutation |
| `[1,2]` | `[[1,2],[2,1]]` | Two elements — two permutations |

---

## Related Problems

| Problem | Link |
|---------|------|
| Permutations II (with duplicates) | [LeetCode 47](https://leetcode.com/problems/permutations-ii/) |
| Next Permutation | [LeetCode 31](https://leetcode.com/problems/next-permutation/) |
| Subsets | [LeetCode 78](https://leetcode.com/problems/subsets/) |
