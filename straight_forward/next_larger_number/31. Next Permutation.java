/**
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order 
(ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. 
Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

 /*
e.g: 1234 531 => 1235 134
4<5  once the nums[i] < nums[i+1]  we find the first index, then we go from right to find the first number
larger than nums[index1] swap those two numbers and reverse the number from index+1 to n-1 is the answer.       
*/
class Solution {
  public void nextPermutation(int[] nums) {
      int n = nums.length, ind=-1, ind2=-1;
      for(int i=n-2; i>=0; i--) {
          if(nums[i+1] > nums[i]) {
              ind = i;
              break;
          }
      }
      if(ind == -1)  {
          reverse(nums,0,n-1);
          return;
      } 
      for(int i=n-1; i>ind; i--) {
          if(nums[i] > nums[ind]) {
              ind2 = i;
              break;
          }
      }
      swap(nums, ind, ind2);
      reverse(nums, ind+1, n-1);
  }
  
  public void swap(int[] nums, int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
      return;
  }
  
  public void reverse(int[] nums, int s, int e) {
      while(s < e) {
          swap(nums, s, e);
          s++;
          e--;
      }
  }
}