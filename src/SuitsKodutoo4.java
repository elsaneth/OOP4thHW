import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SuitsKodutoo4 {
    public static void main(String[] args) {
        String filePath = "/Users/elisabeth/Coding/OOP/OOP4thHW/src/sisend.txt";
        Scanner scanner = findInputFile(filePath);

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

            Map<Character, Integer> letters = countLetters(words);
            Map<Character, Integer> sortedLetters = sortLettersByFrequencyDescending(letters);
            System.out.println(sortedLetters);

            List<String> sortedWordsRandS = getWordsStartingWithRorS(words);
            Collections.sort(sortedWordsRandS);
            System.out.println(sortedWordsRandS);

            Map<String, Integer> wordsWithR = new HashMap<>();
            Map<String, Integer> wordsWithS = new HashMap<>();
            List<Character> firstLetters = Arrays.asList('r', 's');

            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    char firstChar = Character.toLowerCase(word.charAt(0));
                    if (firstLetters.contains(firstChar)) {
                        if (firstChar == 's') {
                            wordsWithS.put(word.toLowerCase(), wordsWithS.getOrDefault(word.toLowerCase(), 0) + 1);
                        } else {
                            wordsWithR.put(word.toLowerCase(), wordsWithR.getOrDefault(word.toLowerCase(), 0) + 1);
                        }
                    }
                }
            }

            List<Map.Entry<String, Integer>> sortedEntriesR = wordsWithR.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(5)
                    .toList();

            List<Map.Entry<String, Integer>> sortedEntriesS = wordsWithS.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(5)
                    .toList();

            System.out.println(sortedEntriesR);
            System.out.println(sortedEntriesS);

            scanner.close();
        }
    }
    private static final Logger LOGGER = Logger.getLogger(SuitsKodutoo4.class.getName());
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

    public static Map<Character, Integer> countLetters(List<String> words) {
        Map<Character, Integer> letters = new HashMap<>();
        List<Character> chars = Arrays.asList('k', 'p', 't', 'r', 's');

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

    public static Map<Character, Integer> sortLettersByFrequencyDescending(Map<Character, Integer> letters) {
        return letters.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

    public static List<String> getWordsStartingWithRorS(List<String> words) {
        LinkedHashSet<String> wordsRandS = new LinkedHashSet<>();
        List<Character> firstLetters = Arrays.asList('r', 's');

        for (String word : words) {
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (firstLetters.contains(firstChar)) {
                wordsRandS.add(word);
            }
        }
        return new ArrayList<>(wordsRandS);
    }
}
