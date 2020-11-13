import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Cryptograms class for decoding inputted cryptograms using a dictionary
 */
public class Cryptograms {

    static String input = "";
    static ArrayList<String> dictionary = new ArrayList<>();
    static String[] inputWords;
    static String singleLetterWord;

    public static void main(String[] args) {

        char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        
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

        inputWords = input.split(" ");
        decode();
        //int numDecodings = 0;
    }

    public static String decode() {
        String solution = "";
        String[] sortedWords = sortWords(inputWords);
        
        for (String cryptogram : sortedWords) {
            int[] pattern = findPattern(cryptogram);
            ArrayList<String> potentialMatches = getMatches(cryptogram, pattern);

            for (String match : potentialMatches) {
                for (int i = 0; i < cryptogram.length(); i++) {

                }
            }
        }
        return solution;
    }

    public static String[] getWordsOfLength(int length) {
        ArrayList<String> validWords = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == length) {
                validWords.add(word);
            }
        }
        return validWords.toArray(new String[validWords.size()]);
    }

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
     * Sorts words by length
     * @param unsortedWords array of Strings to be sorted
     * @return the sorted array of Strings
     */
    public static String[] sortWords(String[] unsortedWords) {
        ArrayList<String> list = new ArrayList<>();
        String[] sortedWords = new String[unsortedWords.length];
        list.addAll(Arrays.asList(unsortedWords));

        for (int i = 0; i < unsortedWords.length; i++) {
            String longestWord = getLongestWord(list.toArray(new String[list.size()]));
            if (longestWord.length() == 1) {
                singleLetterWord = longestWord;
            }
            sortedWords[i] = longestWord;
            list.remove(longestWord);
        }
        return sortedWords;
    }

    public static int[] findPattern(String word) {
        int[] pattern = new int[word.length()];
        HashMap<Character, Integer> lettersMap = new HashMap<>();

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

    public static ArrayList<String> getMatches(String cryptogram, int[] patternedWord) {
        String[] potentialMatches = getWordsOfLength(cryptogram.length());
        ArrayList<String> matches = new ArrayList<>();
        for (String word : potentialMatches) {
            int[] pattern = findPattern(word);
            if (Arrays.equals(pattern, patternedWord)) 
                matches.add(word);
        }
        return matches;
    }
}