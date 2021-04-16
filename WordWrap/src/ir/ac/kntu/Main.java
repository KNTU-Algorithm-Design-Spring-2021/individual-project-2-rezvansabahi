package ir.ac.kntu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter text: ");

        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();

        System.out.println("Enter a limit on the number of characters in one line: ");

        int M = scanner.nextInt();

        WordWrap wp = new WordWrap();

        System.out.println(wp.wordWrap(string, M));

    }
}