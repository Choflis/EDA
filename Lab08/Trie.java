import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    int frequency;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
        frequency = 0;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insertar una palabra en el Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.isEndOfWord = true;
        node.frequency++;
    }

    // Buscar una palabra en el Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord;
    }

    // Reemplazar palabras en un texto usando el Trie
    public String replaceWords(String text, Map<String, String> replacements) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (search(word)) {
                result.append(replacements.getOrDefault(word, word)).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }
        return result.toString().trim();
    }

    // Encontrar las 'k' palabras más frecuentes
    public List<String> getTopKFrequentWords(int k) {
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue());
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        buildFrequencyMap(root, "", wordFrequencyMap);
        maxHeap.addAll(wordFrequencyMap.entrySet());

        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topKWords.add(maxHeap.poll().getKey());
        }
        return topKWords;
    }

    private void buildFrequencyMap(TrieNode node, String prefix, Map<String, Integer> map) {
        if (node.isEndOfWord) {
            map.put(prefix, node.frequency);
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            buildFrequencyMap(entry.getValue(), prefix + entry.getKey(), map);
        }
    }
}
