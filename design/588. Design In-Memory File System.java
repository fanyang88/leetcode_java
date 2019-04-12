/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

 

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]

Explanation:
filesystem
 

Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
*/

/*
       Ls: 比如a/b/c，那么最后的c有可能是文件夹，也有可能好是文件，
       如果c是文件夹的话，ls命令要输出文件夹c中的所有文件和文件夹，
       而当c是文件的话，只需要输出文件c即可。
       
       另外需要注意的是在创建文件夹的时候，路径上没有的文件夹都要创建出来，
       还有就是在给文件添加内容时，路径中没有的文件夹都要创建出来。
       
      define a class Node {
        string content= ""; // record the content if it is a file
        Map<String, Node> children //reocrd all the sub dirs
        isFile = false;
      }
      
      for mkdir: since the input is a dir
      we set node = root, e.g: //a/b
      since dir= "" continue node = root
      since dir="a" node.children doesn't have "a", node.children.put("a", new Node()), node => a
      since dir="b" node.children doesn't have "b", node.children.put("b", new Node()), node => b
      
      for ls: e.g: /a/b/c.txt
       we set node = root
       since node.children has a, node=node.children.get(a)=>a
       since node.children has b, node=node.children.get(b)=>b
       since node.children has c.txt, node=c.txt name = c.txt
       since it is a file, directly return the file name
       otherwise if it is a directory, we should add all children under this node to be the answer
       

*/

class FileSystem {
  class Node {
      String content = "";
      Map<String, Node> children = new HashMap<>();
      boolean isFile = false;
  }
  
  Node root = null;
  public FileSystem() {
      root = new Node();
  }
  
  public List<String> ls(String path) {
      List<String> res = new ArrayList<>();
      Node cur = root;
      String curFileName = "";
      for(String p: path.split("/")) {
          if(p.length() ==0) continue;
          if(!cur.children.containsKey(p)) {
              return res;
          }
          cur = cur.children.get(p);
          curFileName = p;
      }
      if(cur.isFile) {
          res.add(curFileName);
      } else {
          for(String sub: cur.children.keySet()) {
              res.add(sub);
          }
          Collections.sort(res);
      }
      return res;
  }
  
  public void mkdir(String path) {
      Node cur = root;
      for(String p: path.split("/")) {
          if(p.length() ==0) continue;
          if(!cur.children.containsKey(p)) {
              cur.children.put(p, new Node());
          }
          cur = cur.children.get(p);
      }
      
  }
  
  public void addContentToFile(String filePath, String content) {
      Node cur = root;
      for(String p: filePath.split("/")) {
          if(p.length() ==0) continue;
          if(!cur.children.containsKey(p)) {
              cur.children.put(p, new Node());
          }
          cur = cur.children.get(p);
      }
      cur.isFile = true;
      cur.content += content;
  }
  
  public String readContentFromFile(String filePath) {
      Node cur = root;
      for(String p: filePath.split("/")) {
          if(p.length() ==0) continue;
          if(!cur.children.containsKey(p)) {
              cur.children.put(p, new Node());
          }
          cur = cur.children.get(p);
      }
      return cur.content;
  }
}

/**
* Your FileSystem object will be instantiated and called as such:
* FileSystem obj = new FileSystem();
* List<String> param_1 = obj.ls(path);
* obj.mkdir(path);
* obj.addContentToFile(filePath,content);
* String param_4 = obj.readContentFromFile(filePath);
*/
