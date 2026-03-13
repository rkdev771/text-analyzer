package com.springboot.textAnalyzer.analyzers;

public class ReadabilityAnalyzer {

    private final TextTokenizer tokenizer;

    public ReadabilityAnalyzer(TextTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    // uses flesch-kincaid equation to calculate grade level
    public double kincaid() {
        int sentenceCount = tokenizer.getSentenceCount();
        int wordCount = tokenizer.getWordCount();
        int syllableCount = tokenizer.getSyllableCount();

        if (sentenceCount == 0 || wordCount == 0) {
            return 0;
        } else {
            double FK = 0.39 * ((double) wordCount / sentenceCount)
                      + 11.8 * ((double) syllableCount / wordCount)
                      - 15.59;
            return Math.round(FK);
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