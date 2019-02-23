/*
Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/

/*
if last round is false, the next round can be false or true, 
        if false, we add a char, 
        if true, we add a number 
 if last round is true, the next round can only be false
        
                              e.g:  word
                                    ''
                      F  /                      \ T
                        w                  1     2    3     4
                F/         \T              |     |    |      
               wo       w1,  w2,  w3       1o   2r    3d     
         F/      \T     |    |           /   \   ...   ...
        wor   wo1, wo2  w1r  w2d     1or   1o1, 1o2
    F /  |T    |       /  \         /  \    |   
  word  wor1  wo1d  w1r1 w1rd    1ord 1or1  1o1d
     
*/

class Solution {
  List<String> res = new ArrayList<String>();
  public List<String> generateAbbreviations(String word) {
      if(word.length() ==0) {
          res.add(word);
           return res;
      }
      dfs(0, word, "", word.length(), false);
      return res;
  }
  
  public void dfs(int index, String s, String cur, int n, boolean flag) {
      if(index == n)  {
          res.add(cur);
          return;
      }
      if(!flag) {  // this round we are not abbreviate it
          for(int len=1; len+index<=n; len ++) {
              dfs(index+len, s, cur+len, n, true); // next round we can abbreviate it, it set to be true
          }
      }
      dfs(index+1, s, cur+s.charAt(index), n, false); // next round can be not abbreviate it 
  }
}
