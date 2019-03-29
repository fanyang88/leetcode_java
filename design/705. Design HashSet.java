/*

Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
*/

/*
    All values will be in the range of [0, 1000000].
    we can have 1000 bucket, each bucket can store up to 1000 values
    e.g: for 1000000, since 1000000%1000=0 1000000/1000=1000 
    we can store it in bucket[0][1000]
*/

class MyHashSet {
  int bucket = 1000;
  int itemsPerBucket = 1001;
  boolean[][] table;
  /** Initialize your data structure here. */
  public MyHashSet() {
      table= new boolean[1000][1001];
  }
  
  public void add(int key) {
      int hash = key % bucket;
      int pos = key / itemsPerBucket;
      table[hash][pos] = true;
  }
  
  public void remove(int key) {
      int hash = key % bucket;
      int pos = key / itemsPerBucket;
      table[hash][pos] = false;
  }
  
  /** Returns true if this set contains the specified element */
  public boolean contains(int key) {
      return table[key % bucket][key / itemsPerBucket];
  }
}

/**
* Your MyHashSet object will be instantiated and called as such:
* MyHashSet obj = new MyHashSet();
* obj.add(key);
* obj.remove(key);
* boolean param_3 = obj.contains(key);
*/
