/*
Given a sorted array of integers nums and integer values a, b and c. 
Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
*/

/*
  [-4,-2,2,4], a = 1, b = 3, c = 5
  since a>=0 if a<0 we start fill number from end to 0
  [16-12+5, 4-3+5, 4+3+5, 16+12+5]  
     i                     j
  i=0 j=3 since a[j]>a[i] res[3]=a[3] j--=2
  i=0 j=2 since a[j]<a[i] res[2]=a[0] i++=1
  i=1 j=2 since a[i]<a[j] res[0]=a[2] j--=1
  i=1 j=1 since a[i]=a[j] res[0]=a[i] stop i++=2
  
  if a<0 we start fill number from 0 to the end
  [-4,-2,2,4], a = -1, b = 3, c = 5
  [-16-12+5, -4-6+5, -4+6+5, -16+12+5]
     i                     j
  i=0 j=3 since a[i]<a[j] res[0]=a[0] i++=1
  i=1 j=3 since a[i]<a[j] res[1]=a[1] i++=2
  i=2 j=3 since a[i]>a[j] res[0]=a[3] j--=2
  i=2 j=2 since a[i]=a[j] res[0]=a[2] stop 
  
*/

class Solution {
  public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
      int n = nums.length,  i=0, j=n-1;
      int k= a<0 ? 0: n-1;
      int[] res = new int[n];
      
      for(int m=0; m<n; m++) {
          nums[m] = get(nums[m], a, b, c);
      }
      while(i<=j) {
          if(a>=0) {
              res[k--]= nums[i] > nums[j] ? nums[i++] : nums[j--];
          } else {
              res[k++] = nums[i] < nums[j] ? nums[i++] : nums[j--];
          }
      }
      return res;
  }
  
  public int get(int n, int a, int b, int c) {
      return n*n*a+n*b+c;
  }
}