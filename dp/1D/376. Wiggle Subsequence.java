/*
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Example 1:

Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
*/

/*
    [1,2,3,4,5,6,7,8,9]
 for every i there are three situations: 
 1. a[i-1] < a[i]  up = down+1
 2. a[i-1] > a[i]  down = up+1
 3. a[i-1] = a[i]  do nothing
 up=1, down=1
 i=1: 1< 2, up=1+1=2  down=1
 i=2: 2< 3, up=down+1=2 down=1
 i=3: 3< 4, up= down+1=2  down=1
 ....
 
 max length = 2
 
 [1,17,5,10,13,15,10,5,16,8]
 i=1  1< 17, up=1+1=2  down=1
 i=2  17> 5, down=up+1=3 up=2
 i=3  5< 10, up=down+1=4  down=3
 i=4  10<13, up=down+1=4  down=3
 i=5  13<15, up=down+1=4  down=3
 i=6  15>10, down=up+1=5 up=4
 i=7  10>5,  down=up+1=5 up=4
 i=8  5<16,  up=down+1=6  down=5
 i=9  16>8,  down=up+1=7 up=6
 return 7

*/

class Solution {
  public int wiggleMaxLength(int[] nums) {
      if(nums.length==0)  return 0;
      int down=1, up=1;
      for(int i=1; i<nums.length; i++) {
          if(nums[i-1] > nums[i]) {  // down
              down = up+1;
          } else if(nums[i-1] < nums[i]) {  // up
              up = down+1;
          }
      }
      return Math.max(up, down);
  }
}
