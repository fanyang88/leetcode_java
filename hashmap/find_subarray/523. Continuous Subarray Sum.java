/*
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

*/

/*
    
   
    We iterate through the input array exactly once, keeping track of the running sum mod k of the elements in the process. If we find that a running sum value at index j has been previously seen before in some earlier index i in the array, then we know that the sub-array (i,j] contains a desired sum.
    [23, 2, 4, 6, 7],  k=6
    map:  0: -1
    
    i=0: 23 % k = 5 map[5]=0
    i=1: 25 % k = 1 map[1]=1
i=2: 29 % k = 5 since map[5] exist, means there is a subarray (0~2] exist return true 
The key is to keep a running sum, and record the mod k in the map
*/

class Solution {
  public boolean checkSubarraySum(int[] nums, int k) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int sum=0;
      for(int i=0; i<nums.length; i++) {
          sum += nums[i];
          int val = sum;
          if(k !=0) val %= k;
          if(map.containsKey(val)) {
              if(i - map.get(val) > 1)  return true;
          } else {
              map.put(val, i);
          }
      }
      return false;
  }
}
