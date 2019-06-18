/*
You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:

Input: [2,1,1,2]

?????
?   ?
???????>
    ?

Input: true 
Explanation: self crossing
Example 2:

Input: [1,2,3,4]

????????
?      ?
?
?
?????????????>

Output: false 
Explanation: not self crossing
Example 3:

Input: [1,1,1,1]

?????
?   ?
?????>

Output: true 
Explanation: self crossing
*/

/*
    use a hashset to record postion have visited, if there is a dup, reutrn false
    [2,1,1,2]
    (0,0) add to set
    x[0] to north: (-1,0), (-2, 0) add to set
    x[1] to west: (-2,-1), add to set
    x[2] to south: (-1,-1) add to set
    x[3] to east: (-1, 0) visited return true
     1
    ---
  2 | |0       pattern 1:  Fourth line crosses first line and onward
    -------                x[0] >=x[1] && x[2] <= x[0] return true
      |   3              when i=3 x[i] >=x[i-2] && x[i-1] <=x[i-3] 
      
     1 
    ---
    | |           pattern 2:   Fifth line meets first line and onward, 
   2| ||          x[3] ==x[1] && x[4]+x[0] >= x[2] means there is duplicate part from x[0] and x[4]   
    | || 4        when i=4 x[i-1]==x[i-3] x[i]+x[i-4] >=x[i-2]
    ---
     3
     
       1
    |-----| 5
    |   ------      pattern 3:   Sixth line cross first line and onward, 
    |    0|  |      x[0] +x[4]>=x[2] x[4]<=x[2]  x[1]+x[5]>=x[3]  x[1]<=x[3] 
   2|        |4     
    |--------|
         3
*/

class Solution {
  public boolean isSelfCrossing(int[] x) {
       if (x.length <= 3) return false;
      
      for(int i=3; i<x.length; i++) {
          if(x[i] >=x[i-2] && x[i-1] <=x[i-3])  return true;
          if(i>=4) {
              if(x[i-1]==x[i-3] && x[i]+x[i-4] >=x[i-2]) return true;
          }
          if(i>=5) {
              if(x[i]+x[i-1]>=x[i-3] && x[i-1]<=x[i-3] && x[i-4]+x[i]>=x[i-2] && x[i-4]<=x[i-2])  return true;
          }
      }
      return false;
  }
}
