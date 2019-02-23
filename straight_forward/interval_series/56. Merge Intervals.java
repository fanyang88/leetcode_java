/**
 Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

 /**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 [1,3] & [2,6]  next start < prev end update start and end
 */
class Solution {
  public List<Interval> merge(List<Interval> intervals) {
      int n = intervals.size();
      if(n <= 1) return intervals;
      List<Interval> res= new LinkedList<Interval>();
      intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
      int start = intervals.get(0).start, end = intervals.get(0).end;
      
      for(int i=1; i<n; i++) {
          Interval cur = intervals.get(i);
          if(cur.start <= end) {
              end = Math.max(end, cur.end);
          } else {
              Interval interval = new Interval(start, end);
              res.add(interval);
              start = cur.start;
              end = cur.end;
          }
      }
      // add the last one
      Interval interval = new Interval(start, end);
      res.add(interval);
      return res;
  }
}
