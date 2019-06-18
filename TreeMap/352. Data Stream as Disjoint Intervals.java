/*
Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 
 use TreeMap, start time would be the key and the actual interval is the value
 e.g: 1: [1,1] 
      3: [3,3]
 case 1: connect to two intervals
 if there is 2 coming in, find the lowest key=1 highest key=3 
    since map(1).end+1=2 2=map(3).start connect two intervals: map(1).end= map(3).end remove map(3)
    map: 1: [1,3]
 case 2: connect to left interval
 if there is 4 coming in, find the lowest key=1 highest key=null
    since map(1).end+1=4=val map.get(1).end = end+1;
    map: 1: [1,4]
 case 3: connect to right interval
 if there is 0 coming in, find the lowest key=null highest key=1
    since highest key == val+1 map.add(val, [val, map.get(key).end])  map.remove(key)
 case 4: not connect to any intervals
 all all the other case, we should add the interval into the treemap directly.
 */
class SummaryRanges {
  TreeMap<Integer, Interval> map;
  /** Initialize your data structure here. */
  public SummaryRanges() {
      map = new TreeMap<>();
  }
  
  public void addNum(int val) {
       if(map.containsKey(val)) return;
      Integer lo = map.lowerKey(val);
      Integer hi = map.higherKey(val);  // logn
      if(lo != null && hi != null && map.get(lo).end+1==val && hi==val+1) {
          map.get(lo).end = map.get(hi).end;
          map.remove(hi);
      } else if(lo != null && map.get(lo).end +1 >= val) {
          map.get(lo).end = Math.max(map.get(lo).end, val);
      } else if(hi != null && hi == val+1) {
          map.put(val, new Interval(val, map.get(hi).end));
          map.remove(hi);
      } else {
          map.put(val, new Interval(val, val));
      }
  }
  
  public List<Interval> getIntervals() {
      return new ArrayList<>(map.values());
  }
}

/**
* Your SummaryRanges object will be instantiated and called as such:
* SummaryRanges obj = new SummaryRanges();
* obj.addNum(val);
* List<Interval> param_2 = obj.getIntervals();
*/