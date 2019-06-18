/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 
 [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 sort first:
 [1,2] [1,3] [4, 10] [5, 6]
 then merge
 [1,3] [4,10]
 
 freetime: [-inf, 1], [3, 4], [10, inf].
 */
class Solution {
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
      List<Interval> all = new ArrayList<>(), res = new ArrayList<>();
      schedule.forEach(e-> all.addAll(e));
      Collections.sort(all,((a,b) -> a.start - b.start));
      
      int end = all.get(0).end;
      for(int i=1; i<all.size(); i++) {
          if(end < all.get(i).start) {  
              res.add(new Interval(end, all.get(i).start));
              end = all.get(i).end;
          } else {
              end = Math.max(end, all.get(i).end);
          }
      }
      return res;
  }
}