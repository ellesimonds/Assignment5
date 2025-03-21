import java.util.HashMap;

public class WordPattern {
    public boolean wordPattern(String pattern, char delimiter, String s) {
        String[] words = s.split(String.valueOf(delimiter));

        if (pattern.length() != words.length) {
            return false;
        }

        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String word = words[i];

            if (charToWord.containsKey(p)) {
                if (!charToWord.get(p).equals(word)) {
                    return false;
                }
            } else {
                charToWord.put(p, word);
            }

            if (wordToChar.containsKey(word)) {
                if (wordToChar.get(word) != p) {
                    return false;
                }
            } else {
                wordToChar.put(word, p);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        WordPattern solution = new WordPattern();

        System.out.println(solution.wordPattern("abba", '?', "dog?cat?cat?dog"));
        System.out.println(solution.wordPattern("abba", '|', "apple|banana|grape|apple"));
        System.out.println(solution.wordPattern("aaaa", ',', "dog,cat,cat,dog"));
        System.out.println(solution.wordPattern("aaaa", ' ', "ice cream taco day"));
        System.out.println(solution.wordPattern("adxp", ' ', "ice cream taco day"));
    }
}
