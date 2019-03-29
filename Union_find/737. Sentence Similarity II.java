/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 
*/

class Solution {
  public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
      if(words1.length !=words2.length) return false;
      // use uion find, but use map instead
      Map<String, String> roots = new HashMap<>(); // key is string, value is root
      for(String[] p: pairs) {
          String superRoot0 = find(p[0], roots);
          String superRoot1 = find(p[1], roots);
          roots.put(superRoot0, superRoot1);
      }
      
      for(int i=0; i<words1.length; i++) {
          if(!words1[i].equals(words2[i]) && !find(words1[i], roots).equals(find(words2[i], roots))) return false;
      }
      return true;
  }
  
  public String find(String word, Map<String, String> roots) {
      if(roots.get(word)==null) roots.put(word, word);
      return roots.get(word).equals(word) ? word : find(roots.get(word), roots);
  }
}
