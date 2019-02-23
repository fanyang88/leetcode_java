/*
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
*/



/*
3^x = n  x = log(3)(n)  base is 3
since log(3)(n) = log10(n)/log10(3) 
*/

class Solution {
  public boolean isPowerOfThree(int n) {
      return n==0 ? false : n==Math.pow(3, Math.round(Math.log(n) / Math.log(3))); 
  }
}
