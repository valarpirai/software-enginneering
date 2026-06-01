# Find Pair That Sums to k

**Difficulty:** Easy | **LeetCode:** [Two Sum #1](https://leetcode.com/problems/two-sum/)

---

## Problem

Given an array of integers and a target `k`, return the indices of the two numbers that add up to `k`. Each input has exactly one solution. You may not use the same element twice.

---

## Intuition

For every number `x` you see, the number you need is `k - x`. Instead of checking every pair, store each number as you go. When you reach `x`, check if `k - x` is already stored. If yes — you found the pair.

---

## Approach

Use a **Hash Map** to store `value → index` as you scan left to right. For each element, compute the complement `k - x` and check the map in O(1).

---

## Sample Input / Output

```
Input:  nums = [2, 7, 11, 15], k = 9
Output: [0, 1]   (nums[0] + nums[1] = 2 + 7 = 9)
```

---

## Step-by-step Trace

Input: `nums = [2, 7, 11, 15]`, `k = 9`

| i | nums[i] | complement (k - x) | map contains? | map after |
|---|---------|-------------------|---------------|-----------|
| 0 | 2 | 7 | No | {2→0} |
| 1 | 7 | 2 | **Yes → return [map[2], 1] = [0, 1]** | — |

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
int[] twoSum(int[] nums, int k) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = k - nums[i];
        if (seen.containsKey(complement))
            return new int[]{seen.get(complement), i};
        seen.put(nums[i], i);
    }
    return new int[]{-1, -1};
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[3, 3], k=6` | `[0, 1]` | Same value at two different indices — works because we check before storing |
| `[1, 2, 3], k=10` | `[-1, -1]` | No valid pair |
| `[0, 4, 3, 0], k=0` | `[0, 3]` | Zero as value — complement of 0 is 0 |

---

## Related Problems

| Problem | Link |
|---------|------|
| 3Sum | [LeetCode 15](https://leetcode.com/problems/3sum/) |
| Two Sum II (sorted array) | [LeetCode 167](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) |
| Subarray Sum Equals K | [LeetCode 560](https://leetcode.com/problems/subarray-sum-equals-k/) |
