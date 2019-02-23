/**
 Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped.

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

 /*
            [0,1,0,2,1,0,1,3,2,1,2,1]
left_max:   [0,1,1,2,2,2,2,3,3,3,3,3]
right_max:  [3,3,3,3,3,3,3,3,2,2,2,1]
answer:     [0,0,1,0,1,2,1,0,0,1,0,0]
answer: min(left, right) - height
            
*/
class Solution {
  public int trap(int[] height) {
      if(height.length ==0)  return 0;
      int n = height.length, res=0;
      int[] left= new int[n], right = new int[n];
      
      left[0] = height[0];
      right[n-1] = height[n-1];
      
      for(int i=1; i<n; i++) {
          left[i] = Math.max(height[i], left[i-1]);
      }
      for(int i=n-2; i>=0; i--) {
          right[i] = Math.max(height[i], right[i+1]);
      }
      for(int i=0; i<n; i++) {
          res += Math.min(left[i], right[i]) - height[i];
      }
      return res;
  }
}