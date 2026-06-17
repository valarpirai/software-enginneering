# Popular Problems

Solve them in order. Each builds on the last.

---

## How to Choose the Right Data Structure

Read the problem. Look for these clues.

### You need fast lookup by key → Hash Map / Hash Set

Use a Hash Map when you need to look up values by key in O(1). Use a Hash Set when you only need to know if something exists.

**Signals:** "find pair", "count frequency", "two sum", "detect duplicate", "group by"

### You need to process items in order → Queue

A Queue processes items first-in, first-out. Use it for level-by-level traversal (BFS).

**Signals:** "level order", "shortest path", "word ladder", "nearest node"

### You need to track the last seen item → Stack

A Stack processes items last-in, first-out. Use it when you need to undo, match pairs, or track a running state.

**Signals:** "valid brackets", "next greater element", "undo", "nested structure"

### You need the min or max repeatedly → Heap

A Heap gives you the min (or max) in O(1) and inserts in O(log n). Use it when you need the top K items or a running median.

**Signals:** "k largest", "k smallest", "top k", "running median", "merge k sorted"

### You need fast prefix lookups on strings → Trie

A Trie stores strings character by character. Lookup and insert are O(L) where L is the string length.

**Signals:** "autocomplete", "starts with", "word search in dictionary", "count words with prefix"

### You need to explore connections → Graph + BFS/DFS

Model the problem as nodes and edges. Use BFS for shortest path (unweighted). Use DFS for all paths or cycle detection.

**Signals:** "islands", "connected components", "shortest path", "can reach", "dependency order"

### You need to search a sorted input fast → Binary Search

If the input is sorted (or the answer space is monotonic), binary search finds the answer in O(log n).

**Signals:** "sorted array", "find target", "minimum valid answer", "peak element"

### You need to shrink or grow a window → Sliding Window / Two Pointers

Sliding Window maintains a contiguous subarray. Two Pointers move from both ends (or at different speeds).

**Signals:** "longest subarray", "shortest subarray with sum ≥ k", "no repeating characters", "palindrome check"

### You have overlapping subproblems → Dynamic Programming

If the problem can be broken into subproblems and the same subproblems repeat, use DP to cache results.

**Signals:** "count ways", "minimum cost", "longest subsequence", "can you reach", "knapsack"

### You need to explore all possibilities → Backtracking

Try every option. If it fails, undo and try the next. Use recursion with a state you can restore.

**Signals:** "all subsets", "all permutations", "find all paths", "N-queens", "word search"

---

## Quick Reference

| Clue in the problem | Try this |
|---------------------|----------|
| Fast lookup / detect duplicate | Hash Map, Hash Set |
| Level-by-level / shortest path | BFS, Queue |
| Matching pairs / nested structure | Stack |
| Top K / running min or max | Heap |
| String prefix / autocomplete | Trie |
| Nodes and connections | Graph + BFS/DFS |
| Sorted input / answer space | Binary Search |
| Contiguous subarray | Sliding Window |
| Both ends of an array | Two Pointers |
| Overlapping subproblems | Dynamic Programming |
| Explore all possibilities | Backtracking |

---

## Problem Solving Tips

### 1. Use a Heap for K Elements

Need the top K largest or smallest? Use a Heap. Insert and remove cost O(log K). Scanning the whole array costs O(n log K) — far better than sorting.

**Example:** Find the 3 largest numbers in an array.

### 2. Binary Search or Two Pointers for Sorted Inputs

Sorted array? Think Binary Search or Two Pointers first. Binary Search finds a target in O(log n). Two Pointers scan from both ends in O(n).

**Example:** Find two numbers in a sorted array that sum to a target.

### 3. Backtracking or BFS for Combinations

Exploring all subsets or permutations? Use Backtracking to build candidates and prune dead ends. Use BFS when you need the shortest combination.

**Example:** Generate all subsets of a set.

### 4. BFS or DFS for Trees and Graphs

