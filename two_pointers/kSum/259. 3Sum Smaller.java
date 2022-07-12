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


PYTHON *************************

class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        n = len(nums)
        nums.sort()
        count=0
        
        for i in range(n):
            j, k=i+1, n-1
            while j < k:
                if nums[i] + nums[j] + nums[k] == target:
                    # move it appoach to smaller
                    k-=1
                elif nums[i] + nums[j] + nums[k] > target:
                    k-=1
                else:
                    count+= k-j
                    j+=1
        return count
                
            
        
        
# Thought:
#     nums = [-2,0,1,3]
#     1. sort
#     a=-2 b=0 c=3 sum=1<2 in this case we find one group, but given 1<3 so the sum must be also < target
#     so the trick is if nums[i] + nums[j] + nums[k] < target
#     any new tuple in nums[i] + nums[j] + nums[k-1] < target
#     so the range is j+1~k  = k-(j+1)+1 = k-j
    
    
