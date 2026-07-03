# Text Analyzer Web App

A full-stack web application that analyzes user-submitted text and returns 
readability metrics, sentence complexity highlights, and data visualizations.

## Features
- Readability metrics: Flesch-Kincaid, Gunning Fog, SMOG
- Sentence complexity highlighting (Easy / Medium / Hard)
- Readability bar chart and sentence complexity pie chart
- Overall categorical readability score and grade level
- Word count and sentence count statistics

## Tech Stack
- **Backend:** Java 21 + Spring Boot
- **Frontend:** HTML, CSS, JavaScript
- **Charts:** Chart.js
- **Build Tool:** Maven
- **Version Control:** Git + GitHub
- **Deployment:** Render (backend), Vercel (frontend)

## Project Structure

    src/main/java/com/springboot/textAnalyzer/
    ├── controllers/        # HTTP layer, exposes POST /analyzer endpoint
    ├── services/           # Coordinates analysis pipeline
    ├── analyzers/          # Core algorithms (TextTokenizer, ReadabilityAnalyzer)
    └── models/             # AnalysisRequest and AnalysisResponse DTOs

    frontend/
    ├── index.html          # Main UI
    ├── styles.css          # Styling
    ├── app.js              # Fetch API and DOM manipulation
    └── charts.js           # Chart.js visualizations

## API

**POST** `/analyzer`

Request:
```json
{"text": "Your text here..."}
```

Response:
```json
{
  "kincaid": 9.0,
  "gunningFog": 11.0,
  "smog": 10.0,
  "gradeLevel": "High-School",
  "readabilityScore": "Medium",
  "wordCount": 45,
  "sentenceCount": 3,
  "sentenceComplexityPairs": [
    {"sentence": "This is easy.", "category": "Easy"},
    {"sentence": "This is complex.", "category": "Hard"}
  ]
}

## Roadmap
- [x] Core analysis engine (TextTokenizer, ReadabilityAnalyzer)
- [x] Readability metrics (FK, Gunning Fog, SMOG)
- [x] Per-sentence complexity scoring
- [x] REST API (/analyzer endpoint)
- [ ] Frontend UI
- [ ] Chart.js visualizations
- [ ] Deployment

## Author
[rkdev771](https://github.com/rkdev771)
