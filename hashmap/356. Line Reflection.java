/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true
Example 2:

Input: [[1,1],[-1,-1]]
Output: false
Follow up:
Could you do better than O(n2) ?
*/

/*
                   |
                   |
        ------x1---|----x2-----------
                   |
                   |
                   y=mid
        if there is a line parallel to y that can reflect all points, say it is y=mid
 means for each pointer p , there must to another pointer p2 in the set that has x = mid - p1.x  and p1.y=p2.y
 1. find the mid = min+max,  e.g: min=-1 max = 2 mid = 1  
 2. add all pointers in the map with key = p[0]+'_'+p[1]
 3. for each pointer, there should has another pointer mid-p[0]+'_'+p[1]  if can't find, it can't be reflected by a line para to y
*/

class Solution {
  public boolean isReflected(int[][] points) {
      Set<String> set = new HashSet<>();
      int minV = Integer.MAX_VALUE, maxV = Integer.MIN_VALUE;
      for(int[] p: points) {
          minV = Math.min(minV, p[0]);
          maxV = Math.max(maxV, p[0]);
          set.add(p[0] + "a" + p[1]);
      }
      int mid = minV + maxV;
      for(int[] p : points) {
          String str = (mid - p[0]) + "a" + p[1];
          if(!set.contains(str))  return false;
      }
      return true;
  }
}