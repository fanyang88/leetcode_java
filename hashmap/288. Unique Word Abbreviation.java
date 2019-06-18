/*
An abbreviation of a word follows the form <first letter><number><last letter>. 
Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. 
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

/*
    [ "deer", "door", "cake", "card" ]
    loop all words
    word = deer, map[d2r] = deer
    word = door,  since map[d2r] exist, means d2r is not unique, set to be ''
    word = cake,  map[c2e] = cake
    word = card,  map[c2d] = card
    
    test dear, since d2r == '' not unique, return false
    test cart, since map doesn't have c2t, unqie, return true
    test cane, since c2e exist in map, not unqie, false
    test make, since map desn't have m2e, return true

corner case: dict: [a, a]
since i=0 map[a] = a i=1 map[a]=a do nothing count as same
test 'a' = true
*/

class ValidWordAbbr {
  Map<String, String> map;
  public ValidWordAbbr(String[] dictionary) {
      map = new HashMap<String, String>();
      for(String str: dictionary) {
          String abbr = abbreviation(str);
          if(map.containsKey(abbr)) {
              // at least two words have same abbr, set it to be '', This is the key!!!
              if(!map.get(abbr).equals(str)) map.put(abbr, "");  
          } else {
              map.put(abbr, str);
          }
      }
  }
  
  public boolean isUnique(String word) {
      String abbr = abbreviation(word);
      if(!map.containsKey(abbr) || map.get(abbr).equals(word))  return true;
      return false;
  }
  
  public String abbreviation(String word) {
      return word.length() <=2 ? 
          word : word.substring(0, 1) + (word.length()-2) + word.substring(word.length()-1);
  }
}

/**
* Your ValidWordAbbr object will be instantiated and called as such:
* ValidWordAbbr obj = new ValidWordAbbr(dictionary);
* boolean param_1 = obj.isUnique(word);
*/
