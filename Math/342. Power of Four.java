/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
*/

class Solution {
  public boolean isPowerOfFour(int num) {
      if(num <=0)  return false;
      int x = (int)Math.round(Math.log(num) / Math.log(4));
      return Math.pow(4,x) == num;
  }
}
