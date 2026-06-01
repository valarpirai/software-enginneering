# Sort Linked List

**Difficulty:** Medium | **LeetCode:** [Sort List #148](https://leetcode.com/problems/sort-list/)

---

## Problem

Given the head of a linked list, sort it in ascending order and return the sorted head. Must run in O(n log n) time and O(1) extra space.

---

## Intuition

Merge sort is ideal for linked lists. It doesn't need random access. Split the list in half, sort each half, then merge. Finding the middle uses fast/slow pointers. Merging two sorted lists takes O(n).

---

## Approach

**Merge Sort** — recursively split using the slow/fast pointer trick. Merge the two sorted halves. Total: O(n log n) time, O(log n) stack space (recursion depth).

---

## Sample Input / Output

```
Input:  4 → 2 → 1 → 3
Output: 1 → 2 → 3 → 4
```

---

## Step-by-step Trace

Input: `4 → 2 → 1 → 3`

```
Split:  [4 → 2]  and  [1 → 3]
Split:  [4] [2]        [1] [3]
Merge:  [2 → 4]        [1 → 3]
Merge:  [1 → 2 → 3 → 4]
```

| Merge step | Left | Right | Result |
|------------|------|-------|--------|
| compare | 2 | 1 | pick 1 |
| compare | 2 | 3 | pick 2 |
| compare | 4 | 3 | pick 3 |
| remaining | 4 | — | pick 4 |

Result: `1 → 2 → 3 → 4` ✓

---

## Java Solution

```java
// Time: O(n log n)  Space: O(log n) — recursion stack
ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode mid = getMiddle(head);
    ListNode right = mid.next;
    mid.next = null; // cut list in half
    return merge(sortList(head), sortList(right));
}

ListNode getMiddle(ListNode head) {
    ListNode slow = head, fast = head.next;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}

ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), curr = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) { curr.next = l1; l1 = l1.next; }
        else                  { curr.next = l2; l2 = l2.next; }
        curr = curr.next;
    }
    curr.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `null` | `null` | Empty list |
| Single node | Same node | Already sorted |
| `[2, 1]` | `[1, 2]` | Two nodes |
| All same values | Same list | No swaps needed |

---

## Related Problems

| Problem | Link |
|---------|------|
| Merge Two Sorted Lists | [LeetCode 21](https://leetcode.com/problems/merge-two-sorted-lists/) |
| Merge k Sorted Lists | [LeetCode 23](https://leetcode.com/problems/merge-k-sorted-lists/) |
| Insertion Sort List | [LeetCode 147](https://leetcode.com/problems/insertion-sort-list/) |
