# Subsets That Sum to k

**Difficulty:** Medium | **LeetCode:** [Combination Sum II #40](https://leetcode.com/problems/combination-sum-ii/)

---

## Problem

Given an array of integers (may contain duplicates) and a target `k`, find all unique subsets that sum to `k`. Each number may be used only once. No duplicate subsets in the result.

---

## Intuition

Use backtracking. At each step, include or skip the current element. Sort first so duplicates are adjacent — skip a duplicate element if the previous identical element at the same recursion level was skipped.

---

## Approach

**Backtracking with pruning** — sort the array. At each recursion level, skip `candidates[i]` if `i > start` and `candidates[i] == candidates[i-1]` (duplicate at same level). Prune when remaining < 0.

---

## Sample Input / Output

```
Input:  candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
```

---

## Step-by-step Trace

Input: sorted `[1, 1, 2, 5, 6, 7, 10]`, target = 8

| Path | remaining | Action |
|------|-----------|--------|
| [] | 8 | try 1 |
| [1] | 7 | try 1 |
| [1,1] | 6 | try 2 → skip, try 5 → skip (7-5=2), try 6 → [1,1,6] ✓ |
| [1] | 7 | try 2 |
| [1,2] | 5 | try 5 → [1,2,5] ✓ |
| [1] | 7 | try 7 → [1,7] ✓ |
| [] | 8 | skip second 1 (dup at level 0) |
| [] | 8 | try 2 |
| [2] | 6 | try 6 → [2,6] ✓ |

---

## Java Solution

```java
// Time: O(2^n)  Space: O(n)
List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(candidates, target, 0, new ArrayList<>(), result);
    return result;
}

void backtrack(int[] nums, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
    if (remaining == 0) { result.add(new ArrayList<>(current)); return; }
    for (int i = start; i < nums.length; i++) {
        if (nums[i] > remaining) break; // pruning
        if (i > start && nums[i] == nums[i-1]) continue; // skip duplicates
        current.add(nums[i]);
        backtrack(nums, remaining - nums[i], i + 1, current, result);
        current.remove(current.size() - 1);
    }
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| No valid subset | `[]` | Sum too high |
| Single element equals target | `[[k]]` | One subset |
| All same elements `[2,2,2], k=4` | `[[2,2]]` | Duplicate suppression |

---

## Related Problems

| Problem | Link |
|---------|------|
| Combination Sum (reuse allowed) | [LeetCode 39](https://leetcode.com/problems/combination-sum/) |
| Subsets | [LeetCode 78](https://leetcode.com/problems/subsets/) |
| Partition Equal Subset Sum | [LeetCode 416](https://leetcode.com/problems/partition-equal-subset-sum/) |
