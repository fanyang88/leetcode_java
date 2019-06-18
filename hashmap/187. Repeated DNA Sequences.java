/**
 * 
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 *  When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) 
that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */

 /*
    The whole idea is to transform the DNA string to numbers (so it'll be quicker for comparison), and this is actually easy since we know for a fact that the sequence only consists of 4 letters (all upper case) 'A', 'C', 'G' and 'T'.

If we want to map this to binary, we'd need (log 4 = 2)
00 -> 'A'
01 -> 'C'
10 -> 'G'
11 -> 'T'

so we create an array to save these value
char[] map = new char[26];
map['A' - 'A'] = 0;
map['C' - 'A'] = 1;
map['G' - 'A'] = 2;
map['T' - 'A'] = 3;

So, let's transform the sequence:
First of all, we need 20 bits (10 letters * 2 bits for representing each letter)
So we start with a variable V = 0.
Then for each letter we shift V to the left by 2 bits and OR it with the letter representation
so for sequence "CG" for example:
v = 0
v <<= 2
v = 00
v= v | map[s.charAt(j) - 'A'] = 00 | 01  = 01

Nex Character "G":
v = 01
v <<= 2
v = 0100
v = v | map[s.charAt(j) - 'A'] = 0100 | 10 = 0110
go on for the 10 chars.

Added to set1 (Then don't do anything, and notice here that we're depending on the condition short circuiting, so it won't continue to execute the addition to the second set since we're using && and the first condition was true and we're using not, so it will be false) - then - we do nothing.
Not added to set1 and added to set 2 - then - that means that we saw that exact number before, so we add it to the output list.
Not added to set and Not added to set 2 - then - that means that we saw that number before Twice, so do nothing.
*/

class Solution {
  public List<String> findRepeatedDnaSequences(String s) {
      Map<Character, Integer> map = new HashMap<Character, Integer>();
      HashSet<Integer> set = new HashSet<Integer>();
      HashSet<Integer> secSet = new HashSet<Integer>();
     
      
      List<String> res = new ArrayList<String>();
      map.put('A', 0);
      map.put('C', 1);
      map.put('G', 2);
      map.put('T', 3);
      for(int i=0; i<s.length()-9;i++) {
          int v=0;
          // make every 10 ACGT represent by a value v
          for(int j=i; j<i+10; j++) {
              v= v<<2;
              v = v| map.get(s.charAt(j));
          }
          if(!set.add(v) && secSet.add(v)) {// set has, secSet doesn't has
              res.add(s.substring(i, i+10));
          }
      }
      return res;
  }
}
