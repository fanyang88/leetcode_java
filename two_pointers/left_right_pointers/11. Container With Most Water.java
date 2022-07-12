/**
 * 
 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 Find two lines, which together with x-axis forms a container, 
 such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
In this case, the max area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */

class Solution {
  public int maxArea(int[] height) {
      int l = 0, r=height.length-1, maxArea = 0;
      while(l<r) {
          maxArea = Math.max(maxArea, (r-l)*Math.min(height[l],  height[r]));
          if(height[l] < height[r]) l++;
          else r--;
      }
      return maxArea;
  }
}


# PYTHON ******************************************************************************

class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_v = 0
        i, j = 0, len(height)-1
        while i < j:
            area = min(height[j], height[i]) * (j-i)
            max_v = max(max_v, area)
            if height[i] < height[j]:
                i+=1
            else:
                j-=1
        return max_v
        
#thoughts:      
# [1,8,6,2,5,4,8,3,7]
# i=1 j=7 max = 8 * 1
# since 7>1 i++=8 max=7*7
# since 8>7 j++=3 area = 6*3

# we always keep the larger on and move the smaller one towards




