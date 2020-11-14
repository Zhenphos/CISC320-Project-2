import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Cryptograms class for decoding inputted cryptograms using a dictionary
 */
public class Cryptograms {

    static String input = "";
    static List<String> dictionary = new ArrayList<>();
    static Map<Character, Character> alphabet = new HashMap<>(26); // test
    static String[] inputWords;
    static String singleLetterWord = null;
    //static int numDecodings = 0;
    static char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

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

        Collections.sort(dictionary, Comparator.comparing(String::length));

        inputWords = input.split(" ");
        List<String> solutions = new ArrayList<>(decode());
        System.out.println(solutions.size());
        for (String word : solutions) {
            System.out.println(word);
        }
    }

    public static List<String> decode() {
        String solution = "";
        List<String> solutions = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        //String[] sortedWords = sortWords(inputWords);
        
        /*for (String cryptogram : sortedWords) {
            int[] pattern = findPattern(cryptogram);
            ArrayList<String> potentialMatches = getMatches(cryptogram, pattern);
        }*/

        /*for (String cryptogram : inputWords) {
            int index = 0;
            for (char letter : cryptogram.toCharArray()) {
                if (!alphabet.containsKey(letter)) {
                    alphabet.put(letter, letters[index]);
                    index++;
                } else if (alphabet.get(letter) == )
            }
        }*/
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
                for (String answer: solutions) {
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