# Coin Change

**Difficulty:** Medium | **LeetCode:** [#322](https://leetcode.com/problems/coin-change/)

---

## Problem

Given coin denominations and a target amount, find the minimum number of coins needed to make that amount. Return `-1` if it's impossible.

---

## Intuition

For each amount from 1 to target, try every coin. If a coin fits, the number of coins for this amount is 1 plus the number of coins for `amount - coin`. Take the minimum across all valid coins.

---

## Approach

**1D Bottom-up DP** — `dp[i]` = minimum coins to make amount `i`. Initialize to `amount+1` (impossible sentinel). Base case: `dp[0] = 0`. Fill forward.

---

## Sample Input / Output

```
Input:  coins = [1, 5, 6, 9], amount = 11
Output: 2   (5 + 6)
```

---

## Step-by-step Trace

Input: `coins = [1, 5, 6, 9]`, `amount = 11`

| amount | dp[amount] | Best coin | Calculation |
|--------|-----------|-----------|-------------|
| 0 | 0 | — | base |
| 1 | 1 | 1 | dp[0]+1 |
| 5 | 1 | 5 | dp[0]+1 |
| 6 | 1 | 6 | dp[0]+1 |
| 9 | 1 | 9 | dp[0]+1 |
| 10 | 2 | 5 | dp[5]+1 or dp[4]+1(=3)... dp[5]+1=2 |
| 11 | **2** | 6 | dp[5]+1=2 or dp[6]+1=2 |

---

## Java Solution

```java
// Time: O(amount × coins)  Space: O(amount)
int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1); // sentinel: impossible
    dp[0] = 0;
    for (int i = 1; i <= amount; i++)
        for (int coin : coins)
            if (coin <= i)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
    return dp[amount] > amount ? -1 : dp[amount];
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `coins=[1], amount=0` | `0` | Nothing needed |
| `coins=[2], amount=3` | `-1` | Can't make odd amount with only coin 2 |
| `coins=[1,2,5], amount=11` | `3` | 5+5+1 |

---

## Related Problems

| Problem | Link |
|---------|------|
| Coin Change II (count ways) | [LeetCode 518](https://leetcode.com/problems/coin-change-ii/) |
| Perfect Squares | [LeetCode 279](https://leetcode.com/problems/perfect-squares/) |
| Minimum Cost For Tickets | [LeetCode 983](https://leetcode.com/problems/minimum-cost-for-tickets/) |
