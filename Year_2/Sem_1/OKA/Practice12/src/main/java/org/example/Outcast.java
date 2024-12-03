package org.example;

import java.io.IOException;

public class Outcast {
    WordNet wordNet;
    public Outcast(WordNet word){
        wordNet = word;
    }
    public String outcast(String[] nouns){
        int[] arr = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (int j = 0; j < nouns.length; j++) {
                arr[i] += wordNet.distance(nouns[i],nouns[j]);
            }
        }
        int maxDist = arr[0];
        String maxNoun = nouns[0];
        for (int i = 1; i < arr.length; i++) {
            if(maxDist<arr[i]){
                maxDist = arr[i];
                maxNoun = nouns[i];
            }
        }
        return maxNoun;
    }
    public static void main(String[] args) throws IOException {
        WordNet wordNet = new WordNet("C:\\Users\\stasp\\Documents\\GitHub\\Practice12\\src\\main\\java\\synsets.txt", "C:\\Users\\stasp\\Documents\\GitHub\\Practice12\\src\\main\\java\\hypernyms.txt");
        Outcast outcast = new Outcast(wordNet);
        String[] s= {"water", "bed","milk"};
        System.out.println(outcast.outcast(s));
    }
}