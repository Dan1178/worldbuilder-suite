package com.worldbuilder.worldbuilder_suite.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class LanguageModelService {
    private final ChatLanguageModel chatLanguageModel;

    public LanguageModelService(String openAiApiKey) {
        this.chatLanguageModel = OpenAiChatModel.builder()
                .modelName("gpt-4.1-nano-2025-04-14")
                .apiKey(openAiApiKey)
                .build();
    }

    public ChatLanguageModel getChatLanguageModel() {
        return chatLanguageModel;
    }

}
