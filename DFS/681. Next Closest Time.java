/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
*/

// use dfs to generate all the combination and find the next larger one
/*
    e.g: 1934 ->1349
    we can get combination: 1111, 1333, 1344, 1349, 3333, .....
    
    examine each combination to get the next larger or the minimum, 
    if there is no larger one, than the minimum is the answer 
*/

class Solution {
  List<String> res = new ArrayList<>();
  public String nextClosestTime(String time) {
      int diff = Integer.MAX_VALUE;
      String next = "", min= time;
      time = time.substring(0, time.indexOf(":")) + time.substring(time.indexOf(":")+1);
      dfs("", time);
      for(String t: res) {
          if(t.compareTo(time) > 0 && Integer.valueOf(t) - Integer.valueOf(time) < diff) {
              diff = Integer.valueOf(t) - Integer.valueOf(time);
              next = t;
          }
          if(t.compareTo(min) < 0) {
              min = t;
          }
      }
      return next=="" ? min.substring(0,2) + ":" + min.substring(2,4) 
              : next.substring(0,2) + ":" + next.substring(2,4);
  }
  
  public void dfs(String cur, String time) {
      if(cur.length()==4) {
          res.add(cur);
          return;
      }
      for(int i=0; i<4; i++) {
          // pruning
          if(cur.length() >=2) {
              int hour = Integer.valueOf(cur.substring(0,2));
              if(hour >=24) continue;
          }
          if(cur.length() >=3 && cur.charAt(2) >= '6') continue;
          dfs(cur+time.charAt(i), time);
      }
  }
}