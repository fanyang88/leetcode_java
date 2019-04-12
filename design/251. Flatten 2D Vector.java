/*
Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.

 

Example:

Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // return 1
iterator.next(); // return 2
iterator.next(); // return 3
iterator.hasNext(); // return true
iterator.hasNext(); // return true
iterator.next(); // return 4
iterator.hasNext(); // return false
 

Notes:

Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d vector when next() is called.
 
*/
class Vector2D {
  int row, col;
  int[][] v;
  public Vector2D(int[][] v) {
      this.v = v;
      row=0;
      col=0;
  }
  
  public int next() {
      int val= v[row][col++];
      if(row < v.length && col == v[row].length) {
          row++;
          col=0;
      }
      return val;
  }
  
  public boolean hasNext() {
      while(row < v.length) {
          if(col < v[row].length) return true;
          row++;
          col=0;
      }
      return false;
  }
}

/**
* Your Vector2D object will be instantiated and called as such:
* Vector2D obj = new Vector2D(v);
* int param_1 = obj.next();
* boolean param_2 = obj.hasNext();
*/
