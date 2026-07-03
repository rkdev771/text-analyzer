package com.springboot.textAnalyzer.services;
import org.springframework.stereotype.Service;
import com.springboot.textAnalyzer.models.AnalysisResponse;
import com.springboot.textAnalyzer.analyzers.ReadabilityAnalyzer;
import com.springboot.textAnalyzer.analyzers.TextTokenizer;
@Service
public class TextAnalysisService {
    
    public AnalysisResponse analyzeText(String rawText) {
        TextTokenizer tokenizer = new TextTokenizer(rawText);
        ReadabilityAnalyzer readabilityAnalyzer = new ReadabilityAnalyzer(tokenizer);
        AnalysisResponse response = new AnalysisResponse();
        response.setKincaid(readabilityAnalyzer.kincaid());
        response.setGunningFog(readabilityAnalyzer.gunningFog());
        response.setSmog(readabilityAnalyzer.smog());
        response.setWordCount(tokenizer.getWordCount());
        response.setSentenceCount(tokenizer.getSentenceCount());
        response.setReadabilityScore(readabilityAnalyzer.readabilityScore());
        response.setGradeLevel(readabilityAnalyzer.gradeLevel());
        response.setSentenceComplexityPairs(readabilityAnalyzer.pairSentenceComplexity());
        
        return response;
    } 

}
