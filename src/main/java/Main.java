import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Set<String> words = loadAllWords();
        HashSet<String> checkedWords = new HashSet<>();

        long start = System.currentTimeMillis();
        for (String word : words) {
            if (word.length() == 9) {
                if (checkWord(word, words, checkedWords)) {
                    System.out.println(word);
                }
            }
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }

    private static boolean checkWord(String word, Set<String> allWords, Set<String> checkedWords) {
        if (word.equals("I") || word.equals("U")) {
            return true;
        }
        if (checkedWords.contains(word)) {
            return true;
        }
        if (allWords.contains(word)) {
            for (int i = 0; i < word.length(); i++) {
                String temp = word.substring(0, i) + word.substring(i + 1);
                if (checkWord(temp, allWords, checkedWords)) {
                    checkedWords.add(word);
                    return true;
                }
            }
        }
        return false;
    }

    private static Set<String> loadAllWords() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        return br.lines().skip(2).collect(Collectors.toSet());
    }
}
