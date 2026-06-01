# Trie (Prefix Tree)

A trie stores strings character by character. Each path from root to a marked node forms a word. Ideal for prefix search and autocomplete.

---

## Structure

```mermaid
graph TD
    ROOT(["root"]) --> A["a"]
    ROOT --> C["c"]
    A --> AP["p"]
    AP --> APP["p  ✓ (app)"]
    APP --> APPL["l"]
    APPL --> APPLE["e  ✓ (apple)"]
    C --> CA["a"]
    CA --> CAT["t  ✓ (cat)"]
    CA --> CAR["r  ✓ (car)"]
    style ROOT fill:#4a90d9,color:#fff
    style APP fill:#82b366,color:#fff
    style APPLE fill:#82b366,color:#fff
    style CAT fill:#82b366,color:#fff
    style CAR fill:#82b366,color:#fff
```

Words stored: `app`, `apple`, `cat`, `car`. The ✓ marks end of a complete word.

---

## Operations

| Operation      | Time | Notes                           |
|----------------|------|---------------------------------|
| insert(word)   | O(m) | m = length of word              |
| search(word)   | O(m) | Exact match                     |
| startsWith(prefix) | O(m) | Prefix match               |
| delete(word)   | O(m) | Remove word, clean unused nodes |

---

## Java Implementation

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    private TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new TrieNode();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return node.isEnd;
    }

    boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return true;
    }
}
```

---

## Step-by-Step: Insert "cat"

```mermaid
graph LR
    subgraph "Insert c → a → t"
    R(["root"]) --> C["c"]
    C --> A["a"]
    A --> T["t  ✓"]
    end
    style T fill:#82b366,color:#fff
    style R fill:#4a90d9,color:#fff
```

---

## Step-by-Step: Search "car" in {cat, car}

```mermaid
graph TD
    R(["root"]) --> C["c  ← match"]
    C --> CA["a  ← match"]
    CA --> CAT["t  ✓"]
    CA --> CAR["r  ✓  ← found! isEnd=true"]
    style C fill:#ff9900,color:#000
    style CA fill:#ff9900,color:#000
    style CAR fill:#82b366,color:#fff
    style R fill:#4a90d9,color:#fff
```

---

## Trie vs HashMap for String Lookup

| Feature               | Trie           | HashMap              |
|-----------------------|----------------|----------------------|
| Exact search          | O(m)           | O(m) average         |
| Prefix search         | O(m)           | Not supported natively|
| Space                 | More (nodes)   | Less                 |
| Autocomplete          | Natural fit    | Requires extra work  |

---

## Common Use Cases

| Use Case                  | How Trie Helps                          |
|---------------------------|-----------------------------------------|
| Autocomplete              | All words that start with a prefix      |
| Spell checker             | Find closest matching word              |
| IP routing                | Longest prefix match                    |
| Word search in grid       | Build trie of words, DFS the grid       |
| Dictionary lookup         | Faster than scanning all words          |

---

## Autocomplete Example

```java
List<String> autocomplete(Trie trie, String prefix) {
    List<String> results = new ArrayList<>();
    TrieNode node = trie.root;
    for (char c : prefix.toCharArray()) {
        int i = c - 'a';
        if (node.children[i] == null) return results;
        node = node.children[i];
    }
    collectWords(node, new StringBuilder(prefix), results);
    return results;
}

void collectWords(TrieNode node, StringBuilder current, List<String> results) {
    if (node.isEnd) results.add(current.toString());
    for (int i = 0; i < 26; i++) {
        if (node.children[i] != null) {
            current.append((char) ('a' + i));
            collectWords(node.children[i], current, results);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
```
