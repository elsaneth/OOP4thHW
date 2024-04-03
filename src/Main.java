import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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