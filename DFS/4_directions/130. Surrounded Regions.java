/**
 * Given a 2D board containing 'X' and 'O' (the letter O), 
 * capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, 
which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */


/*
 find the unsurrounded area first, find the four side areas first
 and use dfs to make four sides 0 and related one to be 'U'
 then we examine the board, turn all 0 to X
 at last, then all 'U' back to '0'
 
    X X X X
    X O O X
    X X O X
    X O X X
to be:
    X X X X
    X O O X
    X X O X
    X U X X
to be:    
    X X X X
    X X X X
    X X X X
    X O X X
*/

class Solution {
  public void solve(char[][] board) {
      if(board.length==0)  return;
      int m = board.length, n= board[0].length;
      // turn all 0 connected to U or on border to be U
      for(int i=0; i<m; i++) {
          dfs(board, i, 0, m, n);
          dfs(board, i, n-1, m, n);
      }
      for(int i=1; i<n-1; i++) {
          dfs(board, 0, i, m, n);
          dfs(board, m-1, i, m, n);
      }
      // turn all 'O' to 'X'
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(board[i][j] == 'O') board[i][j] = 'X';
          }
      }
      
      // turn all 'U' to 'O'
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(board[i][j] == 'U') board[i][j] = 'O';
          }
      }
  }
  
  public void dfs(char[][] board, int i, int j, int m, int n) {
      if(i<0 || i>=m ||j<0 || j>=n || board[i][j] == 'X' || board[i][j] == 'U') return;
      board[i][j] = 'U';
      dfs(board, i+1, j, m, n);
      dfs(board, i-1, j, m, n);
      dfs(board, i, j+1, m, n);
      dfs(board, i, j-1, m, n);
  }
}