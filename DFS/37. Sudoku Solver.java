/**
 Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

 */

class Solution {
  public void solveSudoku(char[][] board) {
      if(board == null || board.length ==0)  return;
      dfs(board);
  }
  
  public boolean dfs(char[][] board) {
      for(int i=0; i<9; i++) {
          for(int j=0; j<9; j++) {
              if(board[i][j] != '.')  continue;
              for(char k='1'; k<='9'; k++) {
                  if(isValid(board, i, j, k)) {
                      board[i][j] = k;
                      
                      if(dfs(board)) {
                          // If all numbers have filled, find the solution
                          return true;
                      } 
                       else { //Otherwise go back
                           board[i][j] = '.';
                       }  
                  }
              }
              // If all 1-9 has tried
              return false;
          }
      }
      return true;
  }
  
  public boolean isValid(char[][] board, int i, int j, char k) {
      for(int x=0; x<9; x++) {
          if(board[i][x] == k || board[x][j] == k)  return false;
          int si = (i/3)*3, sj=(j/3)*3;
          for(int m=si; m<si+3; m++) {
              for(int n=sj; n<sj+3; n++) {
                  if(board[m][n] == k)  return false;
              }
          }
      }
      return true;
  }
}

