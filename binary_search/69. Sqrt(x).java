/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

//use binary search, the point is to find a mid*mid <= x && (mid+1)*(mid+1) > x then mid is the answer
// e.g: 17 since mid=9 9*9 > 17 hi=mid-1=8 mid=(1+8)/2=4  16<17 but 5*5=25>17 so the answer is 4
class Solution {
  public int mySqrt(int x) {
      if (x == 0) return 0;
      int lo=1, hi=x;
      while(true) {
          int mid = (lo+hi)/2;
          if(mid > x/mid) {
              hi = mid-1;
          } else { // mid*mid<=x && mid+1*mid+1>x answer is mid
              if(mid+1 > x/(mid+1))  return mid;
              lo = mid+1;
          }
      }
  }
}
