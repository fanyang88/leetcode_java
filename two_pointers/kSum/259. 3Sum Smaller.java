/**
 *
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
 */

 /*
[-2,0,1,3]
1. sort [-2,0,1,3]
    a=0 b=1 c=3 n[a]+n[b]+n[c]<target count += c -b; since c is satisfied, anthing smaller than c and larger than b should also satisfy this.
                b++;
    else c--;
         
            
*/

class Solution {
  public int threeSumSmaller(int[] nums, int target) {
      int count=0;
      Arrays.sort(nums);
      for(int i=0; i<nums.length-2; i++) {
          int j=i+1, k=nums.length-1;
          while(j<k) {
              if(nums[i] + nums[j] + nums[k] < target) {
                  count+= k-j;
                  j++;
              } else {
                  k--;
              }
          }
      }
      return count;
  }
}