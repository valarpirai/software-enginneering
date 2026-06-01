# Minimum in Rotated Sorted Array

**Difficulty:** Medium | **LeetCode:** [Find Minimum in Rotated Sorted Array #153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)

---

## Problem

A sorted array was rotated at some pivot. Find the minimum element. Must run in O(log n).

---

## Intuition

In the rotated array, the minimum is where the sorted order breaks. Binary search: if `nums[mid] > nums[right]`, the minimum is in the right half. Otherwise it is in the left half (including `mid`).

---

## Approach

**Binary search on position** — compare `nums[mid]` with `nums[right]`. The side with the "break" contains the minimum.

---

## Sample Input / Output

```
Input:  nums = [3, 4, 5, 1, 2]
Output: 1

Input:  nums = [4, 5, 6, 7, 0, 1, 2]
Output: 0
```

---

## Step-by-step Trace

Input: `nums = [3, 4, 5, 1, 2]`

| Step | left | right | mid | nums[mid] | nums[right] | Decision |
|------|------|-------|-----|-----------|-------------|----------|
| 1 | 0 | 4 | 2 | 5 | 2 | 5 > 2 → min in right → left = 3 |
| 2 | 3 | 4 | 3 | 1 | 2 | 1 < 2 → min in left → right = 3 |
| 3 | 3 | 3 | — | — | — | left == right → **return nums[3] = 1** |

---

## Java Solution

```java
// Time: O(log n)  Space: O(1)
int findMin(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[right]) left  = mid + 1;
        else                         right = mid;
    }
    return nums[left];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1]` | `1` | Single element |
| `[1, 2, 3]` | `1` | Not rotated — works correctly |
| `[3, 1, 2]` | `1` | Rotated once |
| `[2, 1]` | `1` | Two elements |

---

## Related Problems

| Problem | Link |
|---------|------|
| Search in Rotated Sorted Array | [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/) |
| Find Minimum in Rotated Sorted Array II (with duplicates) | [LeetCode 154](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/) |
| Peak Finding | [LeetCode 162](https://leetcode.com/problems/find-peak-element/) |
