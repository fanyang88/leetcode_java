/*
Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1
Note:

3 <= A.length <= 10000
0 <= A[i] <= 10^6
A is a mountain, as defined above.
*/

/*
 same as LC 162
   /\
  /  \
 /    \   if peak is on the left, nums[mid] > nums[mid+1], mid could be the peak
          if peak is on the right,nums[mid] < nums[mid+1] 

*/

class Solution {
  public int peakIndexInMountainArray(int[] A) {
      int s = 0, e = A.length-1;
      while(s < e) {
          int mid = s + (e - s)/2;
          int next = mid+1>=A.length? Integer.MIN_VALUE:A[mid+1];
          if(A[mid] > next) { // peak on left side
               e= mid;
          } else {
              s = mid+1;
          }
      }
      return e;
  }
}
