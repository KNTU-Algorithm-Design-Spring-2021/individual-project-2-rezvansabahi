import java.util.*;

public class WordBreak {

        public List<String> wordBreakTopDown(String s, Set<String> dict, Map<Integer, List<String>> dp, int start) {
        // max <= longest string is in the dictionary
        int max = 0;
        for (String s1 : dict) {
            max = Math.max(max, s1.length());
        }
        //if input is empty
        if (start == s.length()) {
            return Collections.singletonList("");
        }
        //If the whole word was in the dictionary
        if (dp.containsKey(start)) {
            return dp.get(start);
        }

        List<String> words = new ArrayList<>();
        for (int i = start; i < start + max && i < s.length(); i++) {
            String newWord = s.substring(start, i + 1);
            if (!dict.contains(newWord)) {
                continue;
            }
            List<String> result = wordBreakTopDown(s, dict, dp, i + 1);
            for (String word : result) {
                String extraSpace = word.length() == 0 ? "" : " ";
                words.add(newWord + extraSpace + word);
            }
        }
        dp.put(start, words);
        return words;
    }


//    if( input[i...j] belongs in dictionary) T[i][j] = i
//  else{
//      T[i][j] = k if T[i][k-1] != -1 && T[k][j] != -1
    public String wordBreakDP(String word, Set<String> dict) {
        int[][] T = new int[word.length()][word.length()];

        //-1 indicates string between i to j cannot be split
        for (int[] ints : T) {
            Arrays.fill(ints, -1);
        }

        //fill up the matrix in bottom up manner
        for (int l = 1; l <= word.length(); l++) {
            for (int i = 0; i < word.length() - l + 1; i++) {
                int j = i + l - 1;
                String str = word.substring(i, j + 1);
                //if string between i to j is in dictionary T[i][j] is true
                if (dict.contains(str)) {
                    T[i][j] = i;
                    continue;
                }
                //find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true
                for (int k = i + 1; k <= j; k++) {
                    if (T[i][k - 1] != -1 && T[k][j] != -1) {
                        T[i][j] = k;
                        break;
                    }
                }
            }
        }
        if (T[0][word.length() - 1] == -1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            int k = T[i][j];
            if (i == k) {
                sb.append(word, i, j + 1);
                break;
            }
            sb.append(word, i, k).append(" ");
            i = k;
        }

        return sb.toString();
    }
}