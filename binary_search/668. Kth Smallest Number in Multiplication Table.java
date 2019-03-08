/*
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]
*/

/*
    using example 1:
    lo=1, hi=m*n=9   mid=5 . for 0th row: min(mid/1, n)=3 there are 3 element smaller than 5 in 0th row
                             for 1th row: min(mid/2, n)=2 there are 2 element smaller than 5 in 1th row
                             for 2th row: min(mid/3, n)=1 there are 1 element smaller than 5 in 2th row
                    total= 3+2+1=6 > k means mid=5 is to larger, hi = mid
    lo=1 hi=mid=5   mid=3  . for 0th row: min(mid/1, n)=3 there are 3 element smaller than 3 in 0th row
                             for 1th row: min(mid/2, n)=1 there are 1 element smaller than 3 in 1th row
                             for 2th row: min(mid/3, n)=1 there are 1 element smaller than 3 in 2th row
                    total= 3+1+1=5 == k means mid=3 is the answer
    return 3
*/

class Solution {
  public int findKthNumber(int m, int n, int k) {
      int lo = 1, hi = m*n;
      while(lo < hi) {
          int mid = lo + (hi - lo)/2;
          int total = 0;
          // getTotal numbers that no larger than mid
          for(int i=1; i<=m; i++) {
              total += Math.min(mid/i, n);
          }
          if(total >= k) {
              hi = mid;
          } else {
              lo = mid+1;
          }
      }
      return hi;
  }
}
