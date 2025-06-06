package com.interview.hash.B_LruLfuPatterns;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/lru-cache/
 * Video: https://www.youtube.com/watch?v=z9bJUPxzFOw&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=18
 * Related: https://leetcode.com/problems/lfu-cache/ Hard VVImp
 * https://leetcode.com/problems/design-in-memory-file-system/ Hard Locked
 * https://leetcode.com/problems/design-compressed-string-iterator/ Easy Locked
 * https://leetcode.com/problems/design-most-recently-used-queue/  Medium Locked
 * Category: Medium, Must Do, Fundamental
 */
class A_LRUCache {
    public static class LRUCacheUsingLinkedHashMap {
        int capacity;
        Map<Integer, Integer> linkedHashMap;

    public LRUCacheUsingLinkedHashMap(int capacity) {
            this.capacity = capacity;
            this.linkedHashMap = new LinkedHashMap<>();

        }

        public int get(int key) {
            if (linkedHashMap.containsKey(key)) {
                int value = linkedHashMap.get(key);
                linkedHashMap.remove(key); //May in middle so remove it and
                linkedHashMap.put(key, value);//Put in last insertion order maintained
                return value;
            }
            return -1;

        }

        public void put(int key, int value) {
            if (linkedHashMap.containsKey(key)) {
                linkedHashMap.remove(key);

            } else {
                if (linkedHashMap.size() == capacity) {
                    //First key will be which is inserted first which we need to remove
                    for (int keyElm : linkedHashMap.keySet())  {
                        linkedHashMap.remove(keyElm);
                        break;
                    }

                }

            }
            linkedHashMap.put(key, value);

        }
    }
    public static class LRUCacheLinkedListApproach {
        Node head = new Node(0, 0), tail = new Node(0, 0);
        Map < Integer, Node > map = new HashMap();
        int capacity;

        public LRUCacheLinkedListApproach(int _capacity) {
            capacity = _capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                remove(node);
                insertAfterHead(node);
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                remove(map.get(key));
            }
            if (map.size() == capacity) {
                remove(tail.prev);
            }
            insertAfterHead(new Node(key, value));
        }

        private void remove(Node node) {
            map.remove(node.key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertAfterHead(Node node) {
            map.put(node.key, node);
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        class Node {
            Node prev, next;
            int key, value;
            Node(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }
    }

}
