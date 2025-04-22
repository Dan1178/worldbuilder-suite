# worldbuilder-suite
A full-stack application for writers and artists, integrating AI to assist with world-building, character creation, and storytelling.

## Features
- **Name Generator**: Generate unique character names based on descriptions (e.g., race, nationality, vibe) using Mistral AI.
- **Character Prototyper**: Generate an image of a character based on description using DALL-E-3.
- **Loremaster**: Upload text files of lore for your setting, characters, etc. and ask questions about it (i.e. which
  character in this setting would be most likely to lead a rebellion?) using an in-memory vector store and GPT4.1-nano.

## Tech Stack
- **Backend**: Spring Boot, LangChain4J, Mistral (Hugging Face), OpenAI (GPT4.1-nano and DALL-E-3)
- **Frontend**: React, Tailwind CSS v4, Typescript
- **Database**: Langchain In-Memory Vector Store

## Setup
1. **Backend**:
    - Clone repo: `git clone https://github.com/yourusername/worldbuilder-suite.git`
    - Navigate to `/worldbuilder-suite`
    - Update `application.properties` with your Hugging Face API key: `langchain4j.hugging-face.api-key=hf_xxxxxxxxxxxxxxxx`
 and your OpenAi API key.
   - Run: `mvn spring-boot:run`
2. **Frontend**:
    - Navigate to `/frontend/worldbuilder-frontend`
    - Install: `npm install`
    - Run: `npm run dev`
3. Open `http://localhost:5173` and try the Name Generator.

## Progress
- [x] Name Generator module
- [x] Loremaster (RAG-based Q&A)
- [x] Character Prototyper (image generation)
- [ ] Plot Weaver (story prompts)

## Planned Improvements
- [ ] Allow follow-up prompts for the name generator and character prototyper
- [ ] Create a settings tab that controls vector store document loading globally
- [ ] Allow selection of chat models on settings tab
- [ ] 
