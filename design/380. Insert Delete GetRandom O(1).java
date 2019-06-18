/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/
/*
     hashmap + List
    .e.g: insert 1, 2, 3, 
     map: [1,0] [2, 1] [3,2]  list=[1,2,3]
     delete 2
     since indexof 2=1 endVal of list=3   list[1]=3 size-- => [1,3] map[3]=1 delete[2,1]
     delelte 3
     it is the last one, very easy to delete
    
*/

class RandomizedSet {
  Map<Integer, Integer> map  = new HashMap<>();
  List<Integer> arr = new ArrayList<Integer>();

  public RandomizedSet() {}
  
  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
      if(!map.containsKey(val)) {
          map.put(val, arr.size());
          arr.add(val);
          return true;
      }
      return false;
  }
  
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
      if(map.containsKey(val)) {
          int delete = map.get(val);
          if(delete<arr.size()-1) { //the value is not the last one in the list
              int endV = arr.get(arr.size()-1); // replace the end value to be in the delete place, update map
              arr.set(delete, endV);
              map.put(endV, delete);
          } 
          map.remove(val);
          arr.remove(arr.size()-1);
          return true;
      }
      return false;
  }
  
  /** Get a random element from the set. */
  public int getRandom() {
      int rand = (int)(Math.random()*arr.size());
      return arr.get(rand);
  }
}

/**
* Your RandomizedSet object will be instantiated and called as such:
* RandomizedSet obj = new RandomizedSet();
* boolean param_1 = obj.insert(val);
* boolean param_2 = obj.remove(val);
* int param_3 = obj.getRandom();
*/

