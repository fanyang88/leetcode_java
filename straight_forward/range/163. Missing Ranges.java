/**
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
 */

class Solution {
  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
 List<String> res = new ArrayList<String>();
 
 // the next number we need to find
 int next = lower;
 
 for (int i = 0; i < nums.length; i++) {
   // not within the range yet
   if (nums[i] < next) continue;
   
   // continue to find the next one
   if (nums[i] == next) {
     // each time we try to add next, check if it is already max_value to avoid overflow
     if(next == Integer.MAX_VALUE)  return res;  
     next++;
     continue;
   }
   
   // get the missing range string format
   res.add(getRange(next, nums[i] - 1));
   
   // now we need to find the next number
   if(nums[i] == Integer.MAX_VALUE)  return res;
   next = nums[i] + 1;
   
 }
 
 // do a final check
 if (next <= upper) res.add(getRange(next, upper));

 return res;
}

   public String getRange(int a, int b) {
       return a==b? ""+a : ""+a+"->"+b;
   }
}
