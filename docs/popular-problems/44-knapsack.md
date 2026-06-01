# 0-1 Knapsack

**Difficulty:** Medium | **LeetCode:** [Partition Equal Subset Sum #416](https://leetcode.com/problems/partition-equal-subset-sum/)

---

## Problem

Given `n` items with weights and values, and a knapsack of capacity `W`, find the maximum value you can carry. Each item can be used at most once (0-1: either take it or don't).

---

## Intuition

For each item, decide: take it or skip it. Taking it reduces remaining capacity and adds its value. The answer at each capacity is the better of taking or skipping. Build bottom-up from smaller capacities.

---

## Approach

**2D DP** — `dp[i][w]` = max value using first `i` items with capacity `w`.
- Skip item i: `dp[i][w] = dp[i-1][w]`
- Take item i (if it fits): `dp[i][w] = dp[i-1][w - weight[i]] + value[i]`
- Take the max of both options.

---

## Sample Input / Output

```
weights = [1, 2, 3]
values  = [6, 10, 12]
W = 5

Output: 22   (take items 1 and 3: weight=1+3=4, value=6+12=18... wait)
       Actually: take items 2 and 3: weight=2+3=5, value=10+12=22 ✓
```

---

## Step-by-step Trace

Input: `weights=[1,2,3]`, `values=[6,10,12]`, `W=5`

| item\capacity | 0 | 1 | 2 | 3 | 4 | 5 |
|---|---|---|---|---|---|---|
| none | 0 | 0 | 0 | 0 | 0 | 0 |
| item1 (w=1,v=6) | 0 | 6 | 6 | 6 | 6 | 6 |
| item2 (w=2,v=10) | 0 | 6 | 10 | 16 | 16 | 16 |
| item3 (w=3,v=12) | 0 | 6 | 10 | 16 | 18 | **22** |

Return `dp[3][5] = 22` ✓

---

## Java Solution

```java
// Time: O(n×W)  Space: O(n×W)
int knapsack(int[] weights, int[] values, int W) {
    int n = weights.length;
    int[][] dp = new int[n+1][W+1];
    for (int i = 1; i <= n; i++)
        for (int w = 0; w <= W; w++) {
            dp[i][w] = dp[i-1][w]; // skip item i
            if (weights[i-1] <= w)
                dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        }
    return dp[n][W];
}
```

**Space-optimized (1D DP):**

```java
// Time: O(n×W)  Space: O(W)
int knapsack(int[] weights, int[] values, int W) {
    int[] dp = new int[W+1];
    for (int i = 0; i < weights.length; i++)
        for (int w = W; w >= weights[i]; w--) // iterate backwards to avoid reuse
            dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
    return dp[W];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| W=0 | `0` | No capacity |
| All items too heavy | `0` | None fit |
| W ≥ total weight | Sum of all values | Take everything |

---

## Related Problems

| Problem | Link |
|---------|------|
| Partition Equal Subset Sum | [LeetCode 416](https://leetcode.com/problems/partition-equal-subset-sum/) |
| Coin Change | [LeetCode 322](https://leetcode.com/problems/coin-change/) |
| Target Sum | [LeetCode 494](https://leetcode.com/problems/target-sum/) |
