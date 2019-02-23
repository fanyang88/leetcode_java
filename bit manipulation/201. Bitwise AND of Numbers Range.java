/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */

 /*
    This is a math problem
    101&110=100&111=100
    
    m=101 n=111
    since m!=n m=10 n=11  i=10
    since m!=n m=1 n=1 i=100
    answer is 100=4
    
    0&3= 0&1=0&10=0&11=0
    
    1&2=0
*/

class Solution {
  public int rangeBitwiseAnd(int m, int n) {
     
      if(m==0 || m>n) return 0;
      int i=0;
      while(m!=n) {
          m = m >> 1;
          n = n>> 1;
          if(m==0) return 0;
          i++;
      }
      return m<<i;
  }
}
