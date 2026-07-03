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

		TextTokenizer tokenizer = new TextTokenizer("Hello, World!");
		ReadabilityAnalyzer analyzer = new ReadabilityAnalyzer(tokenizer);
		System.out.println("Grade Level: " + analyzer.readabilityScore());
		
		
		TextTokenizer tokenizer2 = new TextTokenizer("create");
		System.out.println("syllables: " + tokenizer2.getSyllableCount());
	}

}
