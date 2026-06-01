# Longest Consecutive Sequence

**Difficulty:** Medium | **LeetCode:** [#128](https://leetcode.com/problems/longest-consecutive-sequence/)

---

## Problem

Given an unsorted array of integers, find the length of the longest sequence of consecutive integers. Must run in O(n).

---

## Intuition

Put all numbers in a HashSet. For each number, only start counting if it's the beginning of a sequence (no `n-1` in the set). Then count how far the sequence extends. This avoids counting the same sequence from the middle.

---

## Approach

**HashSet** — O(1) lookups. Only begin a sequence count from the smallest number in that sequence (`n-1` not in set). Extend as far as possible.

---

## Sample Input / Output

```
Input:  nums = [100, 4, 200, 1, 3, 2]
Output: 4   (sequence 1, 2, 3, 4)
```

---

## Step-by-step Trace

Input: set = {100, 4, 200, 1, 3, 2}

| num | n-1 in set? | Start sequence? | Count | max |
|-----|------------|-----------------|-------|-----|
| 100 | 99 not in set | Yes | 100→ nothing after | 1 |
| 4 | 3 in set | No | — | 1 |
| 200 | 199 not in set | Yes | 200→ nothing after | 1 |
| 1 | 0 not in set | Yes | 1→2→3→4 = **4** | 4 |
| 3 | 2 in set | No | — | 4 |
| 2 | 1 in set | No | — | 4 |

Return `4` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int n : nums) set.add(n);
    int max = 0;
    for (int n : set) {
        if (!set.contains(n - 1)) { // start of a sequence
            int count = 1;
            while (set.contains(n + count)) count++;
            max = Math.max(max, count);
        }
    }
    return max;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[]` | `0` | Empty array |
| `[1]` | `1` | Single element |
| `[1,1,1]` | `1` | All duplicates — set deduplicates |
| Negative numbers | Works | HashSet handles negatives |

---

## Related Problems

| Problem | Link |
|---------|------|
| Missing Number | [LeetCode 268](https://leetcode.com/problems/missing-number/) |
| Find All Numbers Disappeared in an Array | [LeetCode 448](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) |
| Longest Arithmetic Subsequence | [LeetCode 1027](https://leetcode.com/problems/longest-arithmetic-subsequence/) |
