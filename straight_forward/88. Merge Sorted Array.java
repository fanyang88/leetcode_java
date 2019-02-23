/**
 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 */


/*
case 1: 
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3
 since 3< 6 put 6 to right, p2--, right--
 
 case 2:
 nums1 = [1,2,6,0,0,0], m = 3
 nums2 = [2,4,5],       n = 3
 since 5< 6 put 6 to right, p1--, right--
*/
class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
      int p1=m-1, p2=n-1, cur=m+n-1;
      while(p1>=0 || p2 >=0) {
          if(p1<0) {
              nums1[cur] = nums2[p2];
              p2--;
          } else if(p2 < 0) {
              nums1[cur] = nums1[p1];
              p1--;
          } else {
              if(nums1[p1] > nums2[p2]) {
                  nums1[cur] = nums1[p1];
                  p1--;
              } else {
                  nums1[cur] = nums2[p2];
                  p2--;
              }
          }
          cur--;
      }
  }
}
