package com.springboot.textAnalyzer.analyzers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ReadabilityAnalyzer {
    private final int wordCount;
    private final int sentenceCount;
    private final int syllableCount;
    private final int complexWordCount;
    private final List<String> sentences;
    public ReadabilityAnalyzer(TextTokenizer tokenizer) {
        this.wordCount = tokenizer.getWordCount();
        this.sentenceCount = tokenizer.getSentenceCount();
        this.syllableCount = tokenizer.getSyllableCount();
        this.complexWordCount = tokenizer.getComplexWordCount();
        this.sentences = tokenizer.getSentences();
    }

    // uses flesch-kincaid equation to calculate grade level
    public double kincaid() {
        if (sentenceCount == 0 || wordCount == 0) {
            return 0;
        } else {
            double FK = 0.39 * ((double) wordCount / sentenceCount)
                      + 11.8 * ((double) syllableCount / wordCount)
                      - 15.59;
            return Math.round(FK);
        }
    }

    public double gunningFog() {
        if (sentenceCount == 0 || wordCount == 0) {
            return 0;
        } else {
            double GF = 0.4 * (((double) wordCount / sentenceCount) + 100 * ((double) complexWordCount / wordCount));
            return Math.round(GF);
        }
    }
    public double smog() {
        if (sentenceCount == 0) {
            return 0;
        } else {
            double SMOG = 1.043 * Math.sqrt(complexWordCount * (30.0 / sentenceCount)) + 3.1291;
            return Math.round(SMOG);
        }
    }

    public String getSentenceComplexity(String sentence) {
        TextTokenizer sentenceTokenizer = new TextTokenizer(sentence);
        // 1. Prevent crash on empty inputs
        double wordCount = (double) sentenceTokenizer.getWordCount();
        if (wordCount == 0) {
            return "Easy"; 
        }

// 2. Calculate the Sentence Complexity (SC) score
double SC = ((sentence.length() / wordCount) * 2.0) 
          + ((sentenceTokenizer.getComplexWordCount() / wordCount) * 20.0) 
          + (wordCount * 0.2);

        // 3. Classify into 3 levels
        if (SC < 18.0) {
            return "Easy";
        } else if (SC <= 28.0) {
            return "Medium";
        } else {
            return "Hard";
        }
    }
    public List<Map<String, String>> pairSentenceComplexity() {
        List<Map<String, String>> sentenceComplexityPairs = new ArrayList<>();
        for (String sentence : sentences) {
            Map<String, String> pair = new HashMap<>();
            pair.put("sentence", sentence);
            pair.put("category", getSentenceComplexity(sentence));
            sentenceComplexityPairs.add(pair);
        }
        return sentenceComplexityPairs;
    }
    public String readabilityScore() {
        double average = (kincaid() + gunningFog() + smog()) / 3;
        if (average < 10) {
            return "Easy";
        } else if (average < 15) {
            return "Medium";
        } else {
            return "Hard";
        }
    }


    // converts flesch-kincaid to readable grade level
    public String gradeLevel() {
        double score = kincaid();
        if (score < 4) {
            return "Kindergarten";
        } else if (score < 7) {
            return "Elementary";
        } else if (score < 10) {
            return "Middle-School";
        } else if (score < 13) {
            return "High-School";
        } else if (score <= 16) {
            return "College";
        } else {
            return "Post-Grad";
        }
    }
}