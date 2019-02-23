/*
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

*/

class Solution {
  public int thirdMax(int[] nums) {
      long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 =Long.MIN_VALUE;
      for(int n: nums) {
          if(max1 <= n) {
              if(n==max1)  continue;
              max3 = max2;
              max2 = max1;
              max1 = n;
          } else if(max2 <= n) {
              if(n==max2)  continue;
              max3 = max2;
              max2 = n;
          } else if(max3 <= n) {
              if(n==max3)  continue;
              max3 = n;
          }
      }
      return max3==Long.MIN_VALUE ? (int)max1 : (int)max3;
  }
}
