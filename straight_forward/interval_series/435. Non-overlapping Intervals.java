/*
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 
 e.g:  [1,2], [2,3], [3,4], [1,3] 
 sort: [1,2] [1,3] [2,3] [3,4]
        start=1 end=2 
        [1,3] since 1<end means there is a overlapping
        [2,3] since 2=end count++=2, means this is non-overlap one, end= 3
        [3,4] since 3=end count++=3, means this is non-overlap one, end= 4
        
        res = 4-3=1
        
 */
class Solution {
  public int eraseOverlapIntervals(Interval[] intervals) {
      int n= intervals.length;
      if(n==0)  return 0;
      int count=1;  // the first one is not overlapping
      // count all overlapping ones, the total - nonoverlapping =  overlapp number
      // sort all intervals first 
      Arrays.sort(intervals, (Interval a, Interval b) -> a.end - b.end);
      int start = intervals[0].start, end = intervals[0].end;
      for(int i=1; i<n; i++) {
          if(intervals[i].start >= end) {
              count++;
              end = intervals[i].end;
          }
      }
      return n - count;
  }
}
