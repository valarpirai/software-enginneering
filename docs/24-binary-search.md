# Binary Search

Find a target in a sorted array by halving the search space at each step.

---

## Intuition

Open a dictionary to the middle. If the word you want comes before that page, search the left half. If it comes after, search the right half. Repeat. Each step eliminates half the remaining pages.

---

## Operations

| Best | Average | Worst | Space |
|------|---------|-------|-------|
| O(1) | O(log n) | O(log n) | O(1) |

**Requirement:** The array must be sorted.

---

## Sample Input

```
arr = [1, 3, 5, 7, 9, 11, 13]
target = 9
```

---

## Visual Representation

```mermaid
graph TD
    S1["left=0, right=6, mid=3, arr[3]=7"] --> S2["7 < 9 → search right half"]
    S2 --> S3["left=4, right=6, mid=5, arr[5]=11"] --> S4["11 > 9 → search left half"]
    S4 --> S5["left=4, right=4, mid=4, arr[4]=9 → found!"]
    style S5 fill:#82b366,color:#fff
```

---

## Step-by-step Trace

Input: `arr = [1, 3, 5, 7, 9, 11, 13]`, target = 9

| Step | left | right | mid | arr[mid] | Compare | Action |
|------|------|-------|-----|----------|---------|--------|
| 1 | 0 | 6 | 3 | 7 | 7 < 9 | left = mid + 1 = 4 |
| 2 | 4 | 6 | 5 | 11 | 11 > 9 | right = mid - 1 = 4 |
| 3 | 4 | 4 | 4 | 9 | 9 == 9 | **return 4** |

3 comparisons instead of scanning all 7 elements.

---

## Java Implementation

### Iterative

```java
// Time: O(log n)  Space: O(1)
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target)  left  = mid + 1;
        else                    right = mid - 1;
    }
    return -1;
}
```

### Recursive

```java
// Time: O(log n)  Space: O(log n) — call stack
int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) return -1;
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) return mid;
    if (arr[mid] < target)  return binarySearch(arr, target, mid + 1, right);
    return binarySearch(arr, target, left, mid - 1);
}
```

### Variants

**Find first occurrence:**
```java
int findFirst(int[] arr, int target) {
    int left = 0, right = arr.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) { result = mid; right = mid - 1; }
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return result;
}
```

**Find last occurrence:**
```java
int findLast(int[] arr, int target) {
    int left = 0, right = arr.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) { result = mid; left = mid + 1; }
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return result;
}
```

**Search in rotated sorted array:**
```java
int searchRotated(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[left] <= arr[mid]) {
            if (target >= arr[left] && target < arr[mid]) right = mid - 1;
            else left = mid + 1;
        } else {
            if (target > arr[mid] && target <= arr[right]) left = mid + 1;
            else right = mid - 1;
        }
    }
    return -1;
}
```

---

## Common Mistakes

- **Using `(left + right) / 2`.** Overflows when `left + right > Integer.MAX_VALUE`. Always use `left + (right - left) / 2`.
- **Using `left < right` instead of `left <= right`.** With `<`, the loop exits before checking the last remaining element. Use `<=` for exact match.
- **Forgetting the array must be sorted.** Binary search gives wrong results on unsorted input without any error or exception.
- **Infinite loop when boundaries don't move.** After a match check, always move `left = mid + 1` or `right = mid - 1` — never set them to `mid` alone.

---

## Practice Problems

| Difficulty | Problem | Link |
|------------|---------|------|
| Easy | Binary Search | [LeetCode 704](https://leetcode.com/problems/binary-search/) |
| Medium | Find Minimum in Rotated Sorted Array | [LeetCode 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) |
| Medium | Search in Rotated Sorted Array | [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/) |

---

## Deep Dive

### Why O(log n)?

Each step cuts the search space in half. Starting with n elements: after 1 step → n/2, after 2 steps → n/4, after k steps → n/2^k. When n/2^k = 1, we're done. That gives k = log₂(n). For 1 billion elements, that's only 30 comparisons.

### Binary Search on the Answer

Binary search is not just for arrays. Any problem with a monotone property ("if X works, then X+1 works too") can be solved by binary searching on the answer space. Example: "what is the minimum speed to finish tasks in time?" — binary search on speed values.
