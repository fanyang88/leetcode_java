/**
 Design a class which receives a list of words in the constructor, 
 and implements a method that takes two words word1 and word2 
 and return the shortest distance between these two words in the list. 
 Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

class WordDistance {

  Map<String, List<Integer>> map;
  public WordDistance(String[] words) {
      map = new HashMap<String, List<Integer>>();
      for(int i=0; i<words.length; i++) {
          if(map.get(words[i]) == null) {
              map.put(words[i], new ArrayList<Integer>());
          }
          map.get(words[i]).add(i);
      }
  }
  
  public int shortest(String word1, String word2) {
      List<Integer> l1 = map.get(word1);
      List<Integer> l2 = map.get(word2);
      int minV = Integer.MAX_VALUE;
      for(int i=0, j=0; i<l1.size() && j<l2.size(); ) {
          int ind1 = l1.get(i);
          int ind2 = l2.get(j);
          minV = Math.min(minV, Math.abs(ind1 - ind2));
          if(ind1 < ind2) {
              i++;
          } else {
              j++;
          }
      }
      return minV;
  }
}

/**
* Your WordDistance object will be instantiated and called as such:
* WordDistance obj = new WordDistance(words);
* int param_1 = obj.shortest(word1,word2);
*/