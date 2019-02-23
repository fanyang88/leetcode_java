/**
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */


/*
    wordList = [hot","dot","dog","lot","log","cog]
    using bfs
    st = [hit]
    size = 1 cur =hit since hot in wordList, wordList remove hot, st push[hot] level++
    size = 1 cur=hot since dot in wordList, wordlist remove dot, st push[dot]
                     ....
    
*/

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      Queue<String> q = new LinkedList<String>();
      q.add(beginWord);
      wordList.remove(beginWord);
      int level=1;
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              String cur = q.poll();
              if(cur.equals(endWord))  return level;
              for(String neighbor: getNeighbors(cur, wordList)) {
                  q.add(neighbor);
                  wordList.remove(neighbor);
              }
          }
          level++;
      } 
      return 0;
  }
  
  public List<String> getNeighbors(String str, List<String> wordList) {
      List<String> res = new ArrayList<String>();
      for(int i=0; i<str.length(); i++) {
          char[] chars = str.toCharArray();
           for (char c = 'a'; c <= 'z'; c++) {
              chars[i] = c;
              String word = new String(chars);
              if(wordList.contains(word)) res.add(word);
          }   
      }
      return res;
  }
}