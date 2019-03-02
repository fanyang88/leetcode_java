/*
Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
*/

/*
    binary search:
    len must be odd
    
    [3,3,7,7,10,11,11]  len=7   0~mid is even, if nums[mid] == nums[mid-1] means the odd is at right
    [3,7,7,10, 10,11,11]          if nums[mid] != nums[mid-1] means the odd is at left
    
    
 from observation: if the smaller index of the pair is a odd number, means there are odd number in front of it
                        should search on left part, [1,1,2,3,3,4,4,8,8]  nums[3] = 3, single element is on left.
                  if the smaller index of the pair is a even number, means there are odd number adfter it
                        should search on right part  e.g:[3,3,7,7,10,11,11]  nums[7] = 2, should search on right.
[0, 1,2 ....]  if ind is odd number, search on left
[0, 1, ....]  if ind is even number, there are even number of pairs on left, so search right
    
    
*/

class Solution {
  public int singleNonDuplicate(int[] nums) {
      int lo = 0, n = nums.length, hi = n-1;
      while(lo < hi) {
          int mid = lo + (hi - lo)/2;
          Integer left = mid-1 < 0 ? null : nums[mid-1];
          Integer right = mid+1 >=n ? null : nums[mid+1];
          
          if(nums[mid] != left && nums[mid] != right) return nums[mid];
          int next= nums[mid] == left ? mid-1 : mid+1;
          int min = Math.min(mid, next);
          int max = Math.max(mid, next);
          if(min==0 || min %2==0) { // there are even numbers in left, answer is at right
              lo = max+1;
          } else {
              hi = min-1;
          }
      }
      return nums[lo];
  }
}
