import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Cryptograms class for decoding inputted cryptograms using a dictionary
 */
public class Cryptograms {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (scan.hasNext()) {
            input.add(scan.next());
        }
        scan.close();
        File dictionary = new File("dictionary.txt");
        //BufferedReader dictionary = new BufferedReader(new FileReader("dictionary.txt"));

        int numDecodings;
        
    }
}