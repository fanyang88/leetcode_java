/*
Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]
 

Example 1:

Input: [2,1]
Output: false
Example 2:

Input: [3,5,5]
Output: false
Example 3:

Input: [0,3,2,1]
Output: true
 

Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000 
*/

class Solution {
  public boolean validMountainArray(int[] A) {
      int n = A.length, i=0, j=n-1;
      while(i+1 < n && A[i+1] > A[i]) i++;
      while(j-1 >=0 && A[j] < A[j-1]) j--;
      return i==j && i!=n-1 && j!=0;
  }
}



PYTHON ****************************************************

class Solution:
    def validMountainArray(self, arr: List[int]) -> bool:
        left, right = 0, len(arr)-1
        while left+1 <= len(arr)-1 and arr[left] < arr[left+1]: left+=1
        while right-1 >= 0 and arr[right] < arr[right-1]: right-=1
        
        return left==right and left > 0  and right < len(arr)-1
