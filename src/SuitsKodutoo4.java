import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SuitsKodutoo4 {
    private static final Logger LOGGER = Logger.getLogger(SuitsKodutoo4.class.getName());

    /**
     * Main method to read input file and perform text processing operations.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        String filePath = "/Users/elisabeth/Coding/OOP/OOP4thHW/src/sisend.txt";
        Scanner scanner = findInputFile(filePath);

        // put words in text into a list
        List<String> words = new ArrayList<>();
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (String word : line.split("[\\s,.!?;]+")) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
            System.out.println(Arrays.toString(words.toArray()));

            // count letter occurences in text and sort them by frequency desc
            List<Character> charsInText = Arrays.asList('k', 'p', 't', 'r', 's');
            Map<Character, Integer> letters = countLetters(words, charsInText);
            Map<Character, Integer> sortedLetters = sortLettersByFrequencyDescending(letters);
            System.out.println(sortedLetters);

            // get words starting with r or s without duplicates
            List<Character> firstLetters = Arrays.asList('r', 's');
            List<String> sortedWordsRandS = getWordsStartingWithRorS(words, firstLetters);
            Collections.sort(sortedWordsRandS);
            System.out.println(sortedWordsRandS);

            // count 5 most requently used words in text starting with r or s
            Map<String, Integer> wordsWithR = getWordsByFirstLetter('r', words);
            Map<String, Integer> wordsWithS = getWordsByFirstLetter('s', words);
            Map<String, Integer> sortedEntriesR = getFirst5WordsByValueDesc(wordsWithR);
            Map<String, Integer> sortedEntriesS = getFirst5WordsByValueDesc(wordsWithS);
            System.out.println(sortedEntriesR);
            System.out.println(sortedEntriesS);

            scanner.close();
        }
    }

    /**
     * Finds and returns a Scanner object to read the input file.
     * @param path The path of the input file
     * @return Scanner object to read the input file
     */
    public static Scanner findInputFile(String path) {
        try {
            File input = new File(path);
            return new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            LOGGER.log(Level.SEVERE, "File not found.", e);
        }
        return null;
    }

    /**
     * Counts the occurrences of specific letters in the words.
     * @param words The list of words to count letters from
     * @return Map containing the count of each letter
     */
    public static Map<Character, Integer> countLetters(List<String> words, List<Character> chars) {
        Map<Character, Integer> letters = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = Character.toLowerCase(word.charAt(i));
                if (chars.contains(c)) {
                    letters.put(c, letters.getOrDefault(c, 0) + 1);
                }
            }
        }
        return letters;
    }

    /**
     * Sorts the letters by their frequency in descending order.
     * @param letters The map containing letters and their counts
     * @return Sorted map by letter frequency
     */
    public static Map<Character, Integer> sortLettersByFrequencyDescending(Map<Character, Integer> letters) {
        return letters.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

    /**
     * Gets the words starting with 'r' or 's'.
     * @param words The list of words to filter
     * @return List of words starting with 'r' or 's'
     */
    public static List<String> getWordsStartingWithRorS(List<String> words, List<Character> firstLetters) {
        LinkedHashSet<String> wordsRandS = new LinkedHashSet<>();

        for (String word : words) {
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (firstLetters.contains(firstChar)) {
                wordsRandS.add(word);
            }
        }
        return new ArrayList<>(wordsRandS);
    }

    /**
     * Gets the words starting with a specific letter.
     * @param firstLetter The starting letter
     * @param words The list of words to filter
     * @return Map containing words starting with the specified letter and their counts
     */
    public static Map<String, Integer> getWordsByFirstLetter(char firstLetter, List<String> words) {
        Map<String, Integer> wordsWithFirstLetter = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char firstChar = Character.toLowerCase(word.charAt(0));
                if (firstChar == firstLetter) {
                    wordsWithFirstLetter.put(word.toLowerCase(), wordsWithFirstLetter.getOrDefault(word.toLowerCase(), 0) + 1);
                }
            }
        }
        return wordsWithFirstLetter;
    }

    /**
     * Gets the first 5 words with the highest counts.
     * @param words The map containing words and their counts
     * @return Map containing the first 5 words by count in descending order
     */
    public static Map<String, Integer> getFirst5WordsByValueDesc(Map<String, Integer> words) {
        return words.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }
}
