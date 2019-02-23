/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */

 /*
(sum % k) / (T % k)
 template:
 Given a non-empty array of integers, every element appears K times except for one, which appears exactly T times. 
 Find that one.
 
 get sum of every bit first: 
 e.g: [1,2,3,1,2,3,1,3]  K=3 T=2
       0 1 1 0 1 1 0 1  = 5 % k = 2  => 2 / (T%k) = 1
       1 0 1 1 0 1 1 1  = 6 % k = 0  => 0 / (T%k) = 0
 answer is 2
 e.g: [1,2,3,1,2,3,1,3,2,2,2]  K=3 T=5
       0 1 1 0 1 1 0 1 1 1 1  = 8 % k = 2  => 2 / (T%k) is 2 = 1
       1 0 1 1 0 1 1 1 0 0 0  = 6 % k = 0  => 0 / (T%k) is 2 = 0
 answer is 2
 
 the each bit of the answer is: (sum of that bit % k) / (T % k)
*/

class Solution {
  public int singleNumber(int[] nums) {
      int[] bits= new int[32];
      int k=3, t=1, res= 0;
      for(int i=0; i<32; i++) {
          for(int num: nums) {
              bits[i] += getBit(num, i);
          }
          bits[i] = (bits[i] % k) / ( t % k);
      }
      for(int i=0; i<32; i++) {
          res += bits[i] << i;
      }
      return res;
      
  }
  
  public int getBit(int num, int k) {
      return (num>> k) & 1;
  }
}
