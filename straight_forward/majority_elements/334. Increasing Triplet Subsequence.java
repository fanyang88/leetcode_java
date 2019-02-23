/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
*/

/*
    keep recording the smallest one the second smallest one, if there is a number larger than both, return true
    
    corner case: [1,1,1,1,1,1,1,1] = false
    i=0 min1 >=1 min1=1 
    i=1 min2>=1 min2=1
    i=2 min1>=1 min1=1 
    ....
    
    we should put >= not only > since above corner case
*/

class Solution {
  public boolean increasingTriplet(int[] nums) {
      int min1 = Integer.MAX_VALUE, min2= Integer.MAX_VALUE;
      for(int num: nums) {
          if(min1 >= num) {
              min1 = num;
          } else if(min2 >= num) {
              min2 = num;
          } else {
              return true;
          }
      }
      return false;
  }
}
