/*
In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:

Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
 

Note:

The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000
 
*/

class Solution {
    
  public String replaceWords(List<String> dict, String sentence) {
      Node root = new Node();
      String res = "";
      for(String s: dict) {
          insert(s, root);
      }
      for(String s: sentence.split(" ")) {
          String replace = search(s, root);
          res += replace == "" ? s+" " : replace + " ";
      }
      return res.substring(0, res.length()-1);// remove the last space
  }
  
  class Node {
      boolean isEnd;
      Node[] children;
      Node() {
          isEnd= false;
          children= new Node[26];
      }
  }
  
  public void insert(String s, Node node) {
      for(char c: s.toCharArray()) {
          int index = c - 'a';
          if(node.children[index] == null) node.children[index] = new Node();
          node = node.children[index];
      }
      node.isEnd =true;
  }
  
  public String search(String s, Node node) {
      String res= "";
      for(char c: s.toCharArray()) {
          int index = c - 'a';
          res += c;
          if(node.children[index] != null) {
              if(node.children[index].isEnd)  return res;
              else node = node.children[index];
          } else {
              return "";
          }
      }
      return s;
  }
}
