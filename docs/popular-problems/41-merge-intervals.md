# Merge Intervals

**Difficulty:** Medium | **LeetCode:** [#56](https://leetcode.com/problems/merge-intervals/)

---

## Problem

Given an array of intervals, merge all overlapping intervals and return the result.

---

## Intuition

Sort by start time. Walk through the sorted intervals. If the current interval overlaps with the last merged one (current start ≤ last end), extend the last merged interval. Otherwise, start a new one.

---

## Approach

**Sort + greedy merge** — sort by `start`. Maintain the last merged interval. Two intervals overlap if `current.start <= last.end`.

---

## Sample Input / Output

```
Input:  [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
```

---

## Step-by-step Trace

Input: already sorted `[[1,3],[2,6],[8,10],[15,18]]`

| Interval | Last merged | Overlap? | Action |
|----------|------------|---------|--------|
| [1,3] | — | — | add → [[1,3]] |
| [2,6] | [1,3] | 2≤3 Yes | extend end → [[1,6]] |
| [8,10] | [1,6] | 8≤6 No | new → [[1,6],[8,10]] |
| [15,18] | [8,10] | 15≤10 No | new → [[1,6],[8,10],[15,18]] |

---

## Java Solution

```java
// Time: O(n log n)  Space: O(n)
int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    List<int[]> result = new ArrayList<>();
    for (int[] interval : intervals) {
        if (result.isEmpty() || result.get(result.size()-1)[1] < interval[0])
            result.add(interval);
        else
            result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1], interval[1]);
    }
    return result.toArray(new int[0][]);
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[[1,4],[4,5]]` | `[[1,5]]` | Touching intervals merge (4==4) |
| `[[1,4],[2,3]]` | `[[1,4]]` | One fully contained in another |
| Single interval | Same | Nothing to merge |

---

## Related Problems

| Problem | Link |
|---------|------|
| Insert Interval | [LeetCode 57](https://leetcode.com/problems/insert-interval/) |
| Meeting Rooms | [LeetCode 252](https://leetcode.com/problems/meeting-rooms/) |
| Meeting Rooms II | [LeetCode 253](https://leetcode.com/problems/meeting-rooms-ii/) |
