package com.example.lab3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GeneratePasswordService {
    @RequestMapping(path = "{name:[a-z]+}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name){
        Random random = new Random();
        long randomNum = Math.abs(random.nextLong());
        String randomString = Long.toString(randomNum);
        String tenNum = randomString.substring(0,10);
        return "Hi, " + name + "\n" + "Your new password is " + tenNum + ".";
    }
}
