/**
 * 
 * 
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

 /* Asume n1 = nums1.length, n2= nums2.length
 we take m1 elements from nums1, amd m2 elements from nums2
 m1+m2= k = (n1+n2+1)/2
 A: [0, .... m1-1, m1, ...]  0~m1-1=m1 elements
 B: [0, ...  m2-1, m2, ....] 0~m2-1=m2 elements
 C: [0, ...  k-1, k, ....]   0~k-1=k=m1+m2 elements 
 Ck-1=max(A[m1-1], B[m2-1])
 Ck  =min(A[m1], B[m2])
 if n1+n2=odd, answer = Ck-1
 else answer = (Ck-1+Ck)/2
 
 if(A[m1] < B[m2-1]) we need more from array A, so move right
           0  1  2  3  4  5
 e.g: A= [-1, 1, 3, 5, 7, 9]
      B= [ 2, 4, 6, 8, 10, 12, 16]
half = (6+7+1)/2=7 
l=0 r=6 mid=3 since A[3]=5<B[7-3-1]=8 means we need more from A, move right, l=3+1
l=4 r=6 mid=5 since A[5]=9>B[7-5-1]=4 we need less from A, move left, r=mid=5
l=4 r=5 mid=4 since A[4]=7>B[7-4-1]=6 we need less from A, move left, r=4 
r=l stop
we get A[4]=7, A[4-1]=5, B[7-4]=8, B[7-4-1]=6
answer = max(A[3], B[2])=6

 */  
class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      if(nums1.length > nums2.length) {
          return findMedianSortedArrays(nums2, nums1);
      }
      
      int n1=nums1.length, n2=nums2.length, k=(n1+n2+1)/2, l=0, r=n1;
      while(l < r) {
          int mid = (r+l)/2;
          if(nums1[mid] < nums2[k-mid-1]) { // we need more from nums1
              l= mid+1;
          } else {
              r= mid;
          }
      }
      int m1= l, m2= k-l;
      int c1= Math.max(m1<=0 ? Integer.MIN_VALUE : nums1[m1-1], 
                       m2<=0 ? Integer.MIN_VALUE : nums2[m2-1]);
      if((n1+n2) % 2 == 1) return c1;
      
      int c2= Math.min(m1>=n1 ? Integer.MAX_VALUE : nums1[m1], 
                       m2>=n2 ? Integer.MAX_VALUE : nums2[m2]);
      return (c1+c2)*0.5;
  }
}