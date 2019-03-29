/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
*/

/*
    indexing + binary search
    e.g: abcda
        a: [0, 4]
        b: [1]
        c: [2]
        d: [3]
        for word acd
        we start from 0, sicne we can find at 0 there is a, i=1
        we start from 1, find in the array[2]. see if there is a value >=1, found 2, index=3
        we start from 3, find in array[3], see if there is a value>=3, found 3, this word is a subsequence
        
*/

class Solution {
  public int numMatchingSubseq(String S, String[] words) {
      int count=0;
      Map<Character, List<Integer>> map = new HashMap<>();
      for(int i=0; i<S.length(); i++) {
          char c = S.charAt(i);
          if(map.get(c) ==null) map.put(c, new ArrayList<>());
          map.get(c).add(i);
      }
      for(String w: words) {
          if(isSubSequence(w, map)) count++;
      }
      return count;
  }
  
  public boolean isSubSequence(String s, Map<Character, List<Integer>> map) {
      int index=0;
      for(char c: s.toCharArray()) {
          if(map.get(c) ==null) return false;
          // find an index with number>=index+1 in the list
          int nextIndex = BinarySearch(map.get(c), index);
          if(nextIndex == -1) return false;
          index = nextIndex+1;
      }
      return true;
  }
  
  public int BinarySearch(List<Integer> list, int target) {
      int lo = 0, hi = list.size()-1;
      while(lo < hi) {
          int mid = lo + (hi - lo) /2;
          if(list.get(mid) < target) {
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return list.get(hi) >= target ? list.get(hi) : -1;
  }
}
