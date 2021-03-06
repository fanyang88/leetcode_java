/*
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. 
Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

*/

/*
  [3, 1, 4, 1, 5]
  map[3] = 1(freq)
  map[1] = 2
  map[4]=1
  map[5]=1
  
  for key in map
  since map[3+2]=map[5] exist count++
  since map[1+2]=map[3] exist count++;
  since map[4+2] not exist, continue
  since map[5+2] not exist, continue
  
  special case, when k=0, we check if map.get(key)>=2

*/

class Solution {
  public int findPairs(int[] nums, int k) {
      Map<Integer, Integer> map = new HashMap<>();
      int count=0;
      for(int num: nums) map.put(num, map.getOrDefault(num, 0) +1);
    
      for(int key: map.keySet()) {
          if(k==0 && map.get(key) >=2) {
              count++;
          } else if(k>0 && map.containsKey(key+k)) {
              count++;
          }
      }
      return count;
  }
}
