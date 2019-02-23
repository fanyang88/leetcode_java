/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
class Solution {
  public List<String> summaryRanges(int[] nums) {
      List<String> res = new ArrayList<String>();
      int n = nums.length;
      if(n==0)  return res;
      int lower = nums[0], next= nums[0];
      
      for(int i=1; i<n; i++) {
          if(nums[i]== next+1) {
              next++;
          } else {
              res.add(getStr(lower, next));
              lower = nums[i];
              next = lower;
          }
      }
      res.add(getStr(lower, next));
      return res;
  }
  
  public String getStr(int i, int j) {
      if(i==j) {
          return ""+i;
      } 
      return ""+i+"->"+j;
  }
}

