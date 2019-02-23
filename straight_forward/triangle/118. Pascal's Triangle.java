/**
 * 
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */

 /*
[1],
[1,1],
[1,2,1],
[1,3,3,1],
[1,4,6,4,1]
*/

class Solution {
  public List<List<Integer>> generate(int numRows) {
      List<List<Integer>> res = new LinkedList<>();
      if(numRows == 0)  return res;
      List<Integer> cur = new ArrayList<Integer>();
      cur.add(1);
      res.add(cur);
      for(int i=2; i<=numRows; i++) {
          List<Integer> temp = new ArrayList<Integer>();
          for(int j=0; j<=cur.size(); j++) {
              if(j==0) {
                  temp.add(cur.get(j));
              } else if(j==cur.size()) {
                  temp.add(cur.get(j-1));
              } else {
                  temp.add(cur.get(j-1) + cur.get(j));
              }
          }
          cur = temp;
          res.add(cur);
      }
      return res;
  }
}