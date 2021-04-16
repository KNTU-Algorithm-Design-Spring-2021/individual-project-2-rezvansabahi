
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        str = str.toLowerCase();

        //Reading from file

        BufferedReader br;
        FileReader fr = new FileReader("src/main/word_alpha.txt");
        br = new BufferedReader(fr);

        Set<String> dict = new HashSet<>();

        String line;

        while ((line = br.readLine()) != null) {
            dict.add(line);

        }

        fr.close();

        Map<Integer, List<String>> dp = new HashMap<>();

        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreakDP(str, dict));
        System.out.println(wb.wordBreakTopDown(str, dict, dp, 0));

    }
}
