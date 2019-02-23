/**
 Given an array with n objects colored red, white or blue, 
 sort them in-place so that objects of the same color are adjacent, with the colors in the order red, 
 white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */

 /*
    e.g: 1,2,0,0,2,2,0,0
         l             r
i=0 e=1
i=1 e=2  swap num[i] & num[r] r-- i stay the same place to examine new val again
         1,0,0,0,2,2,0,2
i=1 e=0  since e=0 swap num[i] & num[l] l++
         0,1,0,0,2,2,0,2
           l         r   l point to the next potential red, r point to the next blue position
i=2 e=0 swap num[i] & num[l] l++
        0,0,1,0,2,2,0,2
            l       r   
i=3 e=0 swap num[i] & num[l] l++
        0,0,0,1,2,2,0,2
              l     r   
i=3 e=1 
i=4 e=2 swap num[i] & num[l] r-- i--,
        0,0,0,1,0,2,2,2
              l   r 
i=4 e=0 swap num[i] & num[l] l++
        0,0,0,0,1,2,2,2
                l r
i=5 since i=right 


[1, 0, 2]
l      r
i=0 e=1 
i=1  e=0 swap  nums[l] & nums[1], l++
     [0, 1, 2]
         l  r
i=2 e=2 swap nums[2] with nums[r]  r--, i--
i=2 > r=1 stop
*/
class Solution {
  public void sortColors(int[] nums) {
      int left = 0, right= nums.length-1;
      for(int i=0; i<=right; i++) { // it is <= not <, the key
          if(nums[i] == 0) {
              swap(nums, left, i);
              left++;
          } else if(nums[i] == 2) {
              swap(nums, right, i);
              right--;
              i--;
          } 
      }
  }
  
  public void swap(int[] nums, int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}