BFS finds the shortest path. DFS explores all paths. Use BFS for level-order traversal. Use DFS when you need to go deep before backtracking.

**Example:** Find the shortest path between two nodes in a graph.

### 5. Convert Recursion to Iteration with a Stack

Any recursive algorithm can run iteratively with an explicit stack. This prevents stack overflow on deep inputs and gives you direct control over memory.

**Example:** Iterative in-order traversal of a binary tree.

### 6. Optimize Arrays with Hash Maps or Sorting

Nested loops are O(n²). Replace them. A Hash Map cuts lookup to O(1) for an O(n) total. Sorting costs O(n log n) but enables two pointers or binary search afterward.

**Example:** Find duplicates in an array.

### 7. Use Dynamic Programming for Optimization Problems

DP works when a problem has overlapping subproblems and optimal substructure. Cache each subproblem result. Solve it once. Reuse the answer.

**Example:** Solve the 0/1 knapsack problem.

### 8. Hash Map or Trie for String Patterns

Hash Maps speed up substring lookups and frequency counts. Tries handle prefix matching and autocomplete in O(L) per query where L is the string length.

**Example:** Find the longest common prefix among multiple strings.

### 9. Fast and Slow Pointers for Linked Lists

Two pointers at different speeds detect cycles and find midpoints without extra memory. The fast pointer moves two steps; the slow pointer moves one. They meet at the cycle.

**Example:** Detect if a linked list has a loop.

### 10. Recognize DP by These Signals

Look for these phrases: "minimum cost", "maximum profit", "count ways to", "can you reach", "longest subsequence". If you see them, try DP before anything else.

**Example:** Count the number of ways to climb n stairs.

---

