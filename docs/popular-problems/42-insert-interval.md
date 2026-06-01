# Insert Interval

**Difficulty:** Medium | **LeetCode:** [#57](https://leetcode.com/problems/insert-interval/)

---

## Problem

Given a sorted, non-overlapping list of intervals and a new interval, insert the new interval and merge any overlapping intervals. Return the result.

---

## Intuition

Three phases: (1) add all intervals that end before the new one starts, (2) merge all intervals that overlap with the new one by extending its boundaries, (3) add the remaining intervals.

---

## Approach

**Linear scan in three phases** — no sorting needed (input is already sorted). Walk through once: skip non-overlapping before, merge overlapping, append non-overlapping after.

---

## Sample Input / Output

```
Input:  intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
```

---

## Step-by-step Trace

Input: `intervals = [[1,3],[6,9]]`, `newInterval = [2,5]`

| i | interval | Phase | Action | newInterval |
|---|----------|-------|--------|-------------|
| 0 | [1,3] | overlap check: 3≥2 and 1≤5 → overlap | merge: [min(1,2), max(3,5)] = [1,5] | [1,5] |
| 1 | [6,9] | 6 > 5 → no overlap | add newInterval [1,5], then add [6,9] | done |

Output: `[[1,5],[6,9]]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(n)
int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0, n = intervals.length;

    // Phase 1: add all intervals ending before newInterval starts
    while (i < n && intervals[i][1] < newInterval[0])
        result.add(intervals[i++]);

    // Phase 2: merge overlapping intervals
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);

    // Phase 3: add remaining intervals
    while (i < n) result.add(intervals[i++]);

    return result.toArray(new int[0][]);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| Empty intervals | `[newInterval]` | Just insert |
| New interval before all | Prepend | Phase 1 skips nothing |
| New interval after all | Append | Phase 2 merges nothing |
| New interval overlaps all | One big merged interval | Entire list absorbed |

---

## Related Problems

| Problem | Link |
|---------|------|
| Merge Intervals | [LeetCode 56](https://leetcode.com/problems/merge-intervals/) |
| Meeting Rooms II | [LeetCode 253](https://leetcode.com/problems/meeting-rooms-ii/) |
| Non-overlapping Intervals | [LeetCode 435](https://leetcode.com/problems/non-overlapping-intervals/) |
