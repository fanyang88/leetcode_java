/*
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
 

Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
 

Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
 */

 /*
        start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
AACCGGTT: distance 1: AACCGGTA
AACCGGTA: distance 1: AACCGCTA, AAACGGTA

use BFS, similar to word ladder

*/
/*
        start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
AACCGGTT: distance 1: AACCGGTA
AACCGGTA: distance 1: AACCGCTA, AAACGGTA

use BFS, similar to word ladder

*/

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        int level=0;
        Set<String> unvisit  = new HashSet<>();
        for(String s: bank) unvisit.add(s);
        unvisit.remove(start);
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                String cur = q.poll();
                if(cur.equals(end)) return level;
                for(String next: getNext(cur, unvisit)) {
                    q.offer(next);
                    unvisit.remove(next);
                }
            }
            level++;
        }
        return -1;
    }
    
    public List<String> getNext(String cur, Set<String> unvisit) {
        List<String> res = new ArrayList<String>();
        char[] chars= {'A', 'C', 'G', 'T'};
        for(int i=0; i<cur.length(); i++) {
            for(char c: chars) {
                if(c == cur.charAt(i))  continue;
                String str = cur.substring(0, i) + c + cur.substring(i+1);
                if(unvisit.contains(str)) res.add(str);
            }
        }
        return res;
    }
}
