import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Cryptograms class for decoding inputted cryptograms using a dictionary
 */
public class Cryptograms {

    static String input = "";
    static ArrayList<String> dictionary = new ArrayList<>();
    static String[] words;

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

        words = input.split(" ");
        decode();
        //int numDecodings = 0;
        words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            
        }
    }

    public static String decode() {
        String solution = "";
        //String[] sortedStrings = sortByLength(words);
        
        /*for (int i = 0; i < words.length; i++) {
            
        }*/
        return solution;
    }

    public static String[] getWordsOfLength(int length) {
        ArrayList<String> validWords = new ArrayList<>();
        for (String word : dictionary) {
            validWords.add(word);
        }
        return validWords.toArray(new String[validWords.size()]);
    }
}