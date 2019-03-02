/*
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
*/

/*
    sort the numbers, get the medium number and add/subtract each to equal to the medium number
 * e.g: 2 3 5 6 -> 3 3 3 3 count = 6  -> 5 5 5 5 -> count = 6
*/

class Solution {
  public int minMoves2(int[] nums) {
      Arrays.sort(nums);
      int i=0, j=nums.length-1, count=0;
      while(i < j) {
          count+= nums[j] - nums[i];
          j--;
          i++;
      }
      return count;
  }
}
