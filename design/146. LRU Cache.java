/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */




/*
  We should define a double linkedlist which stores the nodes, initially should have a dummy head and tail
  We have a hashmap also to use the value as key to point to the actual node which is the value in map
  put the latest used node in the head, the least used whould be at the tail.
  The key is each node in double linked list should store key, and value
  the map should store the key(key) and node as value
*/
class LRUCache {
    final int capacity;
    final HashMap<Integer, Node> map;
    final Node head = new Node(0,0);
    final Node tail = new Node(0,0);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head.next= tail;
        this.tail.pre = head;
        map = new HashMap<>();
    }
    
    public void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void addToHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }
    
    public int get(int key) {
        int res= -1;
        if(map.containsKey(key)) {
            Node node = map.get(key);
            res = node.value;
            remove(node);
            addToHead(node);
        }
        return res;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToHead(node);
        } else {
            if(map.size() == this.capacity) {
                map.remove(this.tail.pre.key);
                remove(this.tail.pre);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
        }
    }

    class Node {
        int key, value;
        Node pre, next;
        public Node(int key, int value) {
            this.key = key;
            this.value=value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */