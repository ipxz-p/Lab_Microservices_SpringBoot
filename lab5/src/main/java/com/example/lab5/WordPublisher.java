package com.example.lab5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;
    public WordPublisher(){
        this.words = new Word();
    }
    @RequestMapping(value = "addBad/{word}")
    public ArrayList<String> addBadWord(@PathVariable("word") String word){
        words.badWords.add(word);
        return words.badWords;
    }
    @RequestMapping(value = "delBad/{word}")
    public ArrayList<String> deleteBadWord(@PathVariable("word") String word){
        words.badWords.remove(word);
        return words.badWords;
    }
    @RequestMapping(path = "/addGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }
    @RequestMapping(path = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s){
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }
    @RequestMapping(path = "/proof/{sentence}")
    public String proofSentence(@PathVariable("sentence") String s){
        boolean foundGood = false, foundBad = false;
        for(int i = 0; i < this.words.goodWords.size(); i++){
            if(s.contains(this.words.goodWords.get(i))){
                foundGood = true;
            }
        }
        for(int i = 0; i < this.words.badWords.size(); i++){
            if(s.contains(this.words.badWords.get(i))){
                foundBad = true;
            }
        }
        if(foundGood && foundBad){
            rabbitTemplate.convertAndSend("Fanout", "", s);
            return "Found Bad & Good Word";
        }else if (foundGood) {
            rabbitTemplate.convertAndSend("Direct", "good", s);
            return "Found Good Word";
        } else if (foundBad) {
            rabbitTemplate.convertAndSend("Direct", "bad", s);
            return "Found Bad Word";
        }
        return "";
    }
    @RequestMapping(value = "/getSentence")
    public Sentence getSentence(){
        Object out = rabbitTemplate.convertSendAndReceive("Direct", "queue", "");
        return (Sentence) out;
    }
}
