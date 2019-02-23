/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
*/

/*
            [1,3,2,3,1]
            / .        \
        [1,3,2]       [3,1]    compare 1 with [1,3]  1<3 1=1 c=1 compare 3 with [3,1] 3>1 c++=2 compare 2 [3,1]
        /    \        /  \
       [1]   [3,2]   [3] [1]
               / \    since 3>1 c++=1
              [3][2]
               3<2*2 c=0
       
      since 1<3, 1<2 c=0
      
      we can use merge sort and compare with only once for each element
        
we need to use divide and conquer  nums: [ ..i...j..]
say we have an array nums, we get the total number of important pairs that i < j  and nums[i] > nums[j], we can divide the array to two parts: [..... | ......]
                 i       j
              left        right
the total number = total pairs from left array + total pairs from right array + splited pairs (one on left, the other on right)
to get the split pair, say we found nums[i] > nums[j], since the left array and right array are sorted, nums[i] to nums[mid] are all satisfy the condition, so we can add count to mid-i+1, then move j for next compare, if nums[i] < nums[j], we just need to move i to find nums[i] > nums[j] this process is O(N)

after that we merge left array with right array. 
*/

class Solution {
  int[] helper;
  public int reversePairs(int[] nums) {
      this.helper = new int[nums.length];
      return sort(0, nums.length-1, nums);
  }
  
  public int sort(int s, int e, int[] nums) {
      if(s >= e) return 0;
      int mid = s + (e-s)/2;
      int sum = sort(s, mid, nums) + sort(mid+1, e, nums);
      for(int i=s, j=mid+1; i<=mid; i++) { 
          while(j <= e && nums[i]/2.0 > nums[j])  j++;
          sum+= j- (mid+1);
           
      }
      merge(nums, s, mid, e);
      return sum;
  }
  
  public void merge(int[] nums, int s, int mid, int e) {
      for(int i = s; i<=e; i++) helper[i] = nums[i];
      int k=s, p1=s, p2=mid+1;
      while(p1 <= mid || p2 <= e) {
          if(p2 > e || (p2<=e && p1 <= mid && helper[p1] < helper[p2])) {
              nums[k++] = helper[p1++];
          } else {
              nums[k++] = helper[p2++];
          }
      }
  }
}
