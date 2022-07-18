/*
Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]
 

Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]
 

Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".

*/

/*
     "IDID" number needs [0,1,2,3,4]
  s=0, d=4
  it is I, push 0, s++=1
  it is D, push 4 d--=3
  it is I, push 1, s++=2
  it is D, push 3, d--=2
  stop 
  push 2

*/

class Solution {
  public int[] diStringMatch(String S) {
      int s=0, d = S.length(), i=0;
      int[] res = new int[S.length()+1];
      while(s < d) {
          if(S.charAt(i)=='I') {
              res[i++] = s++;
          } else {
              res[i++] = d--;
          }
      }
      res[i] = s;
      return res;
  }
}


PYTHON ****************************************************
class Solution:
    def diStringMatch(self, s: str) -> List[int]:
        right = len(s) 
        left = 0
        res = []
        
        for i in range(0, len(s)):
            if s[i] == 'I':
                res.append(left)
                left +=1
            else:
                res.append(right)
                right -=1
        
        if left == right:
            res.append(left)
        elif left ==0:
            res.append(left)
        else:
            res.append(right)
        return res
    
            

# if first one is I, use the small one, if first one is D, use large one
# e.g: IDID  - 0 1 2 3 4 
# I -> 0
# D -> 04
# I -> 041
# D -> 0413
# res is 0 4 1  3 2

# III - 0 1 2 3
# 0 1 2

