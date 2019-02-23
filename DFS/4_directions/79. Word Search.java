/**
 Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */

class Solution {
  static boolean[][] visited;
  public boolean exist(char[][] board, String word) {
      int m= board.length, n = board[0].length;
      visited = new boolean[m][n];
      // start from each position, keep a visited set to record places has been visited in dfs
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(dfs(board, i, j, m, n, 0, word)) return true;
          }
      }
      return false;
  }
  
  public boolean dfs(char[][] board, int x, int y, int m, int n, int cur, String word) {
      if(cur == word.length())  return true;
      if(x < 0 || x >= m || y< 0 || y>=n || board[x][y] != word.charAt(cur) || visited[x][y])  return false;
      visited[x][y] = true;
      boolean answer= dfs(board, x, y+1, m, n, cur+1, word) ||
                      dfs(board, x+1, y, m, n, cur+1, word) ||
                      dfs(board, x-1, y, m, n, cur+1, word) ||
                      dfs(board, x, y-1, m, n, cur+1, word);
      visited[x][y] = false;
      return answer;
  }
}
