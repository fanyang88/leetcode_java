/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 

Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
*/

/*
    Simply find out the three largest numbers and the two smallest numbers using one pass.
*/
class Solution {
  public int maximumProduct(int[] nums) {
      int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
      for(int n: nums) {
          if(max1 < n) {
              max3 = max2;
              max2= max1;
              max1 = n;
          } else if(max2 < n) {
              max3 = max2;
              max2 = n;
          } else if(max3 < n) {
              max3 = n;
          }
          
          if(min1 > n) {
              min2 = min1;
              min1 = n;
          } else if(min2 > n) {
              min2 = n;
          }
      }
      return Math.max(max1*max2*max3, max1*min2*min1);
  }
}
