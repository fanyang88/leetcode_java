/**
 * 
 Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

Example:

Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
 */

    /*
        we can construct a trie based on the words
           |
        / | |  \
       o  p  e   r
      /   |  |    \
     a    e  a     a
    /     |  |      \ 
   t      a  t       i
  /                   \
 h                     n
 
 then we backtrack from each point on the board
 e.g: start from (0, 0) =o make it as visited, find a (0, 1) make it as visited, till we found the h,         add to result, we should make h.word=null, 
        so that we won't go search the same word again, this is the point

Another corner case is there can be [app, apple] in words
so even if we found app, we should allow it continue searching instead of return, since we can found apple along that way as well.
   */

class Solution {
  List<String> res;
  public List<String> findWords(char[][] board, String[] words) {
      res = new ArrayList<>();
      Node root = buildTrie(words);
      int m= board.length, n=board[0].length;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              dfs(i, j, board, root, m, n);
          }
      }
      return res;
  }
  
  public void dfs(int i, int j, char[][] board, Node node, int m, int n) {
      char c = board[i][j];
      if(board[i][j] == '#') return; // this position has visited
      node = node.next[c- 'a'];
      if(node == null) return; // no such node in trie after this root
      if(node.word != null) {
          res.add(node.word);  // found one
          node.word= null;     // de-duplicate
         // return;  // No return, this is the key
      }
      board[i][j] = '#'; // mark this position is visited and continue searching
      if(i+1 < m && board[i+1][j] != '#') dfs(i+1, j, board, node, m, n);
      if(j+1 < n && board[i][j+1] != '#') dfs(i, j+1, board, node, m, n);
      if(i-1 >=0 && board[i-1][j] != '#') dfs(i-1, j, board, node, m, n);
      if(j-1 >=0 && board[i][j-1] != '#') dfs(i, j-1, board, node, m, n);
      board[i][j] = c; // restore its orginal state 
  }
  
  public Node buildTrie(String[] words) {
      Node root = new Node();
      for(String word : words) {
          Node cur = root;
          for(char c: word.toCharArray()) {
              if(cur.next[c-'a'] == null) {
                  cur.next[c-'a'] = new Node();
              }
              cur = cur.next[c-'a'];
          }
          cur.word = word;
      }
      return root;
  }
  
  class Node {
      String word;
      Node[] next= new Node[26];
  }
}
