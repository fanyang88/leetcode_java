/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".


*/

/*
         s = "++++"
         /     |      \
       --++   +--+    ++--
        |     win      |
       ----           ----
        lose          lose
        
if there is a win condition, return true

we can use a map to cache string to save time.
*/

class Solution {
  Map<String, Boolean> map = new HashMap<String, Boolean>();
  public boolean canWin(String s) {
      
      if(map.get(s) != null)  return map.get(s);
      if(s.length() < 2)  {
          map.put(s, false);
          return false;
      }
      
      for(int i=0; i<s.length()-1; i++) {
          if(s.startsWith("++", i)) {
              String t = s.substring(0, i) + "--" + s.substring(i+2);
              if(!canWin(t))  {  // the opponent lose, means we win
                  map.put(s, true);
                  return map.get(s); 
              }
          }
      }
      map.put(s, false);
      return false;
  }
}
