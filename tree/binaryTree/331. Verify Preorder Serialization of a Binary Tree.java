/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true
Example 2:

Input: "1,#"
Output: false
Example 3:

Input: "9,#,#,1"
Output: false
*/

/*
     In a binary tree, if we consider null as leaves, then
all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.
e.g: "9,3,4,#,#,1,#,#,2,#,6,#,#"
9: 1-1+2=2     3: 2-1+2=3    4:3-1+2=4   #:4-1   #:3-1=2      1:2-1+2=3     #: 3-1=2      #:2-1=1
2: 1-1+2=2     #: 2-1=1      6:1-1+2=2   #:2-1   #:1-1 

    for non-null node, current degree = current degree-1+2
    for null node, current degree = current degree -1
    
*/

class Solution {
  public boolean isValidSerialization(String preorder) {
      int degree = 1;
      for(String str: preorder.split(",")) {
           degree --;
          if(degree <0)  return false; 
          if(!str.equals("#")) {
              degree += 2;
          } 
      }
      return degree == 0;
  }
}


