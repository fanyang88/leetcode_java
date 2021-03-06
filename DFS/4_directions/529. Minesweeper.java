/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
 

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

 

Note:

The range of the input matrix's height and width is [1,50].
The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
The input board won't be a stage when game is over (some mines have been revealed).
For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.

*/

/*
 the rule is
 if x, y is a bomb, turn it to X and return
 if there is bomb around (x, y), turn it to number of bombs around it
 if there is no bomb around (x, y), turn it to 'B' and continue to check its surrounding
*/

class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}, {1, 0}, {1, -1}, {1, 1}};
  public char[][] updateBoard(char[][] board, int[] click) {
      int m = board.length,  n = board[0].length;
      if(board[click[0]][click[1]] == 'M') {
          board[click[0]][click[1]] = 'X';
          return board;
      }
      dfs(click[0], click[1], board, m, n);
      return board;
  }
  
  public void dfs(int x, int y, char[][] board, int m, int n) {
      if(x < 0 || x >=m || y<0 || y>=n || board[x][y] != 'E')  return;
      int numOfBombs = getBombs(x, y, board, m, n);
      if(numOfBombs !=0) {
          board[x][y] = (char)(numOfBombs+ '0');
          return;
      }
      board[x][y] = 'B';
      for(int[] d: dirs) {
           dfs(x+d[0], y+d[1], board, m, n);
      }
  }
  
  public int getBombs(int x, int y, char[][] board, int m, int n) {
      int count=0;
      for(int[] d: dirs) {
          int nx = x + d[0], ny = y + d[1];
          if(nx >=0 && ny>=0 && nx<m && ny<n && (board[nx][ny] == 'M' ||
                                                 board[nx][ny] == 'X')) // This is the key, even if it is M
              count++;
      }
      return count;
  }
}