package com.springboot.textAnalyzer.analyzers;
import java.util.ArrayList;

public class TextTokenizer {
  private String rawText;
  private ArrayList<String> sentences;
  private ArrayList<String> words;
  private int sentenceCount;
  private int wordCount;
  private int syllableCount;

  public TextTokenizer(String rawText) {
    this.rawText = rawText;
    sentences = findSentences();
    sentenceCount = sentences.size();
    words = findWords();
    wordCount = words.size();
    syllableCount = totalSyllables();
  }

  public String getRawText() {
    return rawText;
  }
  public ArrayList<String> getSentences() {
    return sentences;
  }
  public int getSentenceCount() {
    return sentenceCount;
  }
  public int getWordCount() {
    return wordCount;
  }
  
  public ArrayList<String> getWords() {
    return words;
  }
  
  public int getSyllableCount() {
    return syllableCount;
  }
  
  public ArrayList<String> findSentences() {
    sentences = new ArrayList<String>();
    String current = "";
    for (int i=0; i < rawText.length(); i++) {
      char character = rawText.charAt(i);
      current += character;
      if (character == '.' || character == '!' || character == '?') {
        String sentence = current.trim();
        current = "";
        if (sentence.length() > 0) {
          sentences.add(sentence);
        }
      }
    }
    return sentences;
  }
  public ArrayList<String> findWords() {
    ArrayList<String> words = new ArrayList<String>();

    for (String word : rawText.split(" ")) {
      String cleanWord = word.toLowerCase().replaceAll("[^a-z]", ""); // remove anything that isnt a-z
      if (!cleanWord.equals("")) {
        words.add(cleanWord);
      }
    }

    return words;
  }
  //aproximates total syllables in a word

  //PLEASE REVISE THIS METHOD TO ACCOUNT FOR MORE EDGE CASES, SUCH AS "LE" ENDINGS, AND PREVENT COUNTING SILENT "E"s
  public int countSyllables(String word) {    
    int count = 0;
    String vowels = "aeiouy"; 
    boolean wasVowel = false;
    //this loop counts vowels excluding adjacent vowels
    for(int i=0; i < word.length(); i++) {
      if (vowels.indexOf(word.charAt(i)) != -1 && !wasVowel) {
        wasVowel = true;
        count++;
      }
      if(vowels.indexOf(word.charAt(i)) == -1) {
        wasVowel = false;
      }
      
    }
    //ensures short words have 1 syllable
    if (count < 1 || word.length() < 3) {count = 1;}
    
    //special case where words ending in "le" count as a syllable
    else if (word.substring(word.length()-2).equals("le") && vowels.indexOf(word.charAt(word.length()-3)) == -1) {
      return count;
    }
      
    
    //prevents silent e from being counted
    else if (word.charAt(word.length()-1) == 'e') {count--;}
    
    
   return count; 
  }
  //counts total syllables in the text
  public int totalSyllables() {
    int total = 0;
    for (String word : words) {
      total += countSyllables(word);  
    }
   return total;   
  }
  
}