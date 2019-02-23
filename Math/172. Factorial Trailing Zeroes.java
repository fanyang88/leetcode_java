/**
 * Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
 */

 /*
The idea is:

The ZERO comes from 10.
The 10 comes from 2 x 5
And we need to account for all the products of 5 and 2. likes 4×5 = 20 ...
So, if we take all the numbers with 5 as a factor, we'll have way more than enough even numbers to pair with them to get factors of 10
Example One

How many multiples of 5 are between 1 and 23? There is 5, 10, 15, and 20, for four multiples of 5. Paired with 2's from the even factors, this makes for four factors of 10, so: 23! has 4 zeros.


But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is always ample. So we just count how many numbers can be divide by 5 in all number from 1 to n.
25:  5,10,15,20,25 but 25=5*5=2 factors+4=6
*/

class Solution {
  public int trailingZeroes(int n) {
      int count=0;
      while(n > 1) {
          count+= n/5;
          n= n/5;
      }
      return count;
  }
}
