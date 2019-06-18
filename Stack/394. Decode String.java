/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/


/*
    3[a]2[bc]
    st=[]  cSt= []  res=''
    i=0 it is 3, push to cSt=[3]
    i=1 it is [, push res to st=['']  res=''
    i=2 it is char, res='a'
    i=3 it is ] pop st->''+'a' pop cSt->3 => aaa: res='aaa'
    i=4 it it 2, cSt=[2]
    i=5 it is [, push 'aaa' to st, st=[aaa] Cst=[2]  res=''
    i=6 it is char, res+b, 
    i=7 it is char, res+c, 
    i=8 it is ], pop st=>'aaa', pop Cst=>2 => temp ='aaa' append(bc) 2 times res=aaabcbc
    return res;
    
    only time we push res is when encounter [

*/
class Solution {
  public String decodeString(String s) {
      Stack<String> st = new Stack<>();
      Stack<Integer> counter = new Stack<>();
      String res= "";
      
      for(int i=0; i<s.length(); i++) {
          char chr = s.charAt(i);
          if(Character.isDigit(chr)) {
              int j=i;
              while(i<s.length() && Character.isDigit(s.charAt(i))) i++;
              int c = Integer.parseInt(s.substring(j, i));
              counter.push(c);
              i--;
          } else if(chr=='[') {
              st.push(res);  // This is the place we push 
              res = "";
          } else if(chr==']') {
              String temp = st.pop();
              int repeat = counter.pop();
              for(int k=0; k<repeat; k++) {
                  temp += res;
              }
              res = temp;
          } else {
              res += chr;
          }
      }
      return res;
  }
}


// recursive
class Solution {
    int i=0;
    public String decodeString(String s) {
        return dfs(s);
    }
    
    String dfs(String s) {
        StringBuilder sb = new StringBuilder();
        int k=0;
        for(; i<s.length(); i++) {
            char chr = s.charAt(i);
            if(chr == '[') {
                i++;
                String str = dfs(s);
                while(k>0) {
                    sb.append(str);
                    k--;
                }
            } else if(chr == ']') {
                break;
            } else if (Character.isDigit(chr)) {
                 k = k*10 + chr - '0';
            } else {
                sb.append(chr);
            }
        }
        return sb.toString();
    }
}

