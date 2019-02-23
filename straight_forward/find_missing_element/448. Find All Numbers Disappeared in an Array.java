/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

/*
  [4,3,2,7,8,2,3,1]
   same as 442. Find All Duplicates in an Array
    [4,3,2,7,8,2,3,1]
    i=0 ind=4 change num[4-1] to be neg [4,3,2,-7,8,2,3,1]
    i=1 ind=3 change num[3-1] to be neg [4,3,-2,-7,8,2,3,1]
    i=2 ind=3 change num[2-1] to be neg [4,-3,-2,-7,8,2,3,1]
    i=3 ind=7 change num[7-1] to be neg [4,-3,-2,-7,8,2,-3,1]
    i=4 ind=8 change num[8-1] to be neg [4,-3,-2,-7,8,2,-3,-1]
    i=5 ind=2 change num[2-1] to be neg , but since it is alrady neg, so be it
    i=6 ind=3 change num[3-1] to be neg , but since it is alrady neg, so be it
    i=7 ind=1 change num[1-1] to be neg
    
    check which ind is still pos, in above case it is 4,5 so answer is 5, 6
    

*/

class Solution {
  public List<Integer> findDisappearedNumbers(int[] nums) {
      List<Integer> res = new ArrayList<Integer>();
      for(int i=0; i<nums.length; i++) {
          int ind = Math.abs(nums[i]);
          if(nums[ind-1] > 0) {
              nums[ind-1] = -nums[ind-1];
          } 
      }
      
       for(int i=0; i<nums.length; i++) {
          if(nums[i] > 0) {
              res.add(i+1);
          } 
      }
      return res;
  }
}
