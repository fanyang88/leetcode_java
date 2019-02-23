/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
*/

/*
similar to reverse pair, in the presum array, we need to find out the sum[j] - sum[i] in the range
   for an array, split into two parts:  [..i.. | ..j..], left and right are sorted, e.g: [1 2 3 | 1 2 3 ]  range [-1,1]
                                        left    right
   before we merge, for each left[i], we need find the range in right array that the left[i] with elements in right fall in the [lower, upper] range, we use m, n.
   m to be the first elelemt in right array to make right[m] - left[i] >= lower
   n to be the first elelemt in right array to make right[n] - left[i] > upper, then n-m is the number of range for left[i]
   
   for example left: 1 2 3 | right -3 -2 -1 1 2 3    range is [-1, 1]
   for left[0]  since-3-1<-1 m++, -2-1<-1 m++, -1-1<-1 m++ 1-1>lower m point to 1
                since-3-1<1 n++, -2-1<1 n++, -1-1<1 n++ 1-1<1 n++ 2-1=1 n++ 3-1>upper n point to 3 means we found two pairs:
                sum[6] - sum[0] in this range
                sum[8] - sum[0] in this range
                
  e.g: [1,2] [3,4,5]   [3,5]
              |
              m,n
       since num[m]-1=2 < lower=3 m++  4-1=3=lower stop m=3
       since num[m]-1=2 < upper=5 n++  4-1=3<upper n++ since 5-1=4<upper n++ excedd bound n=5
       n-m=2 for 1 there is 2 ranges for in that range
       we do the same thing for 2 as well
       since in [1,2] the same calcualtion has been done, same as [3,4,5], so the total count is the answer
       there won't be duplicate, since each number only compare with the others once before the merge,
       after merge, they won't compare again.
       
       Another key is pre-process the nums array to be [0, sum1, sum2,...]
       the first one has to be 0, so that sum[1] - sum[0] = num[1] 
*/

class Solution {
    
  public int countRangeSum(int[] nums, int lower, int upper) {
      long[] sums = new long[nums.length+1]; // has to make it to be long in case of overflow
      for(int i=0; i<nums.length; i++) {
          sums[i+1] = nums[i] + sums[i];
      }
      return sort(0, sums.length-1, sums, lower, upper);
  }
  
  public int sort(int s, int e, long[] sums, int lower, int upper) {
      if(s>=e) return 0;
      int mid = s + (e-s)/2;
      int count = sort(s, mid, sums, lower, upper) + sort(mid+1, e, sums, lower, upper);
      int m=mid+1, n=mid+1;
      // compare each one from left half with the ones in the right half.
      for(int i=s; i<=mid; i++) {
          while(m <=e && sums[m] - sums[i] < lower) m++;
          // eventually m point to the first that minus sum[i] >= lower from left
          while(n <=e && sums[n] - sums[i] <= upper) n++; 
          // eventually n point to the first that minus sum[i] >upper from left
          count += n-m;
      }
      merge(sums, s, e, mid);
      return count;
  }
  
  public void merge(long[] sums, int s, int e, int mid) {
      long[] helper = sums.clone();
      int p1=s, p2=mid+1, i=s;
      
      while(p1<=mid && p2<=e)
          sums[i++] = helper[p1] < helper[p2] ? helper[p1++] : helper[p2++];
      while(p1 <= mid)  sums[i++] = helper[p1++];
      while(p2 <= e) sums[i++] = helper[p2++];
  }
}