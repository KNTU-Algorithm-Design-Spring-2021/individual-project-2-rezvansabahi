package ir.ac.kntu;

public class WordWrap {

    public String wordWrap(String input, int M) {

        String[] words = input.split("\\s");

        int[][] cost = new int[words.length][words.length];

        //next 2 for loop is used to calculate cost of putting words from i to j in one line.
        for (int i = 0; i < words.length; i++) {
            cost[i][i] = M - words[i].length();
            for (int j = i + 1; j < words.length; j++) {
                cost[i][j] = cost[i][j - 1] - words[j].length() - 1;
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if (cost[i][j] < 0) {
                    cost[i][j] = Integer.MAX_VALUE;
                } else {
                    cost[i][j] = (int) Math.pow(cost[i][j], 3);
                }
            }
        }

        //minCost from i to len is found by trying
        //j between i to len and checking which
        //one has min value
        int[] minCost = new int[words.length];
        int[] result = new int[words.length];
        for (int i = words.length - 1; i >= 0; i--) {
            minCost[i] = cost[i][words.length - 1];
            result[i] = words.length;
            for (int j = words.length - 1; j > i; j--) {
                if (cost[i][j - 1] == Integer.MAX_VALUE) continue;
                if (minCost[i] > minCost[j] + cost[i][j - 1]) {
                    minCost[i] = minCost[j] + cost[i][j - 1];
                    result[i] = j;
                }
            }
        }
        int i = 0;
        int j = result[0];

        System.out.println("Minimum cost is " + minCost[0]);

        //put all words with new line added in string buffer for printing it
        StringBuilder sb = new StringBuilder();
        while (j < words.length) {
            j = result[i];
            for (int k = i; k < j; k++) {
                sb.append(words[k]).append(" ");
            }
            sb.append("\n");
            i = j;
        }
        return sb.toString();
    }
}