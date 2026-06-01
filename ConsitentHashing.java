package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class ConsistentHashing {
    private final int numberOfReplicas;
    private final TreeMap<Long, String> ring;
    private final MessageDigest digest;

    public ConsistentHashing(List<String> nodes, int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
        this.ring = new TreeMap<>();
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
        if (nodes != null) {
            for (String node : nodes) {
                addNode(node);
            }
        }
    }

    private long hash(String key) {
        digest.reset();
        byte[] bytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
        // Convert first 8 bytes to long
        long hash = 0;
        for (int i = 0; i < 8; i++) {
            hash = (hash << 8) + (bytes[i] & 0xFF);
        }
        return hash;
    }

    public void addNode(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String replicaKey = node + ":" + i;
            ring.put(hash(replicaKey), node);
        }
    }

    public void removeNode(String node) {
        List<Long> keysToRemove = new ArrayList<>();
        for (var entry : ring.entrySet()) {
            if (entry.getValue().equals(node)) {
                keysToRemove.add(entry.getKey());
            }
        }
        for (Long key : keysToRemove) {
            ring.remove(key);
        }
    }

    public String getNode(String key) {
        if (ring.isEmpty()) {
            throw new IllegalStateException("No nodes in the hash ring");
        }
        long hashKey = hash(key);
        var entry = ring.tailMap(hashKey).firstKey();
        if (entry == null) {
            return ring.firstEntry().getValue();
        }
        return ring.get(entry);
    }

    // Example usage
    public static void main(String[] args) {
        List<String> nodes = List.of("node1", "node2", "node3", "node4");
        ConsistentHashing ch = new ConsistentHashing(nodes, 1);

        // Test key mapping
        String[] testKeys = {"key1", "key2", "key3", "key4"};
        for (String key : testKeys) {
            String node = ch.getNode(key);
            System.out.println("Key " + key + " maps to " + node);
        }

        // Add a new node
        System.out.println("\nAdding node4...");
        ch.addNode("node4");

        // Test key mapping again
        for (String key : testKeys) {
            String node = ch.getNode(key);
            System.out.println("Key " + key + " maps to " + node);
        }

        // Remove a node
        System.out.println("\nRemoving node2...");
        ch.removeNode("node2");

        // Test key mapping after removal
        for (String key : testKeys) {
            String node = ch.getNode(key);
            System.out.println("Key " + key + " maps to " + node);
        }
    }
}
