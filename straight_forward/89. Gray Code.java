/**
 The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, 
print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:

Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
Example 2:

Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].

 */


/*
 when n=1, we have 0, 1
 when n=2, 1<<1 = 10, 10+0=10 10+1=11 plus 0, 1 we have 00, 01, 10, 11
 when n=3, 10<<1 = 100, 0, 01, 10, 11 -> 100, 101, 110, 111, plus 00, 01, 10, 11
 
*/
class Solution {
  public List<Integer> grayCode(int n) {
      List<Integer> res = new ArrayList<Integer>();
      res.add(0);
      
      for(int i=0; i<n; i++) {
          int size =res.size(); // need to extract size here since it changes in next loop
          for(int k=size-1; k>=0; k--) {
              res.add(res.get(k) + (1 <<i));
          }
      }
      return res;
  }
}
