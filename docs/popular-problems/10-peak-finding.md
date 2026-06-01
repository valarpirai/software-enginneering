# Peak Finding

**Difficulty:** Medium | **LeetCode:** [Find Peak Element #162](https://leetcode.com/problems/find-peak-element/)

---

## Problem

A peak element is greater than its neighbors. Given an array where `nums[-1]` and `nums[n]` are negative infinity, find any peak element's index. Must run in O(log n).

---

## Intuition

Binary search on the slope. If `nums[mid] < nums[mid+1]`, the right side is going up — a peak must exist to the right. Otherwise, a peak must exist to the left (or at mid). Eliminate half the array each step.

---

## Approach

**Binary Search on value, not index** — compare `nums[mid]` with `nums[mid+1]`. Move toward the uphill side. When left equals right, that index is a peak.

---

## Sample Input / Output

```
Input:  nums = [1, 2, 3, 1]
Output: 2   (nums[2] = 3, greater than nums[1]=2 and nums[3]=1)
```

---

## Step-by-step Trace

Input: `nums = [1, 2, 3, 1]`

| Step | left | right | mid | nums[mid] | nums[mid+1] | Decision |
|------|------|-------|-----|-----------|-------------|----------|
| 1 | 0 | 3 | 1 | 2 | 3 | 2 < 3 → go right, left = 2 |
| 2 | 2 | 3 | 2 | 3 | 1 | 3 > 1 → go left, right = 2 |
| 3 | 2 | 2 | — | — | — | left == right → **return 2** |

---

## Java Solution

```java
// Time: O(log n)  Space: O(1)
int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < nums[mid + 1]) left  = mid + 1;
        else                           right = mid;
    }
    return left;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1]` | `0` | Single element is always a peak |
| `[1, 2]` | `1` | Right element is the peak |
| `[3, 2, 1]` | `0` | Strictly decreasing — first is peak |
| `[1, 2, 1, 3, 5, 6, 4]` | `1` or `5` | Multiple peaks — any valid |

---

## Related Problems

| Problem | Link |
|---------|------|
| Find Minimum in Rotated Sorted Array | [LeetCode 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) |
| Search in Rotated Sorted Array | [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/) |
| Peak Index in a Mountain Array | [LeetCode 852](https://leetcode.com/problems/peak-index-in-a-mountain-array/) |
