import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Cryptograms class for decoding inputted cryptograms using a dictionary
 */
public class Cryptograms {

    static String input = "";
    static List<String> dictionary = new ArrayList<>();
    static String[] inputWords;

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            scan.close();

            File file = new File("dictionary.txt");
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()) {
                dictionary.add(scanFile.nextLine());
            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("dictionary.txt not found");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Input error");
            System.exit(1);
        }

        Collections.sort(dictionary, Comparator.comparing(String::length)); // sorts the dictionary by word length

        inputWords = input.split(" ");
        List<String> solutions = new ArrayList<>(decode());
        System.out.println(solutions.size());
        for (String word : solutions) {
            System.out.println(word);
        }
    }

    /**
     * Decodes the inputted cryptograms using a given dictionary
     * 
     * @return a list of the decoded strings
     */
    public static List<String> decode() {
        String solution = "";
        List<String> solutions = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (String cryptogram : inputWords) {
            temp.clear();
            int[] pattern = findPattern(cryptogram);
            for (String word : dictionary) {
                if (word.length() == pattern.length) {
                    int[] potentialMatches = findPattern(word);
                    if (Arrays.equals(pattern, potentialMatches)) {
                        temp.add(word);
                    }
                }
            }
            if (solution.equals("")) {
                solution = solution + cryptogram;
                solutions.clear();
                for (String decoding : temp) {
                    solutions.add(decoding);
                }
            } else {
                solution = solution + " " + cryptogram;
                int[] potentialMatches = findPattern(solution);
                List<String> list = new ArrayList<>();
                for (String answer : solutions) {
                    for (String decoding : temp) {
                        list.add(answer + " " + decoding);
                    }
                }
                solutions.clear();
                for (String decoding : list) {
                    solutions.add(decoding);
                }
                List<String> list2 = new ArrayList<>();
                for (String answer : solutions) {
                    int[] solutionMatches = findPattern(answer);
                    if (Arrays.equals(solutionMatches, potentialMatches)) {
                        list2.add(answer);
                    }
                }
                solutions.clear();
                for (String decoding : list2) {
                    solutions.add(decoding);
                }
            }
        }
        return solutions;
    }

    /**
     * Gets the words in the dictionary of the given length
     * NOT CURRENTLY USED IN THIS IMPLEMENTATION
     * 
     * @param length an integer representing the length of the words to be found
     * @return an ArrayList of strings with the same length as the inputted length
     */
    public static ArrayList<String> getWordsOfLength(int length) {
        ArrayList<String> validWords = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == length) {
                validWords.add(word);
            }
        }
        return validWords;
    }

    /**
     * Gets the longest word in an array of words
     * NOT CURRENTLY USED IN THIS IMPLEMENTATION
     * 
     * @param words an array of strings containing words
     * @return a String containing the longest word
     */
    public static String getLongestWord(String[] words) {
        int length = 0;
        String longestWord = "";
        for (String word : words) {
            if (word.length() > length) {
                longestWord = word;
                length = word.length();
            }
        }
        return longestWord;
    }

    /**
     * Finds a pattern in a word String and makes an array containing the pattern
     * 
     * @param word a String containing the word to find the pattern of
     * @return an array of integers containing the pattern of the given word
     */
    public static int[] findPattern(String word) {
        int[] pattern = new int[word.length()];
        Map<Character, Integer> lettersMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (lettersMap.containsKey(letter)) {
                pattern[i] = lettersMap.get(letter);
            } else {
                lettersMap.put(letter, i);
                pattern[i] = i;
            }
        }
        return pattern;
    }

    /**
     * Gets matches for words in the dictionary which fit a pattern defined by an
     * array of integers
     * NOT CURRENTLY USED IN THIS IMPLEMENTATION
     * 
     * @param cryptogram a String containing a cryptogram
     * @param patternedWord an array of integers containing the pattern of a word
     * @return an ArrayList of strings containing the word matches to the pattern
     */
    public static ArrayList<String> getMatches(String cryptogram, int[] patternedWord) {
        ArrayList<String> potentialMatches = new ArrayList<>(getWordsOfLength(cryptogram.length()));
        ArrayList<String> matches = new ArrayList<>();
        for (String word : potentialMatches) {
            int[] pattern = findPattern(word);
            if (Arrays.equals(pattern, patternedWord)) {
                matches.add(word);
            }
        }
        return matches;
    }

    /**
     * Checks if two words are a match based on their patterns
     * NOT CURRENTLY USED IN THIS IMPLEMENTATION
     * 
     * @param word1 a String of the first word
     * @param word2 a String of the second word
     * @return true if the words' patterns match and false if they don't
     */
    public static Boolean findMatches(String word1, String word2) {
        if (Arrays.equals(findPattern(word1), findPattern(word2))) {
            return true;
        }
        return false;
    }
}