/*
Design a Tic-tac-toe game that is played between two players on a n x n grid. You may assume the following rules:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
TicTacToe toe = new TicTacToe(3);
toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |
toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |
toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|
toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|
toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|
toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|
toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
*/

/*
  we use -1 to denote player 1, 1 to denote player2
  rows[n]: rows[i] means the sum of rows[i]
  cols[n]: cols[i] means the sum of cols[i]
  diag=0
  
*/

class TicTacToe {
  int[] rows, cols;
  int diag, antiDiag, size;
  public TicTacToe(int n) {
      rows = new int[n];
      cols = new int[n];
      size=n;
  }
  
  /** Player {player} makes a move at ({row}, {col}).
      @return The current winning condition, can be either:
              0: No one wins.
              1: Player 1 wins.
              2: Player 2 wins. */
  public int move(int row, int col, int player) {        
       int score = player == 1?-1:1;
       rows[row]+=score;
       cols[col]+=score;
       if(row ==col) diag+=score;
       if(col+row == size-1) antiDiag += score;
       if(Math.abs(rows[row])==size || Math.abs(cols[col])==size || 
         Math.abs(diag)==size || Math.abs(antiDiag)==size) 
          return player;
      return 0;
  }
}

/**
* Your TicTacToe object will be instantiated and called as such:
* TicTacToe obj = new TicTacToe(n);
* int param_1 = obj.move(row,col,player);
*/