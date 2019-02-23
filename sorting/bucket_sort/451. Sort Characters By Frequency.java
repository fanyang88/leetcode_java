/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

/*
    bucket sort: 
    e,g:  treell
    map: t:1 r:1 e:2 l:2
    bucket[1] ={t, r}
    bucket[2]= {e, l}
    go through the buckets from large to small
*/

class Solution {
  public String frequencySort(String s) {
      StringBuilder sb = new StringBuilder();
      Map<Character, Integer> map = new HashMap<>();
      for(char c: s.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
      
      List<Character>[] buckets = new List[s.length()+1];
      for(Character key: map.keySet()) {
          int val = map.get(key);
          if(buckets[val] == null) buckets[val] = new ArrayList<>();
          buckets[val].add(key);
      }
      for(int i=s.length(); i>=0; i--) {
          if(buckets[i]==null)  continue;
          for(char c: buckets[i]) {
              for(int j=0; j<i; j++) {
                  sb.append(c);
              }
          }
      }
      return sb.toString();
  }
}
