/*
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
*/

/*
 e.g: 
 nums1 = [6, 7]
 nums2 = [6, 0, 4]   k=5
 if(nums1.length+nums2.length==k) just use two pointers to get the max which is 6,7,6,0,4
 
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]  k = 5
 if we remove 0 from nums1, 5 from nums2:
 [3, 4, 6, 5]  [9] => [9, 3, 4, 6, 5]
 if we remove 1 from nums1, 4 from nums2:
 [4, 6, 5]  [9,8] => [9, 8, 4, 6, 5]
 if we remove 2 from nums1, 3 from nums2:
 [6, 5]  [9,8,3] => [9, 8, 6,5,3]   =>  max
 if we remove 3 from nums1, 2 from nums2:
 [6]  [9,5,8,3] => [9, 5, 8, 6,3]
 if we remove 4 from nums1, 1 from nums2:
 []  [9,2,5,8,3] => [9,2,5,8,3]
 
 in all the combination above, get the max number
 refer to LC 402
*/

class Solution {
  public int[] maxNumber(int[] nums1, int[] nums2, int k) {
      int[] ans = new int[k];
      for(int k1=0; k1<=k; k1++) {
          int k2 = k-k1;
          if(k1 >nums1.length || k2 >nums2.length)  continue;
          int[] candicate = merge(removeK(nums1, k1), removeK(nums2, k2), k);
          ans= greater(ans, 0, candicate, 0) ? ans : candicate;
      }
      return ans;
  }
  
  public int[] merge(int[] n1, int[] n2, int k) {
      int[] arr = new int[k];
      int p1=0, p2=0;
      for(int i=0; i<k; i++) {
          arr[i] = greater(n1,p1, n2, p2) ? n1[p1++] : n2[p2++];
      }
      return arr;
  }
  
  public boolean greater(int[] n1, int i, int[] n2, int j) {
      while(i< n1.length && j<n2.length && n1[i] == n2[j]) {
          i++;
          j++;
      }
      return j==n2.length || (i<n1.length && n1[i] > n2[j]);
  }
  
  public int[] removeK(int[] num, int k) {
      int[] res =new int[k];
      
      for(int i=0, len=0; i<num.length; i++) {
          while(len>0 && len+num.length-i > k && num[i] > res[len-1]) {
              len--;
          }
          if(len < k) {
              res[len++] = num[i];
          } 
      }
      return res;
  }
}
