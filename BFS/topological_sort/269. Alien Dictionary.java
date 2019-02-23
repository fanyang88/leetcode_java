/**
 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */

 /*
[ "wrt",
   "wrf",
   "er",
   "ett",
   "rftt"]
 use topological sorting, 
 step1: first we init degree for each node, degree: [w: 1, e: 1, r: 1, t : 1, f: 1] count = 5;
 step2: we construct the edges and update map by comparing each two strings.
    for example: wrt - wrf since w===w r===r skip t!==f, since !map[t], map[t] = [f], degree[f]++
    wrf -- er: w!==e, since !map[w], map[w] = [e], degree[e]++
    er -- ett: r!==t, since !map[r], map[r] = [t], degree[t]++
    ett -- rftt: e!==r, since !map[e], map[e] = [r], degree[r]++, map[t] already have f, skip, t====t skip
    degree= [w: 1, e: 2, r: 2, t : 2, f: 2]
    map = {t: [f], w : [e], r: [t], e: [r]}
step3: we can now do BFS based on the map and degree. graph is w - e - r - t - f
    find the degree =1 in degree array, this would be the entry of the graph, put it in the queue, and pop it out, for all the nodes linked to this node, we reduce their degree by 1, if there is a node among them ===1, push into q
 
*/

class Solution {
  public String alienOrder(String[] words) {
      String res = "";
      if(words.length==0)  return res;
      
      int[] degree = new int[26];
      Queue<Character> q = new LinkedList<>();
      Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
      for(String word: words) {
          for(char c : word.toCharArray()) {
              degree[c- 'a'] =1;
              if(map.get(c)==null) map.put(c, new ArrayList<Character>());
          }
      }
      // add next node for each node
      for(int i=0, j=1; j<words.length; i++, j++) {
          compare(words[i], words[j], map, degree);
      }
      
      // get all degree=1 node and push onto Q
      for(int i=0; i<26; i++) {
          if(degree[i] ==1) q.offer((char)(i+'a'));
      }
      
      // topological sorting
      while(!q.isEmpty()) {
          char cur = q.poll();
          res += cur;
          for(char next : map.get(cur)) {
              degree[next - 'a'] --;
              if(degree[next - 'a'] ==1) q.offer(next);
          }
      }
      return res.length() == map.size() ? res : "";
  }
  
  public void compare(String a, String b, Map<Character, List<Character>> map, int[] degree) {
      for(int i=0; i<Math.min(a.length(), b.length()); i++) {
          char ca = a.charAt(i);
          char cb = b.charAt(i);
          if(ca == cb)  continue;
          if(map.get(ca).contains(cb))  break;  // This is the key!!!
          map.get(ca).add(cb);
          degree[cb - 'a'] ++;
          break; // This is the key!!!
      }
  }
}
