/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

/*
    capacity is 2
        opertion          LFU{key, value, freq}
    cache.put(1, 1);       {1,1,1}
    cache.put(2, 2);       {2,2,1}{1,1,1}
    cache.get(1);          {1,1,2}{2,2,1}
    cache.put(3, 3);       {1,1,2}{3,3,1}   //remove{2,2,1}
    cache.get(2);          -1
    cache.get(3);          {3,3,2}{1,1,2}
    cache.put(4, 4);       {3,3,2}{4,4,1}
    cache.get(1);          -1
    cache.get(3);          {3,3,3}{4,4,1}
    cache.get(4);          {3,3,3}{4,4,2}
*/



class LFUCache {
     
  Map<Integer, Integer> vals = new HashMap<>();
  Map<Integer, Integer> counts = new HashMap<>();
  Map<Integer, LinkedHashSet<Integer>> map = new HashMap<>();
  int min, cap;
  public LFUCache(int capacity) {
      cap = capacity;
      min=-1;
      map.put(1, new LinkedHashSet<>());
  }
  
  public int get(int key) {
      if(!vals.containsKey(key)) return -1;
      // update counts
      int c = counts.get(key);
      counts.put(key, c+1);
      // update map
      map.get(c).remove(key);
      if(c==min && map.get(c).size()==0) min++; // This is the key!!!
      if(!map.containsKey(c+1)) map.put(c+1, new LinkedHashSet<>());
      map.get(c+1).add(key);
      return vals.get(key);
  }
  
  public void put(int key, int value) {
      if(cap <=0) return;
      if(vals.containsKey(key)) { // this key already exist
          // update in vals
          vals.put(key, value);
          // update in counts, map
          get(key);
          return;
      } 
      // if not exist before, check capacity first
      if(vals.size() == cap) { // remove the min
          int delete = map.get(min).iterator().next();
          map.get(min).remove(delete);
          vals.remove(delete);
          counts.remove(delete);
      }
      min=1;
      map.get(1).add(key);
      vals.put(key, value);
      counts.put(key, 1);
  }
}

/**
* Your LFUCache object will be instantiated and called as such:
* LFUCache obj = new LFUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/


