import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = findInputFile("/Users/elisabeth/Coding/OOP/OOP4thHW/src/sisend.txt");
        List<String> words = new ArrayList<>();
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (String word : line.split("[\\s,.!?]+")) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(words.toArray()));

        Map<Character, Integer> letters = new HashMap<>();
        List<Character> chars = new ArrayList<>(Arrays.asList('k', 'p', 't', 'r', 's'));
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                for (char c : chars) {
                    if (Character.toLowerCase(word.charAt(i)) == c) {
                        letters.put(c, letters.getOrDefault(c, 0) + 1);
                    }
                }
            }
        }

        Map<Character, Integer> sortedLetters = letters
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(sortedLetters);
    }

    public static Scanner findInputFile(String path) {
        try {
            File input = new File(path);
            return new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return null;
    }
}