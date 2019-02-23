/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false

*/


/*
e.g: n=49
    example: divide 49 by 2 = 24, 24x24 > 49, divide 24 by 2 = 12, 12x12> 49, divide 12 by 2 = 6, 6x6 < 49
 we find the range 12~6, get squres in this range, if there is equal, then we find it.
*/

class Solution {
  public boolean isPerfectSquare(int num) {
      int lo=1, hi=num;
      while(lo <= hi) {
          long mid = lo + (hi-lo)/2;  // has to be long
          if(mid * mid == num)  return true;
          if(mid * mid < num) {
              lo = (int)mid+1;
          } else {
              hi = (int)mid-1;
          }
      }
      return false;
  }
}
