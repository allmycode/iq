public class Trie {
    
    static class TrieNode {
        char c;
        TrieNode[] subs = new TrieNode[26];
        boolean hasKey;

        public TrieNode(char c) { this.c = c;}        
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode(' ');
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.put("hello");
        System.out.println("Has hello = " + t.hasKey("hello"));
        System.out.println("Has hell = " + t.whasKey("hell"));
        System.out.println("Has hellos = " + t.whasKey("hellos"));
        System.out.println("Has hel = " + t.whasKey("hel"));
        System.out.println("Has helloss = " + t.whasKey("helloss"));
        System.out.println("Has jum = " + t.whasKey("jump"));
        System.out.println("Has hfllo = " + t.whasKey("hfllo"));
        System.out.println("Has hfll = " + t.whas2Key("hfll"));
    } 

    private int idx(char c) {
        return c - 'a';
    }

    public void put(String str) {
        put(root, str, 0);
    }

    private void put(TrieNode n, String str, int index) {
        if (index == str.length()) {
            n.hasKey = true;
            return;
        }
        char c = str.charAt(index);
        int i = idx(c);
        TrieNode s = n.subs[i];
        if (s == null) { 
            s = new TrieNode(c);
            n.subs[i] = s;
        }
        put(s, str, index+1);
    }

    public boolean hasKey(String str) {
        return hasKey(root, str, 0);
    }

    public boolean hasKey(TrieNode n, String str, int index) {
        if (index == str.length()) {
            return n.hasKey;
        }
        char c = str.charAt(index);
        int i = idx(c);
        TrieNode s = n.subs[i];
        return s == null ? false : hasKey(s, str, index+1);
    }

    public boolean whasKey(String str) {
        return whasKey(root, str, 0, 1);
    }

    public boolean whas2Key(String str) {
        return whasKey(root, str, 0, 2);
    }

    public boolean whasKey(TrieNode n, String str, int index, int errors) {
        if (index >= str.length()) {
            if (n.hasKey) {
                //System.out.println("HK");
                return true;
            } else {
              if (errors > 0) {
                  for (int j = 0; j < 26; j++) {
                      TrieNode s = n.subs[j]; 
                      if (s != null && whasKey(s, str, index+1, errors-1)) {
                          //System.out.println("HKE");
                          return true;
                      }
                  }
                  return false;
              } else {
                  return false;
              }
            }
        }
        char c = str.charAt(index);
        int i = idx(c);
        if (errors > 0) {
            for (int j = 0; j < 26; j++) {
                TrieNode s = n.subs[j]; 
                if (s != null && whasKey(s, str, index+1, errors-1)) {
                    //System.out.println("T");
                    return true;
                }
            }
        }
        TrieNode s = n.subs[i];
        //System.out.println("N=" + s + " e = " + errors);
        return s == null ? str.length() - index <= errors : whasKey(s, str, index+1, errors);
    }
}
