package com.example.lab5;

import java.io.Serializable;
import java.util.ArrayList;

public class Sentence implements Serializable {
    public ArrayList<String> badSentences;
    public ArrayList<String> goodSentences;
    public Sentence(){
        this.badSentences = new ArrayList<String>();
        this.goodSentences = new ArrayList<String>();
    }
}
