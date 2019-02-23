/*
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/


/*
    BFS alike
    "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"=[dir, \tsubdir1, \tsubdir2, \t\tfile.txt]
     st=[] level=0
     getLveL(dir)=0 since st.length=0 = level direct push dir, sum=3 max=3
     getLveL(\tsubdir1)=1 since st.length=1=level direct push to st[dir, subdir1], sum=3+8 max=11
     getLveL(\tsubdir2)=1 since st.length=2>level pop, st=[dir] now st.length==1 direct push to st[dir, subdir2], sum=3+8 max=11
     getLveL(\t\tfile.txt)=2 since st.length=2=level direct push to st[dir, subdir2, file.txt], sum=3+8+9 max=20
     answer is 20
     
     note: \t, \n are consider as one char
*/

class Solution {
  public int lengthLongestPath(String input) {
      int sum=0, maxL = 0;
      Stack<Integer> st = new Stack<>();
      for(String s: input.split("\n")) {
          int level = getLevel(s);
          s = format(s);
          while(st.size() > level) {
              sum=sum-st.pop();
          }
          st.push(s.length());
          sum= sum+ s.length();
          if(s.indexOf('.') >=0) maxL = Math.max(maxL, sum);  // we only count the length when we found a file
      }
      
      return maxL;
  }
  
  public String format(String s) {
      String str = s.replaceAll("\t", "");
      return str.length()==s.length() ? s : "\\"+str;
  }
  
  public int getLevel(String s) {
       String cur = s.replaceAll("\t", "");
       return s.length() - cur.length();
  }
}
