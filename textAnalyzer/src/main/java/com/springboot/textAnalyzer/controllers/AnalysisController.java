package com.springboot.textAnalyzer.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.textAnalyzer.services.TextAnalysisService;
import com.springboot.textAnalyzer.models.AnalysisRequest;
import com.springboot.textAnalyzer.models.AnalysisResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/analyzer")
@CrossOrigin(origins = "*")
public class AnalysisController {
    @Autowired
    private TextAnalysisService analysisService;

    @PostMapping
    public AnalysisResponse analyzeText(@RequestBody AnalysisRequest request) {
        return analysisService.analyzeText(request.getText());
    }
}
