package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WordNet {

    Digraph digraph;
    ArrayList<Synset> synList = new ArrayList<>();

    public WordNet(String synsets, String hypernyms) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(synsets));
        while (br.ready()){
            String[] s = br.readLine().split(",");
            synList.add(new Synset(Integer.parseInt(s[0]),s[1],s[2]));
        }
        digraph = new Digraph(synList.size());
        br = new BufferedReader(new FileReader(hypernyms));
        while (br.ready()){
            String[] s = br.readLine().split(",");
            for (int i = 1; i < s.length; i++) {
                digraph.addEdge(Integer.parseInt(s[0]),Integer.parseInt(s[i]));
            }
        }
    }

    // returns all iterable nouns
    public Iterable<String> nouns(){
        ArrayList<String> arrOfNouns = new ArrayList<>();
        synList.forEach(x->arrOfNouns.add(x.name));
        return arrOfNouns;
    }

    public boolean isNoun(String word) {
        for (String x : nouns()) {
            if (x.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // distanse between nounA і nounB
    public int getIdByNoun(String n){
        AtomicInteger i = new AtomicInteger(-1);
        synList.forEach(x->{
            if (x.name.equals(n)){
                i.set(x.id);
                return;
            }
        });
        return i.get();
    }

    public String getNounById(int n){
        return synList.get(n).name;
    }
    public int distance(String nounA, String nounB){
        SAP sap = new SAP(digraph);
        return sap.length(getIdByNoun(nounA), getIdByNoun(nounB));
    }

    // same parent for both nouns
    public String sap(String nounA, String nounB){
        SAP sap = new SAP(digraph);
        return synList.get(sap.ancestor(getIdByNoun(nounA), getIdByNoun(nounB))).name;
    }

    class Synset{
        int id;
        String name;
        String definition;

        public Synset(int id, String name, String definition) {
            this.id = id;
            this.name = name;
            this.definition = definition;
        }
    }

    public static void main(String[] args) throws IOException {
        WordNet wordNet = new WordNet("C:\\Users\\stasp\\Documents\\GitHub\\Practice12\\src\\main\\java\\synsets.txt", "C:\\Users\\stasp\\Documents\\GitHub\\Practice12\\src\\main\\java\\hypernyms.txt");
        System.out.println(wordNet.isNoun("milk"));
        System.out.println(wordNet.sap("giant_panda", "zebra"));
        System.out.println(wordNet.distance("whole unit", "entity"));
    }

}