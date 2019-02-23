/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/

/*
  need an arraylist to denote the snake
  first need based on the direction to create a new head
  then we check if this new head is out of bound or not, if it is, return -1;
  then we check if this new head is concide with the body(1~length-2), if it is, return -1;
  then we check if there is a food concide with the new head, if it isn't, we remove the tail
                                otherwise, count++
                    
*/
class SnakeGame {

  /** Initialize your data structure here.
      @param width - screen width
      @param height - screen height 
      @param food - A list of food positions
      E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
  int count, width, height;
  List<int[]> snake;
  int[][] food;
  public SnakeGame(int width, int height, int[][] food) {
      snake = new ArrayList<int[]>();
      int[] head = {0,0};
      snake.add(head);
      this.food = food;
      this.width = width;  // if the same name, need to add this.
      this.height = height;
      count=0;
  }
  
  /** Moves the snake.
      @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
      @return The game's score after the move. Return -1 if game over. 
      Game over when snake crosses the screen boundary or bites its body. */
  public int move(String direction) {
      int x = snake.get(0)[0];
      int y = snake.get(0)[1];
      switch(direction) {
          case "R": y++; break;
          case "L": y--; break;
          case "U": x--; break;
          case "D": x++; break;
      }
      if(x >= height || x<0 || y >=width || y<0)  return -1;
      // check if new head touch the body
      for(int i=1; i<snake.size()-1; i++) {
          if(x == snake.get(i)[0] && y == snake.get(i)[1]) return -1;
      }
      
      // safely add new head
      int[] newHead = {x, y};
      snake.add(0, newHead);
      
      // check foods
      if(count<food.length && food[count][0]==x && food[count][1]==y) {
          count+=1;
      } else {  // remove tail
          snake.remove(snake.size()-1);    
      }
      return count;
  }
}

/**
* Your SnakeGame object will be instantiated and called as such:
* SnakeGame obj = new SnakeGame(width, height, food);
* int param_1 = obj.move(direction);
*/