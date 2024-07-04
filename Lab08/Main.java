import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insertar palabras en el Trie
        trie.insert("hello");
        trie.insert("world");
        trie.insert("hello");
        trie.insert("java");
        trie.insert("java");
        trie.insert("java");

        // Buscar palabras en el Trie
        System.out.println(trie.search("hello")); // true
        System.out.println(trie.search("world")); // true
        System.out.println(trie.search("java"));  // true
        System.out.println(trie.search("python"));// false

        // Reemplazar palabras en un texto
        Map<String, String> replacements = new HashMap<>();
        replacements.put("hello", "hi");
        replacements.put("world", "earth");
        String text = "hello world welcome to java world";
        String replacedText = trie.replaceWords(text, replacements);
        System.out.println(replacedText); // hi earth welcome to java earth

        // Obtener las 'k' palabras más frecuentes
        List<String> topKWords = trie.getTopKFrequentWords(2);
        System.out.println(topKWords); // [java, hello]
    }
}
