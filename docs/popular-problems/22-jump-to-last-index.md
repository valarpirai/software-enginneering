# Jump to Last Index

**Difficulty:** Medium | **LeetCode:** [Jump Game #55](https://leetcode.com/problems/jump-game/)

---

## Problem

Given an array where each element is your maximum jump length from that position, determine if you can reach the last index starting from index 0.

---

## Intuition

Track the farthest index you can reach. At each step, update it. If the current index ever exceeds the farthest reach, you're stuck.

---

## Approach

**Greedy** — keep a `maxReach` variable. For each index `i`, if `i > maxReach` you can't get there. Otherwise update `maxReach = max(maxReach, i + nums[i])`. If you finish the loop, return true.

---

## Sample Input / Output

```
Input:  nums = [2, 3, 1, 1, 4]
Output: true   (0→1→4 or 0→2→3→4)

Input:  nums = [3, 2, 1, 0, 4]
Output: false  (always stuck at index 3)
```

---

## Step-by-step Trace

Input: `nums = [3, 2, 1, 0, 4]`

| i | nums[i] | i > maxReach? | maxReach |
|---|---------|---------------|----------|
| 0 | 3 | 0 > 0? No | max(0, 0+3)=3 |
| 1 | 2 | 1 > 3? No | max(3, 1+2)=3 |
| 2 | 1 | 2 > 3? No | max(3, 2+1)=3 |
| 3 | 0 | 3 > 3? No | max(3, 3+0)=3 |
| 4 | 4 | **4 > 3? Yes → return false** | — |

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i > maxReach) return false;
        maxReach = Math.max(maxReach, i + nums[i]);
    }
    return true;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[0]` | `true` | Already at the last index |
| `[1, 0]` | `true` | One jump reaches the end |
| `[0, 1]` | `false` | Can't move from index 0 |
| `[2, 0, 0]` | `true` | Jump over zeros |

---

## Related Problems

| Problem | Link |
|---------|------|
| Jump Game II (min jumps) | [LeetCode 45](https://leetcode.com/problems/jump-game-ii/) |
| Jump Game III | [LeetCode 1306](https://leetcode.com/problems/jump-game-iii/) |
| Minimum Number of Taps to Open to Water a Garden | [LeetCode 1326](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/) |
