/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
*/

/*

        The soultion is calcuated by doing a product of the length of
	each string to every other string. Anyhow the constraint given is
	that the two strings should not have any common character. This
	is taken care by creating a unique number for every string. Image
	a an 32 bit integer where 0 bit corresponds to 'a', 1st bit
	corresponds to 'b' and so on.
	 
	Thus if two strings contain the same character when we do and
	"AND" the result will not be zero and we can ignore that case
    
        get bit representation:
        aba
        a:  1<<0=1 0|1=1 value=1
        b: 1<<(1)=10 1|10=11 value=11
        a: 1<<0=0 11|0=11
        means it have a and b
        
        abc
        a: 1<<0=1 0|1=1 value=1
        b: 1<<(1)=10 1|10=11 value=11
        c: 1<<2=100 11|100=111 value=111
*/

class Solution {
  public int maxProduct(String[] words) {
      int maxV = 0;
      int[] wordBits = new int[words.length];
      for(int i=0; i<words.length; i++) {
          wordBits[i] = getBitRepresentation(words[i]);
      }
      
      // comparison
      for(int i=0; i<words.length; i++) {
          for(int j=i+1; j<words.length; j++) {
              if((wordBits[i] & wordBits[j]) ==0) { // they don't have common elements, need bracket ()
                  maxV = Math.max(maxV, words[i].length() * words[j].length());
              }
          }
      }
      return maxV;
  }
  
  public int getBitRepresentation(String s) {
      int value = 0;
      for(char c : s.toCharArray()) {
          value = value | (1 << (c-'a'));
      }
      return value;
  }
}
