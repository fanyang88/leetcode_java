/*
In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
Note:

The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].
*/

/*
 
 We need to split A into B and C, the length of B can be [1, A.length / 2], 
 we consider them one by one:
B should have the same mean value as A, 
so sumB / lenOfB = sumA / lenOfA, 
which is equavalent to sumB = sumA * lenOfB / lenOfA. 
All elements here are integers, so sumB must be an integer, this gives our first criteria (sumA * lenOfB) % A.length == 0.
Then further in function we recursicely check if we can find lenOfB elements in A who sum up to sumB.

corner case: [18,10,5,3], [3,5,10] === [18] but avg is not equal, so we need to add constrain of lenB
*/

class Solution {
  public boolean splitArraySameAverage(int[] A) {
      int total=0;
      for(int num: A) total +=num;
      Arrays.sort(A);
      for(int lenB =1; lenB <= A.length/2; lenB++) {
          if((total * lenB) % A.length !=0) continue;
          int sumB = (total * lenB) / A.length;
          // now we check if we can find lenB array with sum is sumB, and length is lenB
          if(dfs(lenB, A, sumB, 0, 0, 0)) return true;
      }
      return false;
  }
  
  public boolean dfs(int lenB, int[] A, int sumB, int index, int len, int sum) {
      if(sum >= sumB || len = lenB) {
          if(sum == sumB && len >= lenB) return true;
          return false;
      }
      for(int i=index; i<A.length; i++) {
          if(dfs(lenB, A, sumB, i+1, len+1, sum+A[i])) return true;
          while(i+1< A.length && A[i] == A[i+1]) i++;
      }
      return false;
  }
}
