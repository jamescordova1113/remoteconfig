import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEndOfWord;
    private int count;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
        this.count = 0;
    }

    public boolean hasChild(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }

    public void addChild(char ch, TrieNode node) {
        children.put(ch, node);
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch)) {
                current.addChild(ch, new TrieNode());
            }
            current = current.getChild(ch);
        }
        current.setEndOfWord(true);
        current.incrementCount();
    }

    public int getCount(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch)) {
                return 0;
            }
            current = current.getChild(ch);
        }
        return current.getCount();
    }
}

public class WordCountImproved {
    public static void main(String[] args) {
        String filePath = "sample.txt";
        Trie trie = buildTrie(filePath);
        printWordCount(trie);
    }

    private static Trie buildTrie(String filePath) {
        Trie trie = new Trie();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                for (String word : words) {
                    trie.insert(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trie;
    }

    private static void printWordCount(Trie trie) {
        // Traverse the Trie to print the word count
        traverseTrie(trie.getRoot(), new StringBuilder());
    }

    private static void traverseTrie(TrieNode node, StringBuilder word) {
        if (node.isEndOfWord()) {
            System.out.println(word.toString() + ": " + node.getCount());
        }
        for (Map.Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
            char ch = entry.getKey();
            word.append(ch);
            traverseTrie(entry.getValue(), word);
            word.deleteCharAt(word.length() - 1);
        }
    }
}