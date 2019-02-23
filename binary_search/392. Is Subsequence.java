/*
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
*/

/*
    we can use a hashmap to record each postion for char in t.
    then for each chr in s, using binary search to find the position for each chr, if any chr can not be found, return false.
 .eg: s = "hc", t = "achbhdc"   map = {a: [0], b: [3], h: [2, 4], d: [5] c: [1, 6]}
  start = -1, find in [2,4] the first larger than -1 is 2, start=2
  start=2, find in [1, 6] the first larger than 2 is 6, start = 6, return true.

*/

class Solution {
  public boolean isSubsequence(String s, String t) {
      Map<Character, List<Integer>> map = new HashMap<>();
      for(int i=0; i<t.length(); i++) {
          char c = t.charAt(i);
          if(!map.containsKey(c))  map.put(c, new ArrayList<>());
          map.get(c).add(i);
      }
      
      int start=0;
      // use binary search
      for(char c : s.toCharArray()) {
          if(!map.containsKey(c)) return false;
          int pos = binarySearch(map.get(c), start); // check if we can find a value larger than start
          if(pos ==-1)  return false;
          start = pos+1;
      }
      return true;
  }
  
  public int binarySearch(List<Integer> list, int target) {
      int lo =0, hi = list.size()-1;
      while(lo < hi) {
          int mid = (lo + hi)/2;
          if(list.get(mid) >= target) {
              hi = mid;
          } else {
              lo = mid+1;
          }
      }
      return list.get(hi) >= target ? list.get(hi) : -1;
  }
}
