/*
Given an array which consists of non-negative integers and an integer m, 
you can split the array into m non-empty continuous subarrays. 
Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
*/

/*

    Use binary search
    assume we can divide the array into m sub arrays                                                            
    then the largest sum of among those arrays is the range of [    max(arr),     - divide into n arrays 
                                                                    sum(arr))     - divide into 1 array
                                                                    
    Give a a candicate C  in that range, compute how many groups needed, 
    C means each group has sum <= C, The key is the find the largest C that makes k=m 
        if number of group k < m, means C is too larger, r = C
        if number of group k = m, means C is the potential answer, we make C
        if number of group k > m, means C is too small, l = C + 1
        to validate, we can sum up elements till > C, split one sum another elements... if we continue dp this till the end
         e.g:  0, 1, ... | k1, k2, ... | kn-1, kn ...
               sum <=C     sum<=C .        sum <=C .      k groups
               if k > m, for example we get 4 groups while we only need 2 groups, means C is too small
               otherwise, if k > m , means k is too larger
               
    O(log(sum)*n)
    
    The validation is only consider worst case if under worse case it is k groups, optimal could be >= k groups
*/


class Solution {
  public int splitArray(int[] nums, int m) {
      long lo = Integer.MIN_VALUE, hi = 0;  // need to convert to long, this is the key
      for(int num : nums) {
          lo = Math.max(lo, num);
          hi += num;
      }
      
      while( lo  < hi) {
          long mid = (hi + lo) / 2;
          if(!valid(mid, nums, m)) {  // group number > m, k is too larger, target is too small
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return (int)hi;
  }
  
  public boolean valid(long target, int[] nums, int m) {
      int k=1, sum =0;
      for(int num : nums) {
          sum += num;
          if(sum > target) {
              sum = num;
              k++;
              if(k > m)  return false;
          }
      }
      return true;
  }
}
