import org.example.SearchDictionaty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TrieSearchDictionaryTest {
    private SearchDictionaty sd;

    @BeforeEach
    public void beforeEach() {
        sd = new SearchDictionaty();
    }

    @Test
    public void addWord_givenNullOrBlank_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> sd.addWord(null));
        assertThrows(IllegalArgumentException.class, () -> sd.addWord("      "));
    }

    @Test
    public void hasWord_givenWordThatWasAdded_returnsTrue() {
        String[] words = {"orange", "grape", "grapefruit", "pear", "peach"};

        for (var word : words) {
            sd.addWord(word);
        }
        for (var word : words) {
            assertTrue(sd.hasWord(word));
        }
    }


    @Test
    public void hasWord_givenNotAddedWord_returnsFalse() {
        String[] words = {"carrot", "car", "dog", "sky", "cloud"};

        for (var word : words) {
            sd.addWord(word);
        }

        String[] notAddedWords = {"flower", "river", "mountain", "sun", "moon", "tree", "bird", "fish"};

        for (var word : notAddedWords) {
            assertFalse(sd.hasWord(word));
        }
    }


    @Test
    public void hasWord_givenPrefixOfAddedWord_returnsFalse() {
        String[] words = {"computer", "comp", "mountain", "mount"};

        for (var word : words) {
            sd.addWord(word);
        }

        String[] prefixes = {"c", "co", "com", "compu", "m", "mo", "mou"};

        for (var prefix : prefixes) {
            assertFalse(sd.hasWord(prefix));
        }
    }


    @Test
    public void delWord_givenNotAddedWord_throwsException() {
        String[] words = {"apple", "carrot", "dog", "sky"};

        for (var word : words) {
            sd.addWord(word);
        }

        String[] notAddedWords = {"banana", "grape", "pear", "cloud", "mountain", "sun", "tree", "river"};

        for (var word : notAddedWords) {
            assertThrows(NoSuchElementException.class, () -> sd.delWord(word));
        }
    }


    @Test
    public void delWord_givenAddedDistinctWords_doNotAffectOtherWords() {
        String[] distinctWords = {"apple", "orange", "grape", "melon", "banana"};

        for (String word : distinctWords) {
            sd.addWord(word);
        }

        assertEquals(distinctWords.length, sd.countWords());

        for (int i = 0; i < distinctWords.length; i++) {
            sd.delWord(distinctWords[i]);
            assertFalse(sd.hasWord(distinctWords[i]));
            assertEquals(distinctWords.length - (i + 1), sd.countWords());
        }

        assertEquals(0, sd.countWords());
    }
    @Test
    public void delWord_givenAddedPrefixOfAnotherWord_doNotDeleteAnotherWord() {
        String[] words = {"b", "ba", "ban", "bana", "banan", "banana"};

        for (var word : words) {
            sd.addWord(word);
        }

        assertEquals(words.length, sd.countWords());

        for (int i = 0; i < words.length; i++) {
            sd.delWord(words[i]);
            assertFalse(sd.hasWord(words[i]));
            assertEquals(words.length - (i + 1), sd.countWords());

            for (int j = i + 1; j < words.length; j++) {
                assertTrue(sd.hasWord(words[j]));
            }
        }

        assertEquals(0, sd.countWords());
    }

    @Test
    public void delWord_givenCompoundWordOfAnotherWord_doNotDeleteAnotherWord() {
        String[] words = {"banana", "banan", "bana", "ban", "ba", "b"};
        for (var word : words) {
            sd.addWord(word);
        }
        assertEquals(words.length, sd.countWords());
        for (int i = 0; i < words.length; i++) {
            sd.delWord(words[i]);
            assertFalse(sd.hasWord(words[i]));
            assertEquals(words.length - (i + 1), sd.countWords());
            for (int j = i + 1; j < words.length; j++) {
                assertTrue(sd.hasWord(words[j]));
            }
        }
        assertEquals(0, sd.countWords());
    }

    @Test
    public void query_givenPrefix_returnsAllWordsByPrefix() {
        String[] words = {"cartoon", "car", "ape", "apple"};

        for (var word : words) {
            sd.addWord(word);
        }

        Map<String, String[]> expectedWordsByPrefix = new LinkedHashMap<>();

        expectedWordsByPrefix.put("*", new String[]{"cartoon", "car", "ape", "apple"});
        expectedWordsByPrefix.put("none", new String[0]);

        expectedWordsByPrefix.put("c*", new String[]{"cartoon", "car"});
        expectedWordsByPrefix.put("ca*", new String[]{"cartoon", "car"});
        expectedWordsByPrefix.put("car*", new String[]{"cartoon", "car"});
        expectedWordsByPrefix.put("cart*", new String[]{"cartoon"});
        expectedWordsByPrefix.put("cartoon*", new String[]{"cartoon"});

        expectedWordsByPrefix.put("a*", new String[]{"ape", "apple"});
        expectedWordsByPrefix.put("ap*", new String[]{"ape", "apple"});
        expectedWordsByPrefix.put("ape*", new String[]{"ape"});
        expectedWordsByPrefix.put("app*", new String[]{"apple"});
        expectedWordsByPrefix.put("apple*", new String[]{"apple"});

        for (var entry : expectedWordsByPrefix.entrySet()) {
            String prefix = entry.getKey();

            Set<String> actual = new HashSet<>();
            sd.query(prefix);
            for (var word : sd.query(prefix)) {
                actual.add(word);
            }

            String[] expected = entry.getValue();

            assertEquals(expected.length, actual.size());
            for (int i = 0; i < expected.length; i++) {
                assertTrue(actual.contains(expected[i]));
            }
        }
    }
}