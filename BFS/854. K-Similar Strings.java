/*
Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

*/

/*
 case 1:    A = aabc  A[j] === B[j]
            B = acba
                 ij   swap A[i] with A[j] doesn't make difference, we need to skip such case
 case 2:    A = aaac  A[i] != B[j]
            B = aaca  swap A[i] with A[j] doesn't make difference, we need to skip such case
                 ij
*/

class Solution {
  public int kSimilarity(String A, String B) {
      int step=0;
      Set<String> visited = new HashSet<>();
      Queue<String> q = new LinkedList<>();
      q.offer(A);
      visited.add(A);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int k=0; k<size; k++) {
              String cur = q.poll();
              if(cur.equals(B)) return step;
              int i=0;
              while(i < B.length() && cur.charAt(i) == B.charAt(i)) i++;
              // now i point to the first pos cur and B doesn;t match
              for(int j = i+1; j<B.length(); j++) {
                  if(cur.charAt(j) == B.charAt(j) || cur.charAt(i) != B.charAt(j)) continue;
                  String str = swap(i, j, cur);
                  if(visited.contains(str)) continue;
                  visited.add(str);
                  q.offer(str);
              }
          }
          step ++;
      }
      return -1;
  }
  
  public String swap(int i, int j, String cur) {
      char[] arr = cur.toCharArray();
      char t = arr[i];
      arr[i] = arr[j];
      arr[j] = t;
      return new String(arr);
  }
}
