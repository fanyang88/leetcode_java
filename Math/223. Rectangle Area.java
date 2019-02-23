/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45
Note:

Assume that the total area is never beyond the maximum possible value of int.
 */

 /*
    get area of ABCD, get area of EFGH
    get the area in overlap
    areaABCD + areaEFGH - overlap
*/

class Solution {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
      int left = Math.max(A, E);
      int right = Math.max(left, Math.min(C, G));
      int top = Math.min(D, H);
      int bottom = Math.min(top, Math.max(B, F));
      int overlap = Math.abs(right - left) * Math.abs(top-bottom);
      return Math.abs(C-A) * Math.abs(D-B) + Math.abs(E-G) * Math.abs(F-H) - overlap;
  }
}
