# TextAnalyzer

A full-stack web application that analyzes text and returns readability metrics and sentence complexity highlights. Built as a portfolio project and my first complete full-stack app — the primary goal was to learn REST API design, client-server communication, and end-to-end deployment of a production web application.

**Live App:** [text-analyzer-wjn3.onrender.com](https://text-analyzer-wjn3.onrender.com)

---

## Features

- **Readability metrics** — Flesch-Kincaid, Gunning Fog, and SMOG scores with grade level classification
- **Sentence complexity highlighting** — each sentence categorized as Easy, Medium, or Hard using a custom scoring formula
- **Complexity filter buttons** — show all sentences or isolate by difficulty level
- **Complexity distribution bar** — visual breakdown of Easy / Medium / Hard sentence percentages
- **Overall readability score** — categorical label (Easy / Medium / Hard) averaged across all three metrics
- **Grade level label** — Kindergarten through Post-Grad derived from Flesch-Kincaid
- **Word and sentence count** — live count updates as you type, final counts returned from the backend
- **Score reference dropdowns** — formulas and plain-English explanations for each readability index built into the UI

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 4.1.0-M2 |
| Frontend | HTML, CSS, Vanilla JavaScript |
| Build | Maven |
| Containerization | Docker |
| Deployment | Render (backend web service + frontend static site) |

---

## Project Structure

```
backend/src/main/java/com/springboot/textAnalyzer/
├── controllers/    # HTTP layer — POST /analyzer, GET /healthz
├── services/       # Orchestrates the analysis pipeline
├── analyzers/      # Core logic (TextTokenizer, ReadabilityAnalyzer)
└── models/         # AnalysisRequest and AnalysisResponse DTOs

frontend/
├── index.html      # Main UI
├── styles.css      # Styling
└── app.js          # API calls and DOM manipulation
```

---

## API

**Base URL:** `https://text-analyzer-7s8s.onrender.com`

### POST `/analyzer`

**Request**
```json
{ "text": "Your text here..." }
```

**Response**
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
    { "sentence": "This is easy.", "category": "Easy" },
    { "sentence": "This is a more complicated sentence.", "category": "Hard" }
  ]
}
```

### GET `/healthz`
Returns `"OK"` — used for uptime monitoring.

---

## Deployment

Both services are deployed on [Render](https://render.com).

- **Frontend (Static Site):** [text-analyzer-wjn3.onrender.com](https://text-analyzer-wjn3.onrender.com)
- **Backend (Web Service):** [text-analyzer-7s8s.onrender.com](https://text-analyzer-7s8s.onrender.com)

---

## Author

[rkdev771](https://github.com/rkdev771)