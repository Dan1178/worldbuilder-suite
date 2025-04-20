# worldbuilder-suite
A full-stack application for writers and artists, integrating AI to assist with world-building, character creation, and storytelling.

## Features
- **Name Generator**: Generate unique character names based on descriptions (e.g., race, nationality, vibe) using Mistral AI.

## Tech Stack
- **Backend**: Spring Boot, LangChain4J, Mistral (Hugging Face)
- **Frontend**: React, Tailwind CSS v4
- **Database**: H2 (planned for future modules)

## Setup
1. **Backend**:
    - Clone repo: `git clone https://github.com/yourusername/worldbuilder-suite.git`
    - Navigate to `/worldbuilder-suite`
    - Add `application.properties` with Hugging Face API key: `langchain4j.hugging-face.api-key=hf_xxxxxxxxxxxxxxxx`
    - Run: `mvn spring-boot:run`
2. **Frontend**:
    - Navigate to `/frontend/worldbuilder-frontend`
    - Install: `npm install`
    - Run: `npm run dev`
3. Open `http://localhost:5173` and try the Name Generator.

## Progress
- [x] Name Generator module
- [ ] Loremaster (RAG-based Q&A)
- [ ] Character Prototyper (image generation)
- [ ] Plot Weaver (story prompts)

## Screenshots
(Add later when more modules are done)
