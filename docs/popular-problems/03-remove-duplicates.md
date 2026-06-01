# Remove Duplicates

**Difficulty:** Easy | **LeetCode:** [Remove Duplicates from Sorted Array #26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

---

## Problem

Given a sorted array, remove duplicates in-place. Each unique value must appear only once. Return the count of unique elements. The relative order must stay the same.

---

## Intuition

The array is sorted, so duplicates are always adjacent. Use two pointers: `slow` tracks the last unique position, `fast` scans ahead. When `fast` finds a new value, copy it to `slow + 1`.

---

## Approach

**Two Pointers (same direction)** — `slow` builds the result in place. `fast` advances one step at a time and copies only when it finds a new unique value.

---

## Sample Input / Output

```
Input:  nums = [1, 1, 2, 3, 3, 4]
Output: 4   (unique values: [1, 2, 3, 4])
```

---

## Step-by-step Trace

Input: `nums = [1, 1, 2, 3, 3, 4]`

| fast | nums[fast] | nums[slow] | New value? | Action | Array |
|------|-----------|-----------|------------|--------|-------|
| 1 | 1 | 1 | No | skip | [1, 1, 2, 3, 3, 4] |
| 2 | 2 | 1 | Yes | slow++, copy | [1, **2**, 2, 3, 3, 4] |
| 3 | 3 | 2 | Yes | slow++, copy | [1, 2, **3**, 3, 3, 4] |
| 4 | 3 | 3 | No | skip | [1, 2, 3, 3, 3, 4] |
| 5 | 4 | 3 | Yes | slow++, copy | [1, 2, 3, **4**, 3, 4] |

Return `slow + 1 = 4`. First 4 elements: `[1, 2, 3, 4]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
int removeDuplicates(int[] nums) {
    int slow = 0;
    for (int fast = 1; fast < nums.length; fast++)
        if (nums[fast] != nums[slow])
            nums[++slow] = nums[fast];
    return slow + 1;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[]` | `0` | Empty array |
| `[1]` | `1` | Single element — already unique |
| `[1, 1, 1]` | `1` | All duplicates |
| `[1, 2, 3]` | `3` | No duplicates — no changes |

---

## Related Problems

| Problem | Link |
|---------|------|
| Remove Duplicates from Sorted Array II | [LeetCode 80](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/) |
| Remove Element | [LeetCode 27](https://leetcode.com/problems/remove-element/) |
| Move Zeroes | [LeetCode 283](https://leetcode.com/problems/move-zeroes/) |
