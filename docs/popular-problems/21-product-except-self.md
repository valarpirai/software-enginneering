# Product of Array Except Self

**Difficulty:** Medium | **LeetCode:** [#238](https://leetcode.com/problems/product-of-array-except-self/)

---

## Problem

Given an integer array, return an array where each element is the product of all other elements. You must not use division and must run in O(n).

---

## Intuition

For each index `i`, the answer is the product of everything to the left of `i` multiplied by the product of everything to the right. Compute left products in one forward pass, right products in one backward pass.

---

## Approach

**Two-pass prefix/suffix product** — first pass builds left products into the result array. Second pass multiplies in right products on the fly using a running `right` variable.

---

## Sample Input / Output

```
Input:  nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
  (result[0] = 2×3×4 = 24, result[1] = 1×3×4 = 12, etc.)
```

---

## Step-by-step Trace

Input: `nums = [1, 2, 3, 4]`

**Pass 1 — left products:**

| i | result[i] | (product of all elements left of i) |
|---|-----------|--------------------------------------|
| 0 | 1 | nothing to the left |
| 1 | 1 | nums[0] = 1 |
| 2 | 2 | nums[0]×nums[1] = 2 |
| 3 | 6 | nums[0]×nums[1]×nums[2] = 6 |

**Pass 2 — multiply right products:**

| i | right | result[i] = result[i] × right | right update |
|---|-------|-------------------------------|--------------|
| 3 | 1 | 6×1 = **6** | right = 1×4 = 4 |
| 2 | 4 | 2×4 = **8** | right = 4×3 = 12 |
| 1 | 12 | 1×12 = **12** | right = 12×2 = 24 |
| 0 | 24 | 1×24 = **24** | — |

Output: `[24, 12, 8, 6]` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1) — output array not counted
int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    // Pass 1: left products
    result[0] = 1;
    for (int i = 1; i < n; i++)
        result[i] = result[i-1] * nums[i-1];

    // Pass 2: multiply right products
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        result[i] *= right;
        right *= nums[i];
    }
    return result;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1, 0, 3]` | `[0, 3, 0]` | Zero in array — only the zero position gets a non-zero result |
| `[0, 0]` | `[0, 0]` | Two zeros — all products are zero |
| `[-1, 1, 0, -3, 3]` | `[0, 0, 9, 0, 0]` | Mix of negatives and zero |

---

## Related Problems

| Problem | Link |
|---------|------|
| Maximum Product Subarray | [LeetCode 152](https://leetcode.com/problems/maximum-product-subarray/) |
| Trapping Rain Water | [LeetCode 42](https://leetcode.com/problems/trapping-rain-water/) |
| Subarray Product Less Than K | [LeetCode 713](https://leetcode.com/problems/subarray-product-less-than-k/) |
