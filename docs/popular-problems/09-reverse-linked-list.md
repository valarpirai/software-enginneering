# Reverse Linked List

**Difficulty:** Easy | **LeetCode:** [#206](https://leetcode.com/problems/reverse-linked-list/)

---

## Problem

Given the head of a singly linked list, reverse it and return the new head.

---

## Intuition

Walk the list once. At each node, point its `next` back to the previous node instead of forward. Keep track of three pointers: previous, current, and next (to avoid losing the rest of the list).

---

## Approach

**Iterative with three pointers** — `prev` starts at null, `curr` starts at head. At each step: save `curr.next`, point `curr.next` back to `prev`, advance both pointers.

---

## Sample Input / Output

```
Input:  1 → 2 → 3 → 4 → 5 → NULL
Output: 5 → 4 → 3 → 2 → 1 → NULL
```

---

## Step-by-step Trace

Input: `1 → 2 → 3 → NULL`

| Step | prev | curr | next | Action |
|------|------|------|------|--------|
| init | null | 1 | — | — |
| 1 | null | 1 | 2 | 1.next = null, prev=1, curr=2 |
| 2 | 1 | 2 | 3 | 2.next = 1, prev=2, curr=3 |
| 3 | 2 | 3 | null | 3.next = 2, prev=3, curr=null |
| done | 3 | null | — | return prev = 3 |

Result: `3 → 2 → 1 → NULL` ✓

---

## Java Solution

```java
// Time: O(n)  Space: O(1)
ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

**Recursive alternative:**

```java
// Time: O(n)  Space: O(n) — call stack
ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `null` | Empty list |
| Single node | Same node | Nothing to reverse |
| Two nodes `1→2` | `2→1` | Basic swap |

---

## Related Problems

| Problem | Link |
|---------|------|
| Reverse Linked List II | [LeetCode 92](https://leetcode.com/problems/reverse-linked-list-ii/) |
| Palindrome Linked List | [LeetCode 234](https://leetcode.com/problems/palindrome-linked-list/) |
| Reverse Nodes in k-Group | [LeetCode 25](https://leetcode.com/problems/reverse-nodes-in-k-group/) |
