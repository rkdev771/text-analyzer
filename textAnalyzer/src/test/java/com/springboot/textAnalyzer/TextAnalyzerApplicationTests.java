package com.springboot.textAnalyzer;

import com.springboot.textAnalyzer.analyzers.TextTokenizer;
import com.springboot.textAnalyzer.analyzers.ReadabilityAnalyzer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerApplicationTests {

    // ── Syllable Count Tests ─────────────────────────────────────────
    @Test
    void testSyllablesSingleSyllable() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(1, t.countSyllables("cat"));
    }

    @Test
    void testSyllablesSimple() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(2, t.countSyllables("simple"));
    }

    @Test
    void testSyllablesTable() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(2, t.countSyllables("table"));
    }

    @Test
    void testSyllablesSilentE() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(1, t.countSyllables("make"));
    }

    @Test
    void testSyllablesScience() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(2, t.countSyllables("science"));
    }

    // ── Sentence Parsing Tests ───────────────────────────────────────
    @Test
    void testSentenceCount() {
        TextTokenizer t = new TextTokenizer("The cat sat. The dog ran. The bird flew.");
        assertEquals(3, t.getSentenceCount());
    }

    @Test
    void testSentenceCountExclamation() {
        TextTokenizer t = new TextTokenizer("Hello! How are you? I am fine.");
        assertEquals(3, t.getSentenceCount());
    }

    // ── Word Count Tests ─────────────────────────────────────────────
    @Test
    void testWordCount() {
        TextTokenizer t = new TextTokenizer("The cat sat on the mat.");
        assertEquals(6, t.getWordCount());
    }

    // ── Complex Word Tests ───────────────────────────────────────────
    @Test
    void testComplexWordCount() {
        TextTokenizer t = new TextTokenizer("The cat sat on the beautiful complicated mat.");
        assertEquals(2, t.getComplexWordCount());
    }

    // ── Readability Tests ────────────────────────────────────────────
    @Test
    void testKincaidReturnsNonNegative() {
        TextTokenizer t = new TextTokenizer("The cat sat on the mat. The dog ran fast.");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertTrue(a.kincaid() >= 0);
    }

    @Test
    void testGunningFogReturnsNonNegative() {
        TextTokenizer t = new TextTokenizer("The cat sat on the mat. The dog ran fast.");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertTrue(a.gunningFog() >= 0);
    }

    @Test
    void testSmogReturnsNonNegative() {
        TextTokenizer t = new TextTokenizer("The cat sat on the mat. The dog ran fast.");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertTrue(a.smog() >= 0);
    }

    @Test
    void testCategoricalScoreEasy() {
        TextTokenizer t = new TextTokenizer("The cat sat. The dog ran. The bird flew.");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertEquals("Easy", a.readabilityScore());
    }

    @Test
    void testGradeLevelNotEmpty() {
        TextTokenizer t = new TextTokenizer("The cat sat on the mat. The dog ran fast.");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertFalse(a.gradeLevel().isEmpty());
    }

    // ── Edge Case Tests ──────────────────────────────────────────────
    @Test
    void testEmptyTextReturnsZeroScores() {
        TextTokenizer t = new TextTokenizer("");
        ReadabilityAnalyzer a = new ReadabilityAnalyzer(t);
        assertEquals(0.0, a.kincaid());
        assertEquals(0.0, a.gunningFog());
        assertEquals(0.0, a.smog());
    }

    @Test
    void testSingleWordMinimumSyllable() {
        TextTokenizer t = new TextTokenizer("test");
        assertEquals(1, t.countSyllables("a"));
    }
}