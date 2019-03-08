/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
*/

class Solution {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
      List<Integer> res = new ArrayList<Integer>();
      int index = binarySearch(arr, x);
          int right = index== -1 ? index+1: index;  // index=-1 means all nums are smaller than x
          int left = index==-1 ? -1: index-1;
          while(k-->0) {
              if(left ==-1 || (left >=0 && right < arr.length && Math.abs(x - arr[right]) < Math.abs(x - arr[left]))) {
                   right++;
              } else {
                  left--;
              }
          }
          for(int i=left+1; i<right; i++) res.add(arr[i]); // this is the key, need go from left+1 to right-1
          return res;
  }
  
  public int binarySearch(int[] arr, int x) {
      int lo = 0, hi = arr.length-1;
      while(lo < hi) {
          int mid = lo + (hi - lo)/2;
          if(arr[mid] >= x) {
              hi = mid;
          } else { // arr[mid] < x
              lo = mid+1;
          }
      }
      return arr[hi] < x ? -1: hi;
  }
}
