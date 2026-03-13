package com.springboot.textAnalyzer.analyzers;

public class ReadabilityAnalyzer {
    private final int wordCount;
    private final int sentenceCount;
    private final int syllableCount;
    private final int complexWordCount;
    public ReadabilityAnalyzer(TextTokenizer tokenizer) {
        this.wordCount = tokenizer.getWordCount();
        this.sentenceCount = tokenizer.getSentenceCount();
        this.syllableCount = tokenizer.getSyllableCount();
        this.complexWordCount = tokenizer.getComplexWordCount();
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

    public String categoricalScore() {
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