# Palindrome Linked List

**Difficulty:** Easy | **LeetCode:** [#234](https://leetcode.com/problems/palindrome-linked-list/)

---

## Problem

Given the head of a singly linked list, return true if it is a palindrome.

---

## Intuition

Find the middle of the list. Reverse the second half. Compare it with the first half node by node. If all match, it is a palindrome.

---

## Approach

Three steps: (1) find middle with fast/slow pointers, (2) reverse the second half in place, (3) compare both halves.

---

## Sample Input / Output

```
Input:  1 → 2 → 2 → 1
Output: true

Input:  1 → 2 → 3
Output: false
```

---

## Step-by-step Trace

Input: `1 → 2 → 2 → 1`

**Step 1 — find middle:**

| slow | fast |
|------|------|
| 1 | 1 |
| 2 | 2 (second) |
| 2 (third) | null |

Middle = node with value `2` (third node).

**Step 2 — reverse second half:** `2 → 1` becomes `1 → 2`

**Step 3 — compare:**

| left | right | Match? |
|------|-------|--------|
| 1 | 1 | Yes |
| 2 | 2 | Yes |

Return `true` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
boolean isPalindrome(ListNode head) {
    // Step 1: find middle
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Step 2: reverse second half
    ListNode prev = null, curr = slow;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    // Step 3: compare
    ListNode left = head, right = prev;
    while (right != null) {
        if (left.val != right.val) return false;
        left  = left.next;
        right = right.next;
    }
    return true;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `[1]` | `true` | Single node |
| `[1, 1]` | `true` | Two same nodes |
| `[1, 2]` | `false` | Two different nodes |
| `[1, 2, 1]` | `true` | Odd length palindrome |

---

## Related Problems

| Problem | Link |
|---------|------|
| Reverse Linked List | [LeetCode 206](https://leetcode.com/problems/reverse-linked-list/) |
| Valid Palindrome | [LeetCode 125](https://leetcode.com/problems/valid-palindrome/) |
| Middle of the Linked List | [LeetCode 876](https://leetcode.com/problems/middle-of-the-linked-list/) |
