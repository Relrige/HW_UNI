package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class SearchDictionaty {
    private class TrieNode {
        Map<Character, TrieNode> allChildren = new HashMap<>();
        boolean isWord = false;
    }

    private TrieNode root;
    private int wordCount;

    public SearchDictionaty() {
        root = new TrieNode();
        wordCount = 0;
    }

    public void addWord(String word) {
        if(word==null || word.trim().isEmpty())
            throw new IllegalArgumentException("Cannot query null or empty query");
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.allChildren.putIfAbsent(ch, new TrieNode());
            node = node.allChildren.get(ch);
        }
        if (!node.isWord) {
            node.isWord = true;
            wordCount++;
        }
    }

    public String delWord(String word) {
        if (!hasWord(word)) {
            throw new NoSuchElementException("The word does not exist in the Trie.");
        }
        boolean deleted = delete(root, word.toLowerCase(), 0);
        if (deleted) {
            return word;
        } else {
            return "Could not be deleted the word" + word;
        }
    }

    private boolean delete(TrieNode current, String s, int index) {
        if (index == s.length()) {
            if (!current.isWord) return false;
            current.isWord = false;
            wordCount--;
            return current.allChildren.isEmpty();
        }
        char ch = s.charAt(index);
        TrieNode nextNode = current.allChildren.get(ch);
        if (nextNode == null) return false;
        boolean shouldCurNodeBeDeleted = delete(nextNode, s, index + 1);
        if (shouldCurNodeBeDeleted) {
            current.allChildren.remove(ch);
            return current.allChildren.isEmpty() && !current.isWord;
        }
        return false;
    }

    public boolean hasWord(String word) {
        TrieNode temp = root;
        for (char ch : word.toCharArray()) {
            temp = temp.allChildren.get(ch);
            if (temp == null) return false;
        }
        return temp.isWord;
    }

    public Iterable<String> query(String query) {
        if (query == null || query.isEmpty())
            throw new IllegalArgumentException("Query must not be null, empty");

        List<String> resList = new ArrayList<>();
        //remove *
        if(query.contains("*")) {
            String prefix = query.substring(0, query.length() - 1);
            TrieNode rigthNode = findNodeForPrefix(root, prefix);
            if (rigthNode != null) {
                appendWords(rigthNode, new StringBuilder(prefix), resList);
            }
            return resList;
        }
        else {
            if(hasWord(query)) {
                resList.add(query);
            }
            return resList;
        }
    }

    private TrieNode findNodeForPrefix(TrieNode node, String prefix) {
        for (char ch : prefix.toCharArray()) {
            node = node.allChildren.get(ch);
            if (node == null) return null;
        }
        return node;
    }

    private void appendWords(TrieNode node, StringBuilder currentPrefix, List<String> results) {
        if (node.isWord) {
            results.add(currentPrefix.toString());
        }
        for (Map.Entry<Character, TrieNode> nodeForWords : node.allChildren.entrySet()) {
            currentPrefix.append(nodeForWords.getKey());
            appendWords(nodeForWords.getValue(), currentPrefix, results);
            currentPrefix.deleteCharAt(currentPrefix.length() - 1);
        }
    }

    public int countWords() {
        return wordCount;
    }
}
