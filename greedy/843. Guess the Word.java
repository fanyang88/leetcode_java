/*
This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
*/

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 
 we can use greedy:
 给出的wordlist词表长度，一定会大于10。不然遍历枚举查询就可以了(┑(￣Д ￣)┍)
基于贪心策略，我们应该要利用好系统的反馈机制，避免我们浪费查询机会。我们的目标应该是提高每次的 matches 值，即每次都要淘汰词表里不等于matches的单词，缩小我们枚举范围。
每次查询之后，如果matches不等于6，我们虽然还不知道"secret"。但我们知道哪些一定不是"secret"，进而缩小范围，逼近答案。

Intuition:
Take a word from wordlist and guess it.
Get the matches of this word
Update our wordlist and keep only the same matches to our guess.

For example we guess "aaaaaa" and get matches x = 3, we keep the words with exactly 3 a.
 */
class Solution {
    
  public void findSecretWord(String[] wordlist, Master master) {
      for(int i=0, x=0; i<10 & x <6; i++) {
          String target = wordlist[new Random().nextInt(wordlist.length)];
          x = master.guess(target);
          List<String> temp = new ArrayList<>();
          for(String w: wordlist) {
              if(match(target, w) == x) {
                  temp.add(w);
              }
          }
          wordlist = temp.toArray(new String[temp.size()]);
      }
  }
  
  public int match(String a, String b) {
      int cnt= 0;
      for(int i=0; i<a.length(); i++) {
          if(a.charAt(i) == b.charAt(i)) cnt++;
      }
      return cnt;
  }
 
}
