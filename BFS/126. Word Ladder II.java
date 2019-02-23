/**
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */

 /*
based on word ladder I, we need to construct two more thing on Bfs
 1. hashmap to map each string to its neighbors [string: [neigbors]]
 2. hashmap to map each string to distance to beginword [string: length]
 then we use dfs to find out all the shortest pathes.
 
 e.g: we should construct the map first
lot:[dot, log, hot]
hit:[hot]
log:[cog, dog, lot]
dot:[dog, hot, lot]
cog:[]
hot:[dot, hit, lot]
dog:[cog, log, dot]

distance: 
lot:2
hit:0
log:3
dot:2
cog:4
hot:1
dog:3
*/

        
class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      List<List<String>> res = new LinkedList<>();
      Map<String, List<String>> neighbors = new HashMap<String, List<String>>();
      Map<String, Integer> distance = new HashMap<String, Integer>();
      HashSet<String> dict = new HashSet<String>(wordList);
      dict.add(beginWord); // This is the key
      // Construct two hashmaps
      bfs(beginWord, endWord, dict, neighbors, distance);
      // find shorest path based on two maps
      dfs(beginWord, endWord, neighbors, distance, new ArrayList<String>(), res);
      return res;
  }
  
  public void bfs(String begin, String end, HashSet<String> dict, Map<String, List<String>> neighbors, Map<String, Integer> distance) {
      Queue<String> q = new LinkedList<>();
      for(String str: dict) {
          neighbors.put(str, new ArrayList<>());
      }
      q.offer(begin);
      distance.put(begin, 0);
      int level=0, stop=0;
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              String cur = q.poll();
            
              for(String next: getNexts(cur, dict)) {
                  neighbors.get(cur).add(next);
                  if(distance.containsKey(next)) continue; // visited
                  distance.put(next, level+1);
                  if(next == end) {
                      stop=1;
                  } else {
                      q.offer(next);
                  }
              }
          }
          level++;
          if(stop==1)  break; //This is the key!!!
      }
  }
  
  public void dfs(String begin, String end, Map<String, List<String>> neighbors, Map<String, Integer> distance, List<String> path, List<List<String>> res) {
      path.add(begin);
      if(begin.equals(end)) {
          res.add(new ArrayList<String>(path));
      } else {
          for(String next: neighbors.get(begin)) {
              if(distance.get(next) != distance.get(begin)+1) continue;
              dfs(next, end, neighbors, distance, path, res);
          }
      }
      path.remove(path.size()-1);
  }
  
  public List<String> getNexts(String str, HashSet<String> dict) {
      List<String> res = new ArrayList<String>();
      for(int i=0; i<str.length(); i++) {
          StringBuilder sb = new StringBuilder(str);
          for(char ch= 'a'; ch <= 'z'; ch++) {
              sb.setCharAt(i, ch);
              String word = sb.toString();
              if(dict.contains(word) && !word.equals(str)) res.add(word);
          }
      }
      return res;
  }
}