/*
A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

*/

/*

Basic idea:
If we start from sx,sy, it will be hard to find tx, ty.
If we start from tx,ty, we can find only one path to go back to sx, sy.
I cut down one by one at first and I got TLE. So I came up with remainder.

First line:
if 2 target points are still bigger than 2 starting point, we reduce target points.
Second line:
check if we reduce target points to (x, y+kx) or (x+ky, y)

since if tx > ty means current tx = pre_tx + ty*k  pre_tx = tx % ty
      else means current ty = pre_ty + tx*k  pre_ty = ty % tx
    
    if current tx = sx, sy < ty, we need to check if sy+k(sx)==ty -> (ty-sy) % sx==0 
    if current ty = sy, sx < tx, we need to check if sx+k(sy)==tx -> (tx-sx) % sy==0 
      
*/

class Solution {
  public boolean reachingPoints(int sx, int sy, int tx, int ty) {
      while(sx < tx && sy< ty) {
          if(tx < ty) ty = ty % tx;
          else tx = tx % ty;
      }
      if(sx ==tx && sy < ty && (ty - sy) % sx ==0) return true;
      if(sy ==ty && sx < tx && (tx - sx) % sy ==0) return true;
      return false;
      
  }
}
