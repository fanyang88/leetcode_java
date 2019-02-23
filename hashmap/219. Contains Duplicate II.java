/**
 * 
 * Given an array of integers and an integer k, 
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
 * and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

 */


/*
  sliding window, window size is k
  [1,2,3,1,2,3]  k=2
  i=0 set add 1
  i=1 set add 2
  i=2 set add 3  set={1,2,3}
  i=3 set remove 1 add 1 set={1,2,3}
  i=4 set remove 2 add 2 set={1,2,3}
  i=5 set remove 3 add 3 set={1,2,3}  return false no dup
  
*/
class Solution {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
      HashSet<Integer> set = new HashSet<>();
      for(int i=0; i<nums.length; i++) {
          if(i-k >0) { // if k=2, i=3 remove 0
              set.remove(nums[i-k-1]);  // The Key, not i-k
          }
          if(!set.add(nums[i])) return true;
      }
      return false;
  }
}
