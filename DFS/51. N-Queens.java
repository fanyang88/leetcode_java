/**
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

 */

 /*
    for each col at level row level we try to add Q in different places
            Q .          .Q .       ..Q.      ...Q .
    /       |      \
. Q      . . Q     ...Q

The core:
dfs(row, board) {
    for(col: 0~n-1) {
        isValid() {
            board[row][col] = 'Q'
            dfs(row+1, board)  // continue to next row
            board[row][col] = '.' 
        }
    }
}
    
*/
class Solution {
  public List<List<String>> solveNQueens(int n) {
      // init board 
      char[][] board = new char[n][n];
      for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) 
              board[i][j] = '.';
      }
      List<List<String>> res= new ArrayList<List<String>>();
      dfs(0, board, res, n);
      return res;
  }
  
  public void dfs(int row, char[][] board, List<List<String>> res, int n) {
      if(row == n) {
          res.add(construct(board, n));
          return;
      }
      for(int col=0; col<n; col++) {
          if(!isValid(row, col, board, n)) continue;
          board[row][col] = 'Q';
          dfs(row+1, board, res, n);
          board[row][col] = '.';
      }
  }
  
  public List<String> construct(char[][] board, int n) {
      List<String> res = new LinkedList<String>();
      for(int i=0; i<n; i++) {
          String s = new String(board[i]);
          res.add(s);
      }
      return res;
  }
  
  public boolean isValid(int row, int col, char[][] board, int n) {
      // check same row
      for(int i=0; i!=col && i<n; i++) {
          if(board[row][i] == 'Q')  return false;
      }
      // check same col
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