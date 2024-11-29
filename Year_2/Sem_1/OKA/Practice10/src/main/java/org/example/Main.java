package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SearchDictionaty dictionary = new SearchDictionaty();
        try (Scanner in = new Scanner(new File("C:\\Users\\stasp\\Documents\\GitHub\\Practice10\\src\\main\\java\\org\\example\\words.txt"))) {
            while (in.hasNext()) {
                dictionary.addWord(in.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found file.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Entry query or 'exit': ");
            String queryToFind = scanner.nextLine();
            if (queryToFind.equals("exit")) break;
                Iterable<String> results = dictionary.query(queryToFind);
                System.out.println("Results: ");
                for (String word : results) {
                    System.out.println(word);
            }
        }
    }
}