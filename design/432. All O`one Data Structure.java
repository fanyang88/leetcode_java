/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
*/

/*
    "A": 4, "B": 4, "C": 2, "D": 1
Then one possible matrix may look like this:

row0: val = 4, strs = {"A", "B"}
row1: val = 2, strs = {"C"}
row2: val = 1, strs = {"D"}

For example, after Inc("D"), the matrix will become

row0: val = 4, strs = {"A", "B"}
row1: val = 2, strs = {"C", "D"}
Inc("D") again

row0: val = 4, strs = {"A", "B"}
row1: val = 3, strs = {"D"}
row2: val = 2, strs = {"C"}

    List: each node has val = freq, set= {keys realted}
    map: key as key, value as which node this key is at
    
    for inc: if the key not in the list, 
                if the first node is not 1, create a node with val=1 and put key in it, conect to the head
                otherwise, direct put the key in the first node
            otherwise, if the key is in the list, get current node first
                if next node of current has value == i+1, directly add it to next node
                if next node of current not has value == i+1, create a new node and add it before next
                update map, also remove the kye from current node, if current node is Empty afterwards,
                we should delete current node
    for dec: if the key not in the map,  do nothing
             otherwise, if prev node of current has value == i-1, directly add it to next node
                if prev node of current not has value == i-1, create a new node and add it after prev
                update map, also remove the kye from current node, if current node is Empty afterwards,
                we should delete current node
    
*/

class ListNode {
  int val;
  Set<String> set;
  ListNode next;
  ListNode prev;
  ListNode(int val) {
      this.val = val;
      set = new HashSet<>();
      next = null;
      prev = null;
  }
}

class AllOne {
  Map<String, ListNode> map;
  ListNode head;
  ListNode tail;
  /** Initialize your data structure here. */
  public AllOne() {
      map = new HashMap<>();
      head = new ListNode(Integer.MIN_VALUE);
      tail = new ListNode(Integer.MAX_VALUE);
      head.next= tail;
      tail.prev= head;
  }
  
  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
      if(!map.containsKey(key)) {
          if(head.next.val == 1) {
              head.next.set.add(key);
              map.put(key, head.next);
          } else {
              insertNode(head, head.next, 1, key);
              map.put(key, head.next);
          }
      } else {
          ListNode cur = map.get(key);
          if(cur.next.val == cur.val+1) {
              cur.next.set.add(key);
              map.put(key, cur.next);
          } else {
              insertNode(cur, cur.next, cur.val+1, key);
              map.put(key, cur.next);
          }
          removeKey(cur, key);
      }
  }
  
  public void insertNode(ListNode pre, ListNode next, int val, String key) {
      ListNode node = new ListNode(val);
      node.set.add(key);
      pre.next = node;
      node.prev = pre;
      node.next = next;
      next.prev = node;
  }
  
   public void removeKey(ListNode cur, String key) {
      cur.set.remove(key);
      if(cur.set.isEmpty()) {
          ListNode pre = cur.prev;
          ListNode next = cur.next;
          next.prev = pre;
          pre.next = next;
      }
  }
  
  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
      if(!map.containsKey(key))  return;
      ListNode cur = map.get(key);
      if(cur.val >1) {  // This is the Key
          // check prev
          ListNode pre = cur.prev;
          if(pre.val == cur.val-1) {
              pre.set.add(key);
              map.put(key, pre);
          } else {
              insertNode(pre, cur, cur.val-1, key);
              map.put(key, pre.next);
          }
      } else {
          map.remove(key);
      }
      
      // remove key from current node set
      removeKey(cur, key);
  }
  
  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
      return tail.prev == head ? "" : tail.prev.set.iterator().next();
  }
  
  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
      return head.next == tail ? "" : head.next.set.iterator().next();
  }
}

/**
* Your AllOne object will be instantiated and called as such:
* AllOne obj = new AllOne();
* obj.inc(key);
* obj.dec(key);
* String param_3 = obj.getMaxKey();
* String param_4 = obj.getMinKey();
*/
