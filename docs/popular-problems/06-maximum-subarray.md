# Maximum Subarray

**Difficulty:** Medium | **LeetCode:** [Maximum Subarray #53](https://leetcode.com/problems/maximum-subarray/)

---

## Problem

Given an integer array, find the contiguous subarray with the largest sum and return its sum.

---

## Intuition

At each position, decide: extend the current subarray or start fresh. If the running sum drops below zero, it drags down any future subarray — start over. Track the best sum seen so far.

---

## Approach

**Kadane's Algorithm** — one pass. Keep a running `current` sum. Reset it to `nums[i]` when it drops below zero (starting fresh is better than extending a negative sum). Update `max` at every step.

---

## Sample Input / Output

```
Input:  nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6   (subarray [4, -1, 2, 1])
```

---

## Step-by-step Trace

Input: `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| i | nums[i] | current | max | Decision |
|---|---------|---------|-----|----------|
| 0 | -2 | -2 | -2 | start |
| 1 | 1 | 1 | 1 | -2+1=-1 < 1 → restart at 1 |
| 2 | -3 | -2 | 1 | extend |
| 3 | 4 | 4 | 4 | -2+4=2 < 4 → restart at 4 |
| 4 | -1 | 3 | 4 | extend |
| 5 | 2 | 5 | 5 | extend |
| 6 | 1 | 6 | **6** | extend |
| 7 | -5 | 1 | 6 | extend |
| 8 | 4 | 5 | 6 | extend |

Return `6` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
int maxSubArray(int[] nums) {
    int current = nums[0], max = nums[0];
    for (int i = 1; i < nums.length; i++) {
        current = Math.max(nums[i], current + nums[i]);
        max = Math.max(max, current);
    }
    return max;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[-1, -2, -3]` | `-1` | All negative — return the least negative |
| `[5]` | `5` | Single element |
| `[1, 2, 3]` | `6` | All positive — entire array |

---

## Related Problems

| Problem | Link |
|---------|------|
| Maximum Product Subarray | [LeetCode 152](https://leetcode.com/problems/maximum-product-subarray/) |
| Best Time to Buy and Sell Stock | [LeetCode 121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) |
| Subarray Sum Equals K | [LeetCode 560](https://leetcode.com/problems/subarray-sum-equals-k/) |
