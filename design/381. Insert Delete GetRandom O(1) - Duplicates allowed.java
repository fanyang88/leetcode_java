/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
*/

/*

     need hashMap + arraylist
           0 1 2 3 4
     e.g: [3,1,2,1,3] 
                val indexes
     hashmap:    3:[0,4]    1:[1,3]        2:[2]
     
     Arraylist: [3, 0]: first 3 in [0,4] is at 0 which has index 0
                [1, 0]  first 1 in [1,3] is at 1 which has index 0
                [2, 0]  first 2 in [2,0] is at 2 which has index 0
                [1, 1] second 1 in [1,3] is at 3 which has index 1
                [3, 1] second 3 in [0,4] is at 4 which has index 1
                
    to add a number 1
    hashmap change to 1:[1, 3,5] 5 is the size of arraylist
    list.add(1, map.get(1).size())=[1, 2]
    
    then delete 3:
    swap 3 with last element which is 1, swap[3,1] with [1,2] in arraylist and remove the last one
    in hashmap: 1. we need to remove the last index from map.get(3)
                2. we need to update the last index in map.get(1) to be[1,3,4]
*/


class RandomizedCollection {
    
  class Node {
      int val, index;
      Node(int val, int index) {
          this.val = val;
          this.index= index;
      }
  }

  HashMap<Integer, List<Integer>> map;
  List<Node> list;
  /** Initialize your data structure here. */
  public RandomizedCollection() {
      map = new HashMap<>();
      list = new ArrayList<>();
  }
  
  /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
  public boolean insert(int val) {
      boolean res = false;
      if(!map.containsKey(val)) {
          res =true;
          map.put(val, new ArrayList<>());
      }  
      int index = map.get(val).size();
      // update the hashmap 
      map.get(val).add(list.size());
      list.add(new Node(val, index));
      return res;
  }
  
  /** Removes a value from the collection. Returns true if the collection contained the specified element. */
  public boolean remove(int val) {
      if(map.get(val) == null)  return false;
      // swap the last node with current node
      int index = map.get(val).get(map.get(val).size()-1);  // to be delete node in where pos in list
      Node lastNode = list.get(list.size()-1);
      // replace to be delete node to be lastNode and delete lastNode safely
      list.set(index, lastNode);
      // change the index value in lastNode entry to be index
      map.get(lastNode.val).set(lastNode.index, index);
      // remove last element in list
      list.remove(list.size()-1);
      // remove the last entry in map.get(val)
      map.get(val).remove(map.get(val).size()-1);
      if(map.get(val).isEmpty()) map.remove(val);
      return true;
  }
  
  /** Get a random element from the collection. */
  public int getRandom() {
      int rand = (int)(Math.random()* list.size());
      return list.get(rand).val;
  }
}

/**
* Your RandomizedCollection object will be instantiated and called as such:
* RandomizedCollection obj = new RandomizedCollection();
* boolean param_1 = obj.insert(val);
* boolean param_2 = obj.remove(val);
* int param_3 = obj.getRandom();
*/
