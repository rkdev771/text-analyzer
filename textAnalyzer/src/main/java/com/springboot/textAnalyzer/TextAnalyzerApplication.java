package com.springboot.textAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//
import com.springboot.textAnalyzer.analyzers.ReadabilityAnalyzer;
import com.springboot.textAnalyzer.analyzers.TextTokenizer;

@SpringBootApplication
public class TextAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextAnalyzerApplication.class, args);
	}

}
