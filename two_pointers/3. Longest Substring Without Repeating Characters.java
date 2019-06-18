/** 
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
/* 
pwwkew
i=0 j=0 map[p]=0, 
i=1 j=0 map[w] =1
i=2 j=0 since map[w] exist, we move j to 2, map[w]=2
i=3 j=2 map[k]=3
i=4 j=2 map[e]=4
i=5 j=2 since w exist in map, move j to map[w]+1=3

abba
when map[a]=0 map[b]=2 i=2, j=2
then i=3 j=2 since map[a] exist, 
but map[a]+1=1, j can't jump backwards, j should stay right where it is.
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen=0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0, j=0; i<s.length(); i++) {
            if(map.containsKey(s.charAt(i))) {
                // The Key, see corner case
                j = Math.max(j, map.get(s.charAt(i)) + 1); 
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i-j+1);
        }
        return maxLen;
    }
}
