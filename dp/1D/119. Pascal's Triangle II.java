/**
 * 
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
 */

/*
e.g: n=3
i=0, res=1
i=1, res =[1,1]
i=2, res= [1,1,1]  j=i-1, j>0; j--  res.set(j, res.get(j-1) + res.get(j))
i=3,
*/
class Solution {
  public List<Integer> getRow(int rowIndex) {
      List<Integer> res= new ArrayList<Integer>();
      for(int i=0; i<rowIndex+1; i++) {
          res.add(1);
          for(int j=i-1; j>0; j--) {
              res.set(j, res.get(j-1) + res.get(j));
          }
      }
      return res;
  }
}

