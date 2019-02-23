/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

/*
 
 3 steps:
 1. copy the original and find the median using quick sort in copyed array
 2. after step 1, all numbers smaller than median on the left, numbers no less than median on the right
    then we need to use same algorithm for sort color to make sure number < median on the left, number - median in the middle
    and number that > median on the right
 3. we put left to middle numbers from copy array to nums from right to left, only put in even indexes
    we put the middle to right numbers from copy array to nums from right to left, only put in odd indexes
 
 nums:
        l2          l1       median
 median     s2            s1
      
 
 13   6   5   5   4   2   median=5
 i
 j                    k
  
 nums[j]>median swap 13 with 2
 2   6   5   5   4   13
 i
 j               k
 nums[j] < median   swap(copy, i++, j++);
 2   6   5   5   4   13
     i
     j           k
 nums[j] > median   swap(copy, j, k--);
 2   4   5   5   6   13
     i
     j       k   
 nums[j] ===5 j++
 nums[j]=5 j++  j=k, stop
 
 put the first half s1<s2<...<median  reversely into nums[even]
 i=m-1, j=0, nums[j] = copy[i] j+=2 , i--
 2   4   5   5   6   13
 j                    i
                 i
 nums[0] = 5   nums[2] = 4   nums[4] = 2
 i=n-1, j=1
 put the second half median <l1<l2< ...  reversely into nums[odd]
 nums[1]= 13  nums[3]=6    nums[5]=5
 
 nums:
 coped: s1 s2 median median l1 l2
 
       l2          l1       median
median     s2            s1
 5 < 13 >  4 <  6  > 2 < 5

*/

class Solution {
  public void wiggleSort(int[] nums) {
      int[] clone = nums.clone();
      int n= nums.length, mid = (n+1)/2;
      int midV = findKthSmallest(clone, mid);
      
      // sort color technique, all number < midV go left, all number > midV go right
      int left=0, right = n-1;
      for(int i= 0; i<=right; i++) {
          if(clone[i] < midV) {
              swap(i, left, clone);
              left++;
          } else if(clone[i] > midV) {
              swap(i, right, clone);
              right--;
              i--; // test again for the new value
          }
      }
      // fill the numbers from middle to the start into the nums[even]
      for(int i=mid-1, j=0; i>=0; i--, j+=2) nums[j] = clone[i];
      // fill the numbers from end to the middle into the nums[odd] 
      for(int i=n-1, j=1; i>mid-1; i--, j+=2) nums[j] = clone[i];
      
  }
  
  public int findKthSmallest(int[] nums, int k) {
      return select(0, nums.length-1, nums, k);
  }
  
  public int select(int lo, int hi, int[] nums, int k) {
      int split =  partition(lo, hi, nums);
      int len = split - lo + 1;
      if(len == k)  return nums[split];
      if(len > k) {
          return select(lo, split-1, nums, k);
      } else {  // len < k, go to right part to find k-len
          return select(split+1, hi, nums, k-len);
      }
  }
  
  public int partition(int lo, int hi, int[] nums) {
      int pivot = nums[lo], s = lo+1, e = hi;
      while(true) {
          while(s <= hi && nums[s] <= pivot) s++;
          while(e > lo && nums[e] >= pivot) e--;
          if(s >= e) break;
          swap(s, e, nums);
      }
      swap(lo, e, nums);
      return e;
  }
    
  public void swap(int i, int j, int[] nums) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}