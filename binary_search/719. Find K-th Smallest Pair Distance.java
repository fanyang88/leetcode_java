/*
Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
*/

/*
 we use binary search to set up the distance m first, 
 then we calculate how many pairs that has distances no larger than m
 when the total number of pairs = k, we get the answer.
 
 e.g: [1, 1, 3, 5, 8] k=2
 low = 1, high = 8, mid = 4 we now find the total number of pair that has distance <=4
    i=0,  j=0, till j= 4, nums[j] - nums[i] > 4, total += j-i-1 = 3
    i=1,  j still is 4, since nums[j] - nums[i] > 4, total += j-i-1 = 2+3 =5
    i=2,  j still is 4, since nums[j] - nums[i] > 4, total += j-i-1 = 1+5 =6
    i=3,  j still is 4, since nums[j] - nums[i] < 4,  j still 4,  total += j-i-1 = 0+6 =6
    
since total =6 > 2, search on left half, high = mid = 4, mid = (1+4)/2 = 2
we now find the total number of pair that has distance <=2
    i=0,  j=0, till j= 3, nums[j] - nums[i] > 2, total += j-i-1 = 2
    i=1,  j still is 3, since nums[j] - nums[i] > 2, total += j-i-1 = 1+2 =3
    i=2,  j still is 3, move j to 4, now nums[j] - nums[i] > 2, total += j-i-1 = 1+3 =4
    i=3,  j still is 4, since nums[j] - nums[i] > 2,    total += j-i-1 = 0+4 =4
    
since total =4 > 2, search on left half, high = mid = 2, mid = (1+2)/2 = 1
    the total number of pairs has distance <=1 is 1
since total =1 <2, search on right half, low = mid +1 =2 since low === high, stop, return high =2
 
*/

class Solution {
  public int smallestDistancePair(int[] nums, int k) {
      Arrays.sort(nums);
      int n = nums.length, lo = 0, hi = nums[n-1]-nums[0];
      while(lo < hi) {
          int mid = (lo + hi) /2; // we need to get how many pairs has distance <=mid
          int total=0, j=0;
          for(int i=0; i<n; i++) {
              while(j<n && nums[j] - nums[i] <= mid) j++;
              total += (j-i-1);
          }
          
          if(total < k) { //means we need more pairs, mid is too small
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return hi; // there are k number of pairs no larger than distance hi
  }
}