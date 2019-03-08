/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
*/

class Solution {
  class Element {
      String word;
      int freq;
      Element (String word, int freq) {
          this.freq= freq;
          this.word = word;
      }
  }
  
  public List<String> topKFrequent(String[] words, int k) {
      List<String> res = new ArrayList<String>();
      // use a map to get the freq
      // use a priority queue to store the k size strings
      Map<String, Integer> map = new HashMap<>();
      for(String word: words) {
          map.put(word, map.getOrDefault(word, 0)+1);
      }
      PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> {
          if(a.freq == b.freq) return b.word.compareTo(a.word);
          return a.freq - b.freq;
      });
      
      for(String key: map.keySet()) {
          pq.offer(new Element(key, map.get(key)));
          if(pq.size() > k) pq.poll();
      }
      
      while(!pq.isEmpty()) {
          res.add(0, pq.poll().word); // This is the key, the word are sort from small to large lexically
      }
      return res;
  }
}
