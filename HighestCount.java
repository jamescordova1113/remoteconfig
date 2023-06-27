import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class WordCountTest {
    @Test
    public void testCountWords_NullInput() {
        Map<String, Integer> wordCount = WordCount.countWords(null);
        Assertions.assertNotNull(wordCount);
        Assertions.assertTrue(wordCount.isEmpty());
    }

    @Test
    public void testCountWords_EmptyInput() {
        Map<String, Integer> wordCount = WordCount.countWords("");
        Assertions.assertNotNull(wordCount);
        Assertions.assertTrue(wordCount.isEmpty());
    }

    @Test
    public void testCountWords_InvalidInput() {
        Map<String, Integer> wordCount = WordCount.countWords("invalid_file.txt");
        Assertions.assertNotNull(wordCount);
        Assertions.assertTrue(wordCount.isEmpty());
    }

    @Test
    public void testCountWords_ValidInput() {
        Map<String, Integer> wordCount = WordCount.countWords("sample.txt");
        Assertions.assertNotNull(wordCount);
        Assertions.assertFalse(wordCount.isEmpty());
        Assertions.assertEquals(3, wordCount.get("word1"));
        Assertions.assertEquals(2, wordCount.get("word2"));
        Assertions.assertEquals(1, wordCount.get("word3"));
    }
}