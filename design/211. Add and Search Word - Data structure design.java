/**
 * Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */

class Node {
  boolean isEnd;
  Node[] children;
  Node() {
      isEnd = true;
      children = new Node[26];
  }
}

class WordDictionary {

  Node root;
  /** Initialize your data structure here. */
  public WordDictionary() {
      root = new Node();
  }
  
  /** Adds a word into the data structure. */
  public void addWord(String word) {
      Node cur = root;
      for(int i=0; i<word.length(); i++) {
          int index = word.charAt(i) - 'a';
          if(cur.children[index] == null) {
              cur.children[index]= new Node();
              cur.children[index].isEnd= false;
          }
          cur = cur.children[index];
      }
      cur.isEnd = true;
  }
  
  /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
  public boolean search(String word) {
      return dfs(0, word, root);
  }
  
  public boolean dfs(int index, String word, Node root) {
      if(index == word.length()) {
          return root.isEnd;
      }
      // if it is not a '.'
      if(word.charAt(index) != '.') {
          int i = word.charAt(index) - 'a';
          if(root.children[i] == null)  return false;
          return dfs(index+1, word, root.children[i]);
      } else {
          for(int i=0; i<26; i++) {
              if(root.children[i] == null)  continue;
              if(dfs(index+1, word, root.children[i]))  return true;
          }
      }
      return false;
  }
}

/**
* Your WordDictionary object will be instantiated and called as such:
* WordDictionary obj = new WordDictionary();
* obj.addWord(word);
* boolean param_2 = obj.search(word);
*/
