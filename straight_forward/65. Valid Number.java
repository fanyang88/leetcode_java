/**
 Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one. 
However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.
 */




/*
number is represented as:
   (+/-)(float)(e)(+/-)(Int), check each part rigoriously
   
*/

class Solution {
    public boolean isNumber(String s) {
        s= s.trim();
        if(s.length()==0)  return false;
        int i=0, n = s.length(), dots=0, digits=0;
        if(s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        while(i<n && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            char chr = s.charAt(i);
            if(Character.isDigit(chr)) digits++;
            if(chr == '.') dots++;
            i++;
        }
        if(digits==0 || dots > 1) return false;
        if(i<n && s.charAt(i) == 'e') {
            i++;
            if((i<n) &&(s.charAt(i) == '+' || s.charAt(i) == '-')) {
                i++;
            }
            // if after e there is number follow, return false
            if(i==n)  return false;
            while(i<n && Character.isDigit(s.charAt(i))) i++; 
        }
        return i ==n;
    }
}