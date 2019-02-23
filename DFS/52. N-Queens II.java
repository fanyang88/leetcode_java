/**
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */

class Solution {
  public int totalNQueens(int n) {
      int[] res= new int[1];
      // init board 
      char[][] board = new char[n][n];
      for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) 
              board[i][j] = '.';
      }
      dfs(0, board, n, res);
      return res[0];
  }
  
  public void dfs(int row, char[][] board, int n, int[] res) {
      if(row == n) {
          res[0] ++;//= res[0]+1;
          return;
      }
      for(int col = 0; col<n; col++) {
          if(!isValid(board, col, row, n)) continue;
          board[row][col] = 'Q';
          dfs(row+1, board, n, res);
          board[row][col] = '.';
      }
  }
  
  public boolean isValid(char[][] board, int col, int row, int n) {
      // check same col
      for(int i=0; i!=col && i<n; i++) {
          if(board[row][i] == 'Q')  return false;
      }
      // check same row
      for(int i=0; i!=row && i<n; i++) {
          if(board[i][col] == 'Q')  return false;
      }
      // check diagnal
      for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
          if(board[i][j] == 'Q')  return false;
      }
      // check anti-diagnal
      for(int i=row-1, j=col+1; i>=0 && j<n; i--, j++) {
          if(board[i][j] == 'Q')  return false;
      }
      return true;
  }
}