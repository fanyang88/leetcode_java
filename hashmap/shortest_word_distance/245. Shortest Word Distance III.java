/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.
 */

 /*
e.g: a,b,a,a,b  w1=a w2=a  index=-1
i=0 index=0
i=1 skip
i=2 since index!=-1 minV= (2-0) index=2
i=3 since index!=-1 minV= (3-2) index=3
i=4 skip

e.g: a,b,a,a,b  w1=a w2=b  index=-1
i=0 index=0
i=1 since index!=-1 and w[0]=a!=w[1]  minV= (1-0) index=1
i=2 since index!=-1 and w[1]=b!=w[2]  minV= (2-1) index=2
i=3 since index!=-1 and w[2]=a==w[3]  just index=3
i=4 since index!=-1 and w[3]=a!=w[4]  minV= (4-3) index=4

*/

class Solution {
  public int shortestWordDistance(String[] words, String word1, String word2) {
      int index = -1, minV = Integer.MAX_VALUE;
      for(int i=0; i<words.length; i++) {
          if(words[i].equals(word1) || words[i].equals(word2)) {
              // When word1 == word2
              if(index != -1 && word2.equals(word1)) {
                  minV = Math.min(minV, i-index);
              } 
              // word[index] != word[i] means the word is different
              else if(index != -1 && !words[index].equals(words[i])) {
                  minV = Math.min(minV, i-index);
              }
              index = i;
          }
      }
      return minV;
  }
}
