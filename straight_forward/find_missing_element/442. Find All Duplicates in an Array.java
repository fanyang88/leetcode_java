/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

/*
     0 1 2 3 4 5 6 7
    [4,3,2,7,8,2,3,1]
    i=0 ind=4 change num[4-1] to be neg [4,3,2,-7,8,2,3,1]
    i=1 ind=3 change num[3-1] to be neg [4,3,-2,-7,8,2,3,1]
    i=2 ind=3 change num[2-1] to be neg [4,-3,-2,-7,8,2,3,1]
    i=3 ind=7 change num[7-1] to be neg [4,-3,-2,-7,8,2,-3,1]
    i=4 ind=8 change num[8-1] to be neg [4,-3,-2,-7,8,2,-3,-1]
    i=5 ind=2 change num[2-1] to be neg , but since it is alrady neg, so 2 is dup
    i=6 ind=3 change num[3-1] to be neg , but since it is alrady neg, so 3 is dup
    
*/

class Solution {
  public List<Integer> findDuplicates(int[] nums) {
      List<Integer> res = new ArrayList<Integer>();
      for(int i=0; i<nums.length; i++) {
          int ind = Math.abs(nums[i]);
          if(nums[ind-1] < 0) {
              res.add(ind);
          } else {
              nums[ind-1] = -nums[ind-1];
          }
      }
      return res;
  }
}
