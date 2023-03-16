package com.test.employee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @GetMapping("/count")
    public Map<Character, Integer> Count() {
        String para = "aaabbb";
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : para.toCharArray()) {
            charCount.merge(c,          // key = char
                    1,                  // value to merge
                    Integer::sum);      // counting
        }
        return charCount;
        }


         @GetMapping("/test")
        public Map<Integer, Integer>  CountTest() {
             final String input = "I love my work E";
             final String[] words = input.split(" ");
             final Map<Integer, Integer> occurencesMap = new HashMap<>();
             for (final String word : words) {
                 final int lenght = word.length();
                 if (occurencesMap.get(lenght) == null) {
                     occurencesMap.put(lenght, 1);
                 } else {
                     occurencesMap.put(lenght, occurencesMap.get(lenght) + 1);
                 }
             }
            return occurencesMap;
         }


}
