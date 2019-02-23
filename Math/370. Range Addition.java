/*
Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Explanation:

Initial state:
[0,0,0,0,0]

After applying operation [1,3,2]:
[0,2,2,2,0]

After applying operation [2,4,3]:
[0,2,5,5,3]

After applying operation [0,2,-2]:
[-2,0,3,5,3]
*/

/*
Just store every start index for each value and at end index plus one minus it

     0 1 2 3 4
    [0,0,0,0,0] 
    i=[1,3,2] => [0,2,0,0,-2]
    i=[2,4,3] => [0,2,3,0,-2]
    i=[0,2,-2]=> [-2,2,3,2,-2]
    
    [-2,2,3,2,-2]
    sum=[-2, 0, 3, 5, 3]

*/

class Solution {
  public int[] getModifiedArray(int length, int[][] updates) {
      int[] nums = new int[length];
      for(int[] update: updates) {
          nums[update[0]] += update[2];
          if(update[1]+1 < length) {
              nums[update[1]+1] -= update[2];
          }
      }
      
      for(int i=1; i<nums.length; i++) {
          nums[i]  += nums[i-1];
      }
      return nums;
  }
}