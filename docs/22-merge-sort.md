# Merge Sort

Merge sort uses divide and conquer. It splits the array in half, sorts each half recursively, then merges the two sorted halves.

| Best       | Average    | Worst      | Space | Stable |
|------------|------------|------------|-------|--------|
| O(n log n) | O(n log n) | O(n log n) | O(n)  | Yes    |

---

## How It Works

Split until each piece has one element. One element is always sorted. Merge sorted pieces back together.

### Step-by-Step: Sort [38, 27, 43, 3]

```mermaid
graph TD
    A["[38, 27, 43, 3]"] --> B["[38, 27]"]
    A --> C["[43, 3]"]
    B --> D["[38]"]
    B --> E["[27]"]
    C --> F["[43]"]
    C --> G["[3]"]
    D --> H["[27, 38]  ← merge"]
    E --> H
    F --> I["[3, 43]  ← merge"]
    G --> I
    H --> J["[3, 27, 38, 43]  ← merge"]
    I --> J
    style D fill:#4a90d9,color:#fff
    style E fill:#4a90d9,color:#fff
    style F fill:#4a90d9,color:#fff
    style G fill:#4a90d9,color:#fff
    style H fill:#ff9900,color:#000
    style I fill:#ff9900,color:#000
    style J fill:#82b366,color:#fff
```

---

## Merge Step Detail

Merge `[27, 38]` and `[3, 43]` into `[3, 27, 38, 43]`:

```mermaid
graph LR
    subgraph "Compare heads, pick smaller"
    L["[27, 38]"]
    R["[3, 43]"]
    L --> C1["3 < 27 → pick 3"]
    R --> C1
    C1 --> C2["27 < 43 → pick 27"]
    C2 --> C3["38 < 43 → pick 38"]
    C3 --> C4["pick 43 → done"]
    C4 --> DONE["[3, 27, 38, 43]"]
    end
    style DONE fill:#82b366,color:#fff
```

---

## Java Implementation

```java
void mergeSort(int[] arr, int left, int right) {
    if (left >= right) return;
    int mid = left + (right - left) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
}

void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] L = new int[n1];
    int[] R = new int[n2];

    System.arraycopy(arr, left, L, 0, n1);
    System.arraycopy(arr, mid + 1, R, 0, n2);

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) arr[k++] = L[i++];
        else               arr[k++] = R[j++];
    }
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}

// Call: mergeSort(arr, 0, arr.length - 1);
```

---

## Advantages

- Always O(n log n) — no worst case degradation
- Stable — equal elements keep their original order
- Predictable — used in Java's `Arrays.sort` for objects (`TimSort`)

## Disadvantages

- Needs O(n) extra space for the temporary arrays
- Slower than Quick Sort in practice for small arrays due to overhead

---

## When to Use

- When stability matters (preserving relative order of equal elements)
- Sorting linked lists — merge sort works without random access
- External sorting — data too large to fit in memory
- When worst-case O(n log n) is required
