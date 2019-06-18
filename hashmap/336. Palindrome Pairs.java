/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]

*/


/*

 e.g:  abcd   
       we can split it into str1=''   str2 = 'abcd'
       since str1 is a palindrome, we need to find if there is a dcba in map, there is, so we record it to result
       e.g: lls
       we can split it into '' and lls there is no sll skip
       we can split it into l and ls, since l is a palindrome, we need to find sl, not found, skip
       we can split it into ll and s, since ll is a palindrome, we need to find s, there is record,
                                      since s is a palindrome, we need to find ll, not found, skip
            
      1. record each word in map
      2. split each word from 0 to word.length, check str1 and str2
         if(str1 is palindrome)  check if we can find str2 reverse word in map, if there is, record index
         if(str2 is palindrome)  check if we can find str1 reverse word in map, if there is, record index
         
    ["bat","tab","cat"]
    map={bat: 0, tab: 1, cat: 2}
    bat: b & at no ta in map skip
         ba & t, no t in map skip
         bat & "", there is tab in map, found: [0, 1]
    tab: t & ab, no ba in map
         ....

*/

class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
      Map<String, Integer> map = new HashMap<String, Integer>();
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      for(int i=0; i<words.length; i++) map.put(words[i], i);
      
      for(int i=0; i<words.length; i++) {
          for(int j=0; j<=words[i].length(); j++) {   // <= not <, this is KEY1!!!
              String sub1 = words[i].substring(0, j);
              String sub2 = words[i].substring(j);
              if(isPalindrome(sub1)) {
                  String reverse = new StringBuilder(sub2).reverse().toString();
                  if(map.containsKey(reverse) && map.get(reverse) != i) {
                      List<Integer> list = new ArrayList<Integer>();
                      list.add(map.get(reverse));
                      list.add(i);
                      res.add(list);
                  }
              }
              if(isPalindrome(sub2)) {
                  String reverse = new StringBuilder(sub1).reverse().toString();                    
                  if(map.containsKey(reverse) && map.get(reverse) != i && sub2.length() > 0) {  // sub2!='' KEY 2!!!
                      List<Integer> list = new ArrayList<Integer>();
                      list.add(i);
                      list.add(map.get(reverse));
                      res.add(list);
                  }
              }           
          }
      }
      return res;
  }
  
  public boolean isPalindrome(String str) {
      int s=0, e = str.length()-1;
      while(s < e) {
          if(str.charAt(s++) != str.charAt(e--))  return false;
      }
      return true;
  }
}
