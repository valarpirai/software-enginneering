# Find the Duplicate

**Difficulty:** Medium | **LeetCode:** [Find the Duplicate Number #287](https://leetcode.com/problems/find-the-duplicate-number/)

---

## Problem

Given an array of `n + 1` integers where each value is in `[1, n]`, exactly one number appears more than once. Find it. You must not modify the array and must use only O(1) extra space.

---

## Intuition

Treat the array as a linked list where `nums[i]` points to the next node. A duplicate means two different indices point to the same value — creating a cycle. Floyd's cycle detection finds where the cycle starts, which is the duplicate.

---

## Approach

**Floyd's Cycle Detection (Tortoise and Hare)** — fast pointer moves two steps, slow moves one. They meet inside the cycle. Then reset one pointer to the start and move both one step at a time. They meet at the cycle entrance — the duplicate.

---

## Sample Input / Output

```
Input:  nums = [1, 3, 4, 2, 2]
Output: 2
```

---

## Step-by-step Trace

Input: `nums = [1, 3, 4, 2, 2]`. Treat as: index 0→1→3→2→4→2→4→... (cycle at 2)

**Phase 1 — find meeting point:**

| Step | slow | fast |
|------|------|------|
| start | 0 | 0 |
| 1 | nums[0]=1 | nums[nums[0]]=nums[1]=3 |
| 2 | nums[1]=3 | nums[nums[3]]=nums[2]=4 |
| 3 | nums[3]=2 | nums[nums[4]]=nums[2]=4 |
| 4 | nums[2]=4 | nums[nums[4]]=nums[2]=4 |
| meet | 4 | 4 |

**Phase 2 — find cycle entrance (= duplicate):**

Reset slow to 0. Move both one step.

| Step | slow | fast |
|------|------|------|
| start | 0 | 4 |
| 1 | nums[0]=1 | nums[4]=2 |
| 2 | nums[1]=3 | nums[2]=4 |
| 3 | nums[3]=**2** | nums[4]=**2** |
| meet | 2 | 2 → **return 2** |

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
int findDuplicate(int[] nums) {
    int slow = nums[0], fast = nums[0];

    // Phase 1: find intersection
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow != fast);

    // Phase 2: find cycle entrance
    slow = nums[0];
    while (slow != fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    return slow;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1, 1]` | `1` | Smallest valid input |
| `[3, 1, 3, 4, 2]` | `3` | Duplicate not at end |
| `[2, 2, 2, 2, 2]` | `2` | All same value |

---

## Related Problems

| Problem | Link |
|---------|------|
| Linked List Cycle II | [LeetCode 142](https://leetcode.com/problems/linked-list-cycle-ii/) |
| Find All Duplicates in an Array | [LeetCode 442](https://leetcode.com/problems/find-all-duplicates-in-an-array/) |
| Missing Number | [LeetCode 268](https://leetcode.com/problems/missing-number/) |
