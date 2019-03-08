/*
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
Example 1:

Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
​​​​​
Note:

All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.
*/


/*
if the squirrel starts from the tree, then the distance = 2 * (tree to each nut)
since squirrel is not from the tree, we need to find the nearest nut, get the distance and add that distance to the total and deduct that nut to the tree distance.
For the first nut, 
let a be the distance from Tree to Nut j
let b be the distance from Squirrel to Nut j
We need to remove a from the total, and add b to the total
Therefore we will MINIMIZE the equation (b-a)
*/
class Solution {
  public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
      int min= Integer.MAX_VALUE, total =0;
      // find out the closet nut the squirrel can get first
      for(int i=0; i<nuts.length; i++) {
          total += dist(nuts[i], tree) * 2;
          int d = dist(nuts[i], squirrel) - dist(nuts[i], tree);
          if(min > d) {
              min = d;
          }
      }
      return total + min;
  }
  
  public int dist(int[] p1, int[] p2) {
      return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);
  }
}
