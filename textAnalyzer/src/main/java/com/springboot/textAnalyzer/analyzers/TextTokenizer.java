package com.springboot.textAnalyzer.analyzers;
import java.util.ArrayList;
import java.util.List;

public class TextTokenizer {
  private String rawText;
  private List<String> sentences;
  private List<String> words;
  private List<String> complexWords;
  private int sentenceCount;
  private int wordCount;
  private int syllableCount;
  private int complexWordCount; 

  public TextTokenizer(String rawText) {
    this.rawText = rawText;
    sentences = findSentences();
    sentenceCount = sentences.size();
    words = findWords();
    wordCount = words.size();
    syllableCount = totalSyllables();
    complexWords = findComplexWords();
    complexWordCount = complexWords.size();
  }

  public String getRawText() {
    return rawText;
  }
  public List<String> getSentences() {
    return sentences;
  }
  public int getSentenceCount() {
    return sentenceCount;
  }
  public int getWordCount() {
    return wordCount;
  }
  
  public List<String> getWords() {
    return words;
  }
  
  public int getSyllableCount() {
    return syllableCount;
  }
  public List<String> getComplexWords() {
    return complexWords;
  }
  public int getComplexWordCount() {
    return complexWordCount;
  }
  
  public List<String> findSentences() {
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
  public List<String> findWords() {
    List<String> words = new ArrayList<String>();

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
    char prevVowel = '\0'; //primitive types must have a value
    boolean wasVowel = false;
    //this loop counts vowels excluding adjacent vowels
    for(int i=0; i < word.length(); i++) {
      if (vowels.indexOf(word.charAt(i)) != -1 && !wasVowel) {
        wasVowel = true;
        prevVowel = word.charAt(i);
        count++;
      }
      if(vowels.indexOf(word.charAt(i)) == -1) {
        wasVowel = false;
      }
    }
    //ensures short words have 1 syllable
    if (count < 1 || word.length() < 3) {count = 1;}
    
    //special case where words ending in "le" that don't have a preceding vowel count as a syllable
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
  // finds complex words with more than 2 syllables
  public List<String> findComplexWords() {
    List<String> complexWords = new ArrayList<String>();
    for (String word : words) {
      if (countSyllables(word) > 2) {
        complexWords.add(word);
      }
    }
    return complexWords;
  }
  
}