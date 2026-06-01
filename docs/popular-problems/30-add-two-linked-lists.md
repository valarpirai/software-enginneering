# Add Two Linked Lists

**Difficulty:** Medium | **LeetCode:** [Add Two Numbers #2](https://leetcode.com/problems/add-two-numbers/)

---

## Problem

Two non-empty linked lists represent two non-negative integers stored in reverse order (ones digit first). Add the two numbers and return the result as a linked list in the same reverse order.

---

## Intuition

Simulate grade-school addition digit by digit. Both lists store digits in reverse order — so the first nodes are the ones place. Walk both lists together, sum the digits plus any carry, attach the result digit to the output, and carry forward the tens place.

---

## Approach

**Iterative with carry** — advance both pointers simultaneously. At each step: `sum = l1.val + l2.val + carry`. New digit is `sum % 10`. New carry is `sum / 10`. Use a dummy head node to simplify list building.

---

## Sample Input / Output

```
Input:  l1 = 2→4→3  (represents 342)
        l2 = 5→6→4  (represents 465)
Output: 7→0→8        (represents 807)
```

---

## Step-by-step Trace

Input: `l1 = 2→4→3`, `l2 = 5→6→4`, carry starts at 0

| Step | l1 | l2 | carry | sum | digit | new carry | Result so far |
|------|----|----|-------|-----|-------|-----------|---------------|
| 1 | 2 | 5 | 0 | 7 | 7 | 0 | 7 |
| 2 | 4 | 6 | 0 | 10 | 0 | 1 | 7→0 |
| 3 | 3 | 4 | 1 | 8 | 8 | 0 | 7→0→8 |

Return `7→0→8` ✓

---

## Java Solution

```java
// Time: O(max(m,n))  Space: O(max(m,n))
ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), curr = dummy;
    int carry = 0;
    while (l1 != null || l2 != null || carry != 0) {
        int sum = carry;
        if (l1 != null) { sum += l1.val; l1 = l1.next; }
        if (l2 != null) { sum += l2.val; l2 = l2.next; }
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
    }
    return dummy.next;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[5] + [5]` | `[0,1]` | Carry at the end |
| Lists of different lengths | Works | Short list treated as 0 after exhausted |
| `[9,9] + [1]` | `[0,0,1]` | Multiple carries |

---

## Related Problems

| Problem | Link |
|---------|------|
| Add Two Numbers II (forward order) | [LeetCode 445](https://leetcode.com/problems/add-two-numbers-ii/) |
| Sum of Two Integers | [LeetCode 371](https://leetcode.com/problems/sum-of-two-integers/) |
| Multiply Strings | [LeetCode 43](https://leetcode.com/problems/multiply-strings/) |
