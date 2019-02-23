/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

 */

 /*
    [1,1,1,3,3,2,2,2]
    major1= null, major2=null
    i=0 major1=1 c1=1
    i=1 c1++=2
    i=2 c1++=3
    i=3 major2=3 c2=1
    i=4 c2++=2
    i=5 c1--=2 c2--=1
    i=6 c1--=1 c2--=0
    i=7 since c2=0 major2=2 c2=1
    
    Check equal first, this is the key
*/

class Solution {
  public List<Integer> majorityElement(int[] nums) {
      List<Integer> res = new ArrayList<Integer>();
      int c1= 0, c2=0, major1=-1, major2=-1, n=nums.length;
      for(int i=0; i<n; i++) {
          if(nums[i] == major1) {
              c1++;
          } else if(nums[i] == major2) {
              c2++;
          } else if(c1==0) {
              major1 = nums[i];
              c1=1;
          } else if(c2==0) {
              major2 = nums[i];
              c2=1;
          } else {
              c1--;
              c2--;
          }
      }
      // Verify
      c1=0;
      c2=0;
      for(int i=0; i<n; i++) {
          if(major1 == nums[i]) c1++;
          if(major2 == nums[i]) c2++;
      }
      if(c1 > n / 3)  res.add(major1);
      if(c2 > n / 3 && major2 != major1)  res.add(major2);
      return res;
  }
}
