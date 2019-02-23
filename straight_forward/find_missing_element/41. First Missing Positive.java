/**
 Given an unsorted integer array, find the smallest missing positive integer.
Example 1:
Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:
Your algorithm should run in O(n) time and uses constant extra space.
 */


/*
        0,1, 2, 3
        3,4,-1, 1
        i=0, nums[0] swap nums[nums[0]-1] -1,4,3,1
             nums[0] < 0 skip 
        i=1 nums[1] swap nums[nums[1] -1]   -1,1,3,4
            nums[1] swap nums[nums[1] -1]   1,-1,3,4
            nums[1]-1 < 0 skip
        i=2 since nums[2] = nums[nums[2]-1] skip
        i=3 since nums[3] = nums[nums[3]-1] skip
        
        7,8,9,11,12
        i=0 nums[0]-1 > length skip
        i=1 nums[1]-1 > length skip
        ....
        the first one that num[i] != i+1 is i=0 return i+1=1
        the core is since num[i] = i+1 => 
                          nums[i]-1=i => 
                          num[i]= nums[nums[i]-1]
*/

class Solution {
  public int firstMissingPositive(int[] nums) {
      int n = nums.length;
      
      for(int i=0; i<n; i++) {
          while(nums[i] -1< n && nums[i] -1>=0 && nums[i] != nums[nums[i] -1]) {
              int temp = nums[nums[i] -1];
              nums[nums[i] -1] = nums[i];
              nums[i] = temp;
          }
      }
      for(int i=0; i<n; i++) {
          if(nums[i] != i+1)  return i+1;
      }
      // The key!!!
      return n+1;
  }
}

 