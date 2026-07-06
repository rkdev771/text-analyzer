// ── Element References ──────────────────────────────────────────────
const analyzeBtn = document.getElementById('analyze-btn');
const textInput = document.getElementById('text-input');


// ── Analyze Button Click Handler ────────────────────────────────────
// Triggered when user clicks Analyze — grabs text, calls API, updates UI
analyzeBtn.addEventListener('click', async () => {
    const text = textInput.value.trim();

    // Guard clause — don't call API if input is empty
    if (text === '') {
        alert('Please enter some text first.');
        return;
    }

    // Show loading state while waiting for API response
    analyzeBtn.textContent = 'Analyzing...';
    analyzeBtn.disabled = true;

    try {
        // POST request to Spring Boot backend with raw text as JSON body
        const response = await fetch('http://localhost:8080/analyzer', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ text: text })
        });

        // Parse JSON response into a JavaScript object
        const data = await response.json();

        // Populate all UI components with the response data
        updateUI(data);

    } catch (error) {
        console.error('Error calling API:', error);
        alert('Something went wrong. Make sure the backend is running.');
    } finally {
        // Always re-enable button whether call succeeded or failed
        analyzeBtn.textContent = 'Analyze';
        analyzeBtn.disabled = false;
    }
});


// ── Update UI ───────────────────────────────────────────────────────
// Takes the full API response object and updates all UI components
function updateUI(data) {
    // Readability score cards
    document.getElementById('fk-score').textContent = data.kincaid;
    document.getElementById('smog-score').textContent = data.smog;
    document.getElementById('fog-score').textContent = data.gunningFog;

    // Grade level and overall readability badge
    document.getElementById('grade-level').textContent = data.gradeLevel;
    document.getElementById('overall-score').textContent = data.readabilityScore;

    // Word and sentence counts from backend
    document.getElementById('word-count').textContent = data.wordCount;
    document.getElementById('sentence-count').textContent = data.sentenceCount;

    // Render highlighted sentences in output box
    renderSentences(data.sentenceComplexityPairs);

    // Update complexity distribution bar
    updateDistributionBar(data.sentenceComplexityPairs);
}


// ── Render Sentences ────────────────────────────────────────────────
// Takes sentence/category pairs from API and renders each sentence
// as a colored span in the output box
function renderSentences(sentenceComplexityPairs) {
    const outputText = document.getElementById('output-text');

    // Clear previous results before rendering new ones
    outputText.innerHTML = '';

    sentenceComplexityPairs.forEach(pair => {
        const span = document.createElement('span');

        // Add base class and complexity class (easy/medium/hard) for CSS highlighting
        span.classList.add('sentence', pair.category.toLowerCase());

        // Store category as data attribute for filter buttons to reference
        span.dataset.category = pair.category.toLowerCase();

        span.textContent = pair.sentence + ' ';
        outputText.appendChild(span);
    });
}


// ── Filter Buttons ──────────────────────────────────────────────────
// Sets up click handlers for All/Hard/Medium/Easy filter buttons
// Shows or hides sentences based on their complexity category
function setupFilters() {
    const filterBtns = document.querySelectorAll('.filter-btn');

    filterBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            // Remove active state from all buttons then set on clicked one
            filterBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');

            const filter = btn.dataset.filter;
            const sentences = document.querySelectorAll('.sentence');

            // Show all sentences or only those matching the selected filter
            sentences.forEach(sentence => {
                if (filter === 'all' || sentence.dataset.category === filter) {
                    sentence.classList.remove('hidden');
                } else {
                    sentence.classList.add('hidden');
                }
            });
        });
    });
}


// ── Complexity Distribution Bar ─────────────────────────────────────
// Calculates percentage of Easy/Medium/Hard sentences and updates
// the distribution bar widths and percentage labels
function updateDistributionBar(sentenceComplexityPairs) {
    const total = sentenceComplexityPairs.length;

    if (total === 0) return;

    // Count sentences in each category
    const counts = { hard: 0, medium: 0, easy: 0 };
    sentenceComplexityPairs.forEach(pair => {
        counts[pair.category.toLowerCase()]++;
    });

    // Calculate percentages — easy gets remainder to avoid rounding errors
    const hardPct = Math.round((counts.hard / total) * 100);
    const mediumPct = Math.round((counts.medium / total) * 100);
    const easyPct = 100 - hardPct - mediumPct;

    // Update bar segment widths
    document.getElementById('bar-hard').style.width = hardPct + '%';
    document.getElementById('bar-medium').style.width = mediumPct + '%';
    document.getElementById('bar-easy').style.width = easyPct + '%';

    // Update percentage labels
    document.getElementById('pct-hard').textContent = hardPct + '%';
    document.getElementById('pct-medium').textContent = mediumPct + '%';
    document.getElementById('pct-easy').textContent = easyPct + '%';
}


// ── Initialization ──────────────────────────────────────────────────
// Run once on page load to set up event listeners

// Set up filter button click handlers
setupFilters();

// Live word and character count — updates as user types without API call
textInput.addEventListener('input', () => {
    const text = textInput.value.trim();
    const words = text === '' ? 0 : text.split(/\s+/).length;
    const chars = textInput.value.length;

    document.getElementById('word-count-live').textContent = words + ' words';
    document.getElementById('char-count-live').textContent = chars + ' chars';
});