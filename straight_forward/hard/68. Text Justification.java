/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */


 /*
    There are 3 cases:
       "acknowledgment  ",
        "shall be        "
    1. when there is only one word can fit in one line, e.g line 2
        just add blank space after this word
    2. When we reach to the end of the words, add words from cur to last-1 to this line and follow spaces.
    3. general case, we calculate average spaces for between each word and the remain need to add up during loop

*/

class Solution {
  public List<String> fullJustify(String[] words, int maxWidth) {
      List<String> res = new ArrayList<String>();
      int cur= 0, n= words.length;
      while(cur < n) {
          int curLen = words[cur].length();
          int next = cur+1;
          StringBuilder line = new StringBuilder();
          line.append(words[cur]);
          while(next < n && curLen+1+words[next].length() <= maxWidth) {
              curLen += 1+words[next].length();
              next++;
          }
          if(next == n || next-cur==1) {
              for(int i=cur+1; i<next; i++) 
                  line.append(" "+words[i]);
                  line.append(getSpace(maxWidth - curLen));       
          } else {
              // e.g cur=0 next=3  w0 w1 w2 = 2 spaces
              int avg = (maxWidth - curLen) / (next- cur-1);
              int remain = (maxWidth - curLen) % (next- cur-1);
              for(int i=cur+1; i<next; i++) {
                  line.append(getSpace(avg+1)); // avg spaces
                  if(remain-- >0) 
                      line.append(" "); 
                  
                  line.append(words[i]);
              }     
          }
          res.add(line.toString());
          cur = next;
      }
      return res;
  }
  
  public String getSpace(int n) {
      String res= "";
      for(int i=0; i<n; i++) {
          res += " ";
      }
      return res;
  }
}
