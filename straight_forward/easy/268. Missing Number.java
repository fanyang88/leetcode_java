/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
*/

// get diff between sum and (0+n)*(n+1)/2 

class Solution {
  public int missingNumber(int[] nums) {
      int sum=0, n=nums.length;
      for(int num: nums) {
          sum+= num;
      }
      return n*(n+1)/2 - sum;
  }
}