| # | Problem | Pattern | Difficulty |
|---|---------|---------|------------|
| 1 | [Find pair that sums up to k](popular-problems/01-find-pair-that-sums-to-k.md) | Hash Map, Two Pointers | Easy |
| 2 | [First repeating character](popular-problems/02-first-repeating-character.md) | Hash Map | Easy |
| 3 | [Remove duplicates](popular-problems/03-remove-duplicates.md) | Array, Two Pointers | Easy |
| 4 | [Find the duplicate](popular-problems/04-find-the-duplicate.md) | Array, Two Pointers | Medium |
| 5 | [Tree depth first search](popular-problems/05-tree-dfs.md) | Tree, DFS, Recursion | Easy |
| 6 | [Maximum subarray](popular-problems/06-maximum-subarray.md) | Dynamic Programming, Greedy | Medium |
| 7 | [Reverse binary tree](popular-problems/07-reverse-binary-tree.md) | Tree, Recursion | Easy |
| 8 | [Longest substring without repeating characters](popular-problems/08-longest-substring-without-repeating.md) | Sliding Window, Hash Map | Medium |
| 9 | [Reverse linked list](popular-problems/09-reverse-linked-list.md) | Linked List, Two Pointers | Easy |
| 10 | [Peak finding](popular-problems/10-peak-finding.md) | Binary Search | Medium |
| 11 | [Palindrome linked list](popular-problems/11-palindrome-linked-list.md) | Linked List, Two Pointers | Easy |
| 12 | [Longest possible palindrome](popular-problems/12-longest-palindrome.md) | String, Dynamic Programming | Medium |
| 13 | [Get substring index](popular-problems/13-get-substring-index.md) | String | Easy |
| 14 | [Tree breadth first search](popular-problems/14-tree-bfs.md) | Tree, BFS, Queue | Medium |
| 15 | [Sort linked list](popular-problems/15-sort-linked-list.md) | Linked List, Merge Sort | Medium |
| 16 | [Valid binary search tree](popular-problems/16-valid-bst.md) | BST, DFS, Recursion | Medium |
| 17 | [Minimum cost path in matrix](popular-problems/17-minimum-cost-path.md) | Dynamic Programming, Matrix | Medium |
| 18 | [Balanced binary tree](popular-problems/18-balanced-binary-tree.md) | Tree, DFS, Recursion | Easy |
| 19 | [Paths in matrix](popular-problems/19-paths-in-matrix.md) | Dynamic Programming, Matrix | Medium |
| 20 | [Tree breadth first search II](popular-problems/20-tree-bfs-2.md) | Tree, BFS, Queue | Medium |
| 21 | [Product of array except self](popular-problems/21-product-except-self.md) | Array, Prefix Sum | Medium |
| 22 | [Jump to last index](popular-problems/22-jump-to-last-index.md) | Greedy | Medium |
| 23 | [Graph depth first search](popular-problems/23-graph-dfs.md) | Graph, DFS | Medium |
| 24 | [Graph breadth first search](popular-problems/24-graph-bfs.md) | Graph, BFS | Medium |
| 25 | [String subsequences](popular-problems/25-string-subsequences.md) | Backtracking, Recursion | Medium |
| 26 | [Valid brackets](popular-problems/26-valid-brackets.md) | Stack | Easy |
| 26a | [Min Stack](03a-min-stack.md) | Stack | Medium |
| 27 | [Flatten binary tree](popular-problems/27-flatten-binary-tree.md) | Tree, DFS, Recursion | Medium |
| 28 | [Lowest common ancestor](popular-problems/28-lowest-common-ancestor.md) | Tree, DFS, Recursion | Medium |
| 29 | [Minimum in rotated sorted array](popular-problems/29-minimum-rotated-sorted-array.md) | Binary Search | Medium |
| 30 | [Add two linked lists](popular-problems/30-add-two-linked-lists.md) | Linked List, Math | Medium |
| 31 | [Ways to climb stairs](popular-problems/31-ways-to-climb-stairs.md) | Dynamic Programming, Recursion | Easy |
| 32 | [Subsets that sum up to k](popular-problems/32-subsets-sum-to-k.md) | Backtracking, Recursion | Medium |
| 33 | [Ways to decode](popular-problems/33-ways-to-decode.md) | Dynamic Programming | Medium |
| 34 | [Remove node from binary search tree](popular-problems/34-remove-node-bst.md) | BST, Recursion | Medium |
| 35 | [Array permutations](popular-problems/35-array-permutations.md) | Backtracking, Recursion | Medium |
| 36 | [Longest common subsequence](popular-problems/36-longest-common-subsequence.md) | Dynamic Programming | Medium |
| 37 | [Longest consecutive sequence](popular-problems/37-longest-consecutive-sequence.md) | Hash Set | Medium |
| 38 | [Edit distance](popular-problems/38-edit-distance.md) | Dynamic Programming | Hard |
| 39 | [Count sorted vowel strings](popular-problems/39-count-sorted-vowel-strings.md) | Dynamic Programming, Combinatorics | Medium |
| 40 | [Smallest number after removing k digits](popular-problems/40-remove-k-digits.md) | Stack, Greedy | Medium |
| 41 | [Merge intervals](popular-problems/41-merge-intervals.md) | Array, Sorting | Medium |
| 42 | [Insert interval](popular-problems/42-insert-interval.md) | Array, Binary Search | Medium |
| 43 | [Maximum path sum](popular-problems/43-maximum-path-sum.md) | Tree, DFS, Recursion | Hard |
| 44 | [0-1 Knapsack](popular-problems/44-knapsack.md) | Dynamic Programming | Medium |
| 45 | [Shortest palindrome](popular-problems/45-shortest-palindrome.md) | String | Hard |
| 46 | [Coin change](popular-problems/46-coin-change.md) | Dynamic Programming | Medium |
| 47 | [Word search](popular-problems/47-word-search.md) | Backtracking, Matrix, DFS | Medium |
| 48 | [N-queens](popular-problems/48-n-queens.md) | Backtracking, Recursion | Hard |
| 49 | [Word ladder](popular-problems/49-word-ladder.md) | Graph, BFS | Hard |
| 50 | [Longest increasing subsequence](popular-problems/50-longest-increasing-subsequence.md) | Dynamic Programming, Binary Search | Medium |
