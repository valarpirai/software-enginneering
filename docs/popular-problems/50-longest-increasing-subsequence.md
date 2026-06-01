# Longest Increasing Subsequence

**Difficulty:** Medium | **LeetCode:** [#300](https://leetcode.com/problems/longest-increasing-subsequence/)

---

## Problem

Given an integer array, return the length of the longest strictly increasing subsequence (elements don't need to be contiguous).

---

## Intuition

For each element, the longest increasing subsequence ending at it is 1 plus the longest one ending at any smaller element before it. Track this for every position.

---

## Approach

**DP** — `dp[i]` = length of LIS ending at index `i`. For each `i`, look back at all `j < i` where `nums[j] < nums[i]`. `dp[i] = max(dp[j] + 1)`. Answer is `max(dp)`.

O(n²) DP or O(n log n) with patience sorting (binary search).

---

## Sample Input / Output

```
Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4   (subsequence [2, 3, 7, 18] or [2, 5, 7, 18])
```

---

## Step-by-step Trace

Input: `nums = [10, 9, 2, 5, 3, 7, 101, 18]`

| i | nums[i] | Look back at j where nums[j]<nums[i] | dp[i] |
|---|---------|--------------------------------------|-------|
| 0 | 10 | none | 1 |
| 1 | 9 | none (10>9) | 1 |
| 2 | 2 | none (10,9 > 2) | 1 |
| 3 | 5 | j=2(2<5): dp[2]+1=2 | 2 |
| 4 | 3 | j=2(2<3): dp[2]+1=2 | 2 |
| 5 | 7 | j=2,3,4: max(1+1,2+1,2+1)=3 | 3 |
| 6 | 101 | all j: max=3+1=4 | 4 |
| 7 | 18 | j=2,3,4,5: max=3+1=4 | 4 |

Return `max(dp) = 4` ✓

---

## Java Solution

```java
// DP — Time: O(n²)  Space: O(n)
int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++)
            if (nums[j] < nums[i])
                dp[i] = Math.max(dp[i], dp[j] + 1);
        max = Math.max(max, dp[i]);
    }
    return max;
}
```

**Binary search — O(n log n):**

```java
int lengthOfLIS(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (int n : nums) {
        int lo = 0, hi = tails.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (tails.get(mid) < n) lo = mid + 1;
            else hi = mid;
        }
        if (lo == tails.size()) tails.add(n);
        else tails.set(lo, n);
    }
    return tails.size();
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1]` | `1` | Single element |
| `[5,4,3,2,1]` | `1` | Strictly decreasing |
| `[1,2,3,4,5]` | `5` | Entire array is LIS |
| `[1,3,2,4]` | `3` | [1,2,4] or [1,3,4] |

---

## Related Problems

| Problem | Link |
|---------|------|
| Russian Doll Envelopes | [LeetCode 354](https://leetcode.com/problems/russian-doll-envelopes/) |
| Longest Common Subsequence | [LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) |
| Number of LIS | [LeetCode 673](https://leetcode.com/problems/number-of-longest-increasing-subsequence/) |
