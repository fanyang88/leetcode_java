/**
 * 
 Given an array nums of n integers and an integer target, 
 are there elements a, b, c, and d in nums such that a + b + c + d = target? 
 Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
      int n = nums.length;
      List<List<Integer>> res = new LinkedList<>(); 
      Arrays.sort(nums);
      for(int i=0; i<n-3; i++) {
          int c = nums[i];
          for(int j=i+1; j<n-2; j++) {
              int d = nums[j], l=j+1, r=n-1;
              while(l < r) {
                  if(c+d+nums[l] + nums[r] == target) {
                      res.add(Arrays.asList(c, d, nums[l], nums[r]));
                      l++;
                      r--;
                      while(nums[l]== nums[l-1] && l<r) l++;
                      while(nums[r]== nums[r+1] && l<r) r--;
                  } else if(c+d+nums[l] + nums[r] < target) {
                      l++;
                  } else {
                      r--;
                  }
              }
              while(nums[j] == nums[j+1] && j+1 < n-2) j++;
          }
          while(nums[i] == nums[i+1] && i+1 < n-3) i++;
      }
      return res;
  }
}



PYTHON ***************************************************

class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        length = len(nums)
        nums.sort()
        res = []
        
        for i in range (0, length-3):
            if i>0 and nums[i-1]==nums[i]: 
                continue
            for j in range (i+1, length-2):
                if j>i+1 and nums[j-1]==nums[j]: 
                    continue
                l, r = j+1, length-1
                while l<r:
                    if nums[i]+nums[j]+nums[l]+nums[r] == target:
                        print(i, j, l, r)
                        res.append([nums[i], nums[j], nums[l], nums[r]])
                        l+=1
                        r-=1
                        while l<r and nums[l-1]==nums[l]: l+=1
                        while l<r and nums[r+1]==nums[r]: r-=1
                    
                    elif nums[i]+nums[j]+nums[l]+nums[r] < target:
                        l+=1
                    else:
                        r-=1
                
        return res
                        
                
        
        
# Thoughts:
#     similar like 3 sum
#     [1,0,-1,0,-2,2]
#     sort first:
#     [-2,-1,0,0,1,2]
    
#     a=-2 b=-1   c = 0 d=2 sum-1<0 move c, since next is 0 continue c=1 sum=0 record c++ d-- exceed
#     a=-2 b=0    c=0 d=2 sum=0 record it, c++ d-- c++ exit
#     a=-2 b=1 exceed
    
