/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false
 */

class TwoSum {

  /** Initialize your data structure here. */
  private HashMap<Integer, Integer> map;
  public TwoSum() {
       map = new HashMap<Integer, Integer>();
  }
  
  /** Add the number to an internal data structure.. */
  public void add(int number) {
      map.put(number, map.getOrDefault(number, 0) +1);
  }
  
  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
      for(int key: map.keySet()) {
          int key2 = value - key;
          if(key2 == key && map.get(key) > 1)  return true;
          if(key2 != key && map.containsKey(key2)) return true;
      }
      return false;
  }
}

/**
* Your TwoSum object will be instantiated and called as such:
* TwoSum obj = new TwoSum();
* obj.add(number);
* boolean param_2 = obj.find(value);
*/
