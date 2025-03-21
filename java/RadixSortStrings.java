import java.util.*;

public class RadixSortStrings {
    public static void radixSort(String[] arr) {
        if (arr.length == 0) return;

        int maxLen = Arrays.stream(arr).mapToInt(String::length).max().orElse(0);

        for (int pos = maxLen - 1; pos >= 0; pos--) {
            Map<Character, List<String>> buckets = new HashMap<>();

            for (String word : arr) {
                char key = pos < word.length() ? word.charAt(pos) : ' '; // Use space for padding
                buckets.putIfAbsent(key, new ArrayList<>());
                buckets.get(key).add(word);
            }

            List<Character> sortedKeys = new ArrayList<>(buckets.keySet());
            sortedKeys.sort(Comparator.comparingInt(c -> c == ' ' ? -1 : c));

            int index = 0;
            for (char key : sortedKeys) {
                for (String word : buckets.get(key)) {
                    arr[index++] = word;
                }
            }
        }
    }

    public static void radixSortUppercase(String[] arr) {
        if (arr.length == 0) return;

        int maxLen = Arrays.stream(arr).mapToInt(String::length).max().orElse(0);

        for (int pos = maxLen - 1; pos >= 0; pos--) {
            Map<Character, List<String>> buckets = new HashMap<>();

            for (String word : arr) {
                char key = pos < word.length() ? word.charAt(pos) : ' ';
                buckets.putIfAbsent(key, new ArrayList<>());
                buckets.get(key).add(word);
            }

            List<Character> sortedKeys = new ArrayList<>(buckets.keySet());
            sortedKeys.sort((c1, c2) -> {
                if (c1 == ' ') return -1;
                if (c2 == ' ') return 1;
                if (Character.isLowerCase(c1) && Character.isUpperCase(c2)) return 1;
                if (Character.isUpperCase(c1) && Character.isLowerCase(c2)) return -1;
                return Character.compare(c1, c2);
            });

            int index = 0;
            for (char key : sortedKeys) {
                for (String word : buckets.get(key)) {
                    arr[index++] = word;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"google", "gojo", "amazingly", "jogo", "luna", "pup", "solas", "solo", "pupperino", "amaterasu",
                "amazon", "puppy", "hydra", "amazonia", "vueltiao"};

        radixSort(words);
        System.out.println(String.join(", ", words));

        String[] wordsUpper = {"Google", "gojo", "Amazingly", "Jogo", "Luna", "Pup", "Solas", "Solo", "Pupperino", "Amaterasu",
                "Amazon", "Puppy", "Hydra", "Amazonia", "Vueltiao"};

        radixSortUppercase(wordsUpper);
        System.out.println(String.join(", ", wordsUpper));
    }
}