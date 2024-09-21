package com.example.commonproject.ai.basicmodel;

/**
 * AI 모델에 대한 요청
 * @param <T>
 */
public interface ModelRequest<T> {
    /**
     * Retrieves the instructions or input required by the AI model.
     * @return the instructions or input required by the AI model
     */
    T getInstructions(); // required input

    /**
     * Retrieves the customizable options for AI model interactions.
     * @return the customizable options for AI model interactions
     */
    ModelOptions getOptions();
}
