/**
 Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */


/*
    5-(6+(4-7))  res= 0, sign=1
    i=0 st=[] res=5 
    i=1 sign=-1
    i=2 st=[5, -1] res=0 sign=1
    i=3 st=[5, -1] res=6
    i=4 st=[5, -1] res=6 sign=1
    i=5 st=[5,-1,6,1] res=0, sign=1
    i=6 st=[5,-1,6,1] res=4, sign=1
    i=7 st=[5,-1,6,1] res=4, sign=-1
    i=8 st=[5,-1,6,1] res=4+(-7)=-3, sign=-1
    i=9 st=[5,-1]  pop 6, 1, res=6+1*(res)=3
    i=10 st=[]  pop 5, -1 5+(-1)*(res)=2
    
*/

class Solution {
    public int calculate(String s) {
        int sum=0, sign=1;
        Stack<Integer> st= new Stack<Integer>();
        for(int i=0; i<s.length(); i++) {
            char chr = s.charAt(i);
            if(chr == ' ') continue;
            if(Character.isDigit(chr)) {
                int num = chr - '0';
                while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    num = num*10 + s.charAt(i+1) - '0';
                    i++;
                }
                sum = sum + sign*num;
            } else if(chr=='+') {
                sign=1;
            } else if(chr=='-') {
                sign=-1;
            } else if(chr=='(') {
                st.push(sum);
                st.push(sign);
                sum=0;
                sign=1;
            } else if(chr == ')') {
                sum = st.pop()*sum + st.pop();// first pop is the pre sign, sec is the preSum
            }
        }
        return sum;
    }
}
