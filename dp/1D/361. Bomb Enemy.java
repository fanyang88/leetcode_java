/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
*/


/*
   0 1 2 3
  --------
0| 0 E 0 0
1| E 0 W E
2| 0 E 0 0
 
e.g: if we at (0, 0)  we can caculate the rowKill = 1  colKill[0] = 1
     when we at (0,1) rowKill = prevRowKill = 1 colKill[1] = 2
     when we at (0,2) rowKill = prevRowKill = 1 colKill[2] = 0
     when we at (0,3) rowKill = prevRowKill = 1 colKill[3] = 1
    
    come to second row:
    when we at (1,0) since j=0, caclaute rowKill = 1 since grid[i-1][j] !=W, colKill[0] = precolKill[0]=1
    when we at (1,1) since j-1 not W, rowKill = prev= 1 since grid[i-1][j] !=W, colKill[1] still= 2
    when we at (1,2) since j-1 not W, rowKill = prev= 1 since grid[i-1][j] !=W, colKill[2] still= 0
    when we at (1,3) since j-1 is W, rowKill = 1 since grid[i-1][j] !=W, colKill[2] still= 0
    
    so the algorithm is 
    if i=0 or gird[i-1][j] === 'W'  we need to recacluate how many E in colKill[j], otherwise, we can use the prev colKill value
    if j=0 or gird[i][j-1] === 'W'  we need to recacluate how many E in rowKill, otherwise, we can use the prev rowKill value
 
*/
class Solution {
  public int maxKilledEnemies(char[][] grid) {
      if(grid.length==0 || grid[0].length==0)  return 0;
      // for the first row, we need to caculate for each col
      int m =grid.length, n = grid[0].length, rowKill=0, maxV=0;
      int[] colKill= new int[n];
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              
              if(i==0 || grid[i-1][j] == 'W') {
                  // recalcuate colKill
                  colKill[j]=0;
                  for(int k=i; k<m && grid[k][j] !='W'; k++) {
                      if(grid[k][j]=='E') colKill[j]++;
                  }
              }
              if(j==0 || grid[i][j-1] == 'W') {
                  rowKill=0;
                  for(int k=j; k<n && grid[i][k] !='W'; k++) {
                      if(grid[i][k]=='E')  rowKill++;
                  }
              }
              if(grid[i][j]=='0') maxV = Math.max(maxV, rowKill+colKill[j]);
          }
      }
      return maxV;
  }
}
