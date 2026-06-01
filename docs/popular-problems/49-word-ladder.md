# Word Ladder

**Difficulty:** Hard | **LeetCode:** [#127](https://leetcode.com/problems/word-ladder/)

---

## Problem

Given a `beginWord`, `endWord`, and a word list, find the length of the shortest transformation sequence from `beginWord` to `endWord`. Each step changes exactly one letter, and every intermediate word must be in the word list.

---

## Intuition

This is a shortest path problem on an implicit graph. Each word is a node. Two words are connected if they differ by one letter. BFS from `beginWord` finds the shortest path to `endWord`.

---

## Approach

**BFS** — start with `beginWord`. At each level, generate all one-letter variations. If a variation is in the word set and unvisited, add it to the queue. Count levels. Return the level count when `endWord` is found.

---

## Sample Input / Output

```
Input:  beginWord = "hit", endWord = "cog"
        wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Path:   hit → hot → dot → dog → cog
```

---

## Step-by-step Trace

Input: above

| Level | Queue | Words reached |
|-------|-------|--------------|
| 1 | [hit] | start |
| 2 | [hot] | hit→hot (h→h, i→o) |
| 3 | [dot, lot] | hot→dot, hot→lot |
| 4 | [dog, log] | dot→dog, lot→log |
| 5 | [**cog**] | dog→cog → **return 5** |

---

## Java Solution

```java
// Time: O(M²×N)  Space: O(M²×N) — M=word length, N=wordList size
int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    if (!wordSet.contains(endWord)) return 0;

    Queue<String> q = new ArrayDeque<>();
    q.offer(beginWord);
    wordSet.remove(beginWord);
    int steps = 1;

    while (!q.isEmpty()) {
        steps++;
        for (int size = q.size(); size > 0; size--) {
            char[] word = q.poll().toCharArray();
            for (int i = 0; i < word.length; i++) {
                char orig = word[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    word[i] = c;
                    String next = new String(word);
                    if (next.equals(endWord)) return steps;
                    if (wordSet.contains(next)) {
                        wordSet.remove(next);
                        q.offer(next);
                    }
                }
                word[i] = orig;
            }
        }
    }
    return 0;
}
```

---

## Edge Cases

| Input | Expected | Reason |
|-------|----------|--------|
| `endWord` not in list | `0` | No path possible |
| `beginWord == endWord` | `1` | Already there |
| No path exists | `0` | Words not connected |

---

## Related Problems

| Problem | Link |
|---------|------|
| Word Ladder II (all shortest paths) | [LeetCode 126](https://leetcode.com/problems/word-ladder-ii/) |
| Minimum Genetic Mutation | [LeetCode 433](https://leetcode.com/problems/minimum-genetic-mutation/) |
| Open the Lock | [LeetCode 752](https://leetcode.com/problems/open-the-lock/) |
