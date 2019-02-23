/*
Given a string which contains only lowercase letters, 
remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"
*/

/*
cbacdcbc
 hashmap = {c: 4, b: 2, a: 1, d: 1}
 st push c visited[c] =1 since st is empty st = [c] map[c]=3
 since b < st.peek() and map[c] > 0, means c would occur in later again, 
        so we kick out c now, and set visited[c] = 0. st = [b], map[b]=1 map[c]=3;
 since a < st.peek() and map[b] > 0, means b would occur in later again, 
        so we kick out b now, and set visited[b] = 0 st = [a], map[a]=0 map[b]=1  map[c]=3;
 since c > st.peek() and visited[c] == 0, push c, visited[c] = 1, map[c]=2, st= [a, c]
 since d > st.peek() and visited[d] == 0, push d, visited[d] = 1, map[d]=0, st= [a, c, d]
 though c < st.peek() and but map[d]==0, and since visited[c] ==1 do nothing map[c]--=1
 since  count[d] ==0 do not pop, directly push b map[b]--=0 visited[b]=1 
 
 so 1: map[c] -- 
    when !visited[c]
        while c < st.peek() && map[st.peek()] > 0  val = st.pop(), visited[val] = 0
        st.push(c)  visited[c] = 1
*/
class Solution {
  public String removeDuplicateLetters(String s) {
      boolean[] visited = new boolean[26];
      int[] map = new int[26];
      Stack<Character> st = new Stack<>();
  
      for(char c : s.toCharArray()) map[c-'a']++;
      
      for(char c : s.toCharArray()) {
          map[c-'a'] --;
          if(!visited[c-'a']) { // c is not visited
              while(!st.isEmpty() && c < st.peek() && map[st.peek() -'a'] >0) {
                  char val = st.pop();
                  visited[val -'a'] =false;
              }
              visited[c-'a'] = true;
              st.push(c);
          }
      }
      
      StringBuilder sb = new StringBuilder();
      while(!st.isEmpty())  sb.insert(0,st.pop());
      return sb.toString();
  }
}