package com.springboot.textAnalyzer.models;
import java.util.List;
import java.util.Map;

public class AnalysisResponse {
    private double kincaid;
    private double gunningFog;
    private double smog;
    private String readabilityScore;
    private String gradeLevel;
    private int wordCount;
    private int sentenceCount;
    private List<Map<String, String>> sentenceComplexityPairs;

    public double getKincaid() {
        return kincaid;
    }

    public void setKincaid(double kincaid) {
        this.kincaid = kincaid;
    }

    public double getGunningFog() {
        return gunningFog;
    }

    public void setGunningFog(double gunningFog) {
        this.gunningFog = gunningFog;
    }

    public double getSmog() {
        return smog;
    }

    public void setSmog(double smog) {
        this.smog = smog;
    }

    public String getReadabilityScore() {
        return readabilityScore;
    }

    public void setReadabilityScore(String readabilityScore) {
        this.readabilityScore = readabilityScore;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public void setSentenceCount(int sentenceCount) {
        this.sentenceCount = sentenceCount;
    }

    public List<Map<String, String>> getSentenceComplexityPairs() {
        return sentenceComplexityPairs;
    }

    public void setSentenceComplexityPairs(List<Map<String, String>> sentenceComplexityPairs) {
        this.sentenceComplexityPairs = sentenceComplexityPairs;
    }
}
