/*
Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.
 

 

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.
 

 

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.
 

 

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.

*/

/*
each input is col1, row1, col2, row2  -> 4 pointers: (row1, col1), (row1, col2), (row2, col1), (row2, col2)
                 3 . 4 .  5
         10 |-------------|
            | .  |   |    |
           1|----|---|9   |
            |   2    |    |
            |--------|----|
            6 .      7 .  8
            
        1th rec: record (10, 1, 2, 3)
        2th rec: record (4, 9) since 2 exist delete 2, since 3 exist delete 3  set=(10,1, 4, 9)
        3th rec: record (7, 8, 5) since 4 exist  delete 4, set=(10,1,9,7,8,5)
        4th rec: record (6) since 1 exist, delte 1, since 7 exist, delete 7, since 9 exist, delete 9
        set={10,6,8,5}  which is the maxX, maxY, minX, and minY   
        
        The right answer must satisfy two conditions:
    the large rectangle area should be equal to the sum of small rectangles
    count of all the points should be even, and that of all the four corner points should be one
*/


class Solution {
  public boolean isRectangleCover(int[][] rectangles) {
      int area = 0, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE,minY= Integer.MAX_VALUE;
      Set<String> set = new HashSet<>();
      
      for(int[] rec: rectangles) {
          int col1=rec[0], row1= rec[1], col2=rec[2], row2=rec[3];
          area += (col2-col1)*(row2-row1);
          maxX = Math.max(maxX, Math.max(row1, row2));
          minX = Math.min(minX, Math.min(row1, row2));
          maxY = Math.max(maxY, Math.max(col1, col2));
          minY = Math.min(minY, Math.min(col1, col2));
          String[] strs = {col1+":"+row1, col1+":"+row2, col2+":"+row1, col2+":"+row2};
          for(String s: strs) {
              if(!set.contains(s)) {
                  set.add(s);
              } else {
                  set.remove(s);
              }
          }
      }
      if(set.size() != 4 || !set.contains(minY+":"+maxX) || !set.contains(minY+":"+minX) || !set.contains(maxY+":"+minX) || !set.contains(maxY+":"+maxX))  return false;
      return area == (maxX - minX) *(maxY - minY);
  }
}