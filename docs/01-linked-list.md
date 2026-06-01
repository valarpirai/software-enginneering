# Linked List

A chain of nodes. Each node holds a value and a pointer to the next node.

---

## Intuition

Arrays store items in a fixed block of memory. Linked lists scatter nodes anywhere in memory and connect them with pointers. You trade fast random access for cheap insert and delete at the head.

---

## Operations

| Operation | Time | Notes |
|-----------|------|-------|
| Access by index | O(n) | Must walk from head |
| Search | O(n) | Must walk from head |
| Insert at head | O(1) | Update one pointer |
| Insert at tail | O(n) | Must walk to end first |
| Delete at head | O(1) | Move head to next |
| Delete at middle | O(n) | Must find the node first |

---

## Sample Input

```
10 → 20 → 30 → NULL
```

This list is used in all examples below.

---

## Visual Representation

**Node structure:**

```mermaid
graph LR
    N1["data: 10 | next ●"] --> N2["data: 20 | next ●"] --> N3["data: 30 | next ●"] --> NUL(["NULL"])
    style NUL fill:#e8e8e8,stroke:#999
```

**Types of linked lists:**

```mermaid
graph LR
    subgraph Singly
        S1["10"] --> S2["20"] --> S3["30"] --> SN(["NULL"])
    end
    style SN fill:#e8e8e8,stroke:#999
```

```mermaid
graph LR
    subgraph Doubly
        D1["10"] <--> D2["20"] <--> D3["30"]
    end
```

```mermaid
graph LR
    subgraph Circular
        C1["10"] --> C2["20"] --> C3["30"] --> C1
    end
```

---

## Step-by-step Trace — Traversal

Input: `10 → 20 → 30 → NULL`

```java
// Time: O(n)  Space: O(1)
void traverse(Node head) {
    Node current = head;
    while (current != null) {
        System.out.println(current.data);
        current = current.next;
    }
}
```

| Step | current | current.data | Action |
|------|---------|--------------|--------|
| init | Node(10) | 10 | current = head |
| 1 | Node(10) | 10 | print 10 → advance to Node(20) |
| 2 | Node(20) | 20 | print 20 → advance to Node(30) |
| 3 | Node(30) | 30 | print 30 → advance to null |
| 4 | null | — | condition false → exit loop |

**Output:** `10  20  30`

**Traversal state — step by step:**

Initial:
```mermaid
graph LR
    HEAD(["HEAD"]) --> N1["10"] --> N2["20"] --> N3["30"] --> NUL(["NULL"])
    style HEAD fill:#4a90d9,color:#fff
    style NUL fill:#e8e8e8,stroke:#999
```

After visiting 10:
```mermaid
graph LR
    HEAD(["HEAD"]) --> N1["10"] --> N2["20"] --> N3["30"] --> NUL(["NULL"])
    CUR(["current"]) --> N2
    style HEAD fill:#4a90d9,color:#fff
    style CUR fill:#ff9900,color:#000
    style N1 fill:#82b366,color:#fff
    style N2 fill:#ff9900,color:#000
    style NUL fill:#e8e8e8,stroke:#999
```

After visiting 10, 20:
```mermaid
graph LR
    HEAD(["HEAD"]) --> N1["10"] --> N2["20"] --> N3["30"] --> NUL(["NULL"])
    CUR(["current"]) --> N3
    style HEAD fill:#4a90d9,color:#fff
    style CUR fill:#ff9900,color:#000
    style N1 fill:#82b366,color:#fff
    style N2 fill:#82b366,color:#fff
    style N3 fill:#ff9900,color:#000
    style NUL fill:#e8e8e8,stroke:#999
```

Done:
```mermaid
graph LR
    N1["10"] --> N2["20"] --> N3["30"] --> NUL(["NULL"])
    style N1 fill:#82b366,color:#fff
    style N2 fill:#82b366,color:#fff
    style N3 fill:#82b366,color:#fff
    style NUL fill:#e8e8e8,stroke:#999
```

---

## Java Implementation

### Node Class

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

### LinkedList Class

```java
class LinkedList {
    Node head;

    // Time: O(1)
    void addToFront(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    // Time: O(n)
    void addToEnd(int data) {
        Node node = new Node(data);
        if (head == null) { head = node; return; }
        Node current = head;
        while (current.next != null) current = current.next;
        current.next = node;
    }

    // Time: O(1)
    void deleteFromFront() {
        if (head != null) head = head.next;
    }

    // Time: O(n)
    void traverse() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    // Time: O(n)
    int size() {
        int count = 0;
        Node current = head;
        while (current != null) { count++; current = current.next; }
        return count;
    }
}
```

### Build the Sample List

```java
Node head = new Node(10);
head.next = new Node(20);
head.next.next = new Node(30);
// 10 → 20 → 30 → NULL
```

---

## Common Mistakes

- **Not checking `null` before accessing `.next`.** Always guard: `if (current != null)` before `current.next`.
- **Losing the head reference.** If you overwrite `head` without saving it, the whole list is gone.
- **Off-by-one in traversal.** Stop condition is `current != null`, not `current.next != null` (that skips the last node).
- **Forgetting to update `next` on insert.** When inserting between two nodes, link the new node to the next node *before* updating the previous node's pointer.
- **Confusing singly vs doubly.** Doubly linked list needs both `prev` and `next` updated on every insert and delete.

---

## Practice Problems

| Difficulty | Problem | Link |
|------------|---------|------|
| Easy | Reverse Linked List | [LeetCode 206](https://leetcode.com/problems/reverse-linked-list/) |
| Easy | Palindrome Linked List | [LeetCode 234](https://leetcode.com/problems/palindrome-linked-list/) |
| Medium | Add Two Numbers | [LeetCode 2](https://leetcode.com/problems/add-two-numbers/) |

---

## Deep Dive

### Array vs Linked List

| Operation | Array | Linked List |
|-----------|-------|-------------|
| Access by index | O(1) | O(n) |
| Insert at head | O(n) | O(1) |
| Insert at tail | O(1)* | O(n) |
| Delete at head | O(n) | O(1) |
| Memory layout | Contiguous | Scattered |
| Cache performance | Great | Poor |

*Amortized for dynamic arrays.

**Use a linked list when** you insert or delete at the head frequently and never need random access. Use an array when you need fast index-based access.

### Memory Layout

Each linked list node uses extra memory for the pointer field. On a 64-bit system a pointer is 8 bytes. A list of 1000 integers uses 1000 × (4 bytes data + 8 bytes pointer) = 12 KB, versus an array's 4 KB. The pointer overhead matters at scale.
