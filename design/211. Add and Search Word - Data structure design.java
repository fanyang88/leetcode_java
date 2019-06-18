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

class WordDictionary {
    class Node {
        Node[] children;
        boolean isWord;
        Node() {
            children = new Node[26];
            isWord = false;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for(char c: word.toCharArray()) {
            if(cur.children[c-'a'] == null) cur.children[c-'a'] = new Node();
            cur =cur.children[c-'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
        
    }
    
    boolean search(String word, int index, Node cur) {
        if(index == word.length()) return cur.isWord;
        char c= word.charAt(index);
        if(c != '.') {
            if(cur.children[c-'a'] == null) return false;
            return search(word, index+1, cur.children[c-'a']);   
        } else {
            for(int i=0; i<26; i++) {
                if(cur.children[i] == null) continue; 
                if(search(word, index+1, cur.children[i])) return true;
            }
            return false;
        }
        
    }
}

/**
* Your WordDictionary object will be instantiated and called as such:
* WordDictionary obj = new WordDictionary();
* obj.addWord(word);
* boolean param_2 = obj.search(word);
*/
