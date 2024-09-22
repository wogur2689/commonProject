package com.example.commonproject.ai.basicmodel;


/**
 * AI 모델의 주요 출력과 이 결과와 관련된 메타데이터에 액세스 하는 방법을 제공,
 * @param <T>
 */
public interface ModelResult<T> {

    /**
     * Retrieves the output generated by the AI model.
     * @return the output generated by the AI model
     */
    T getOutput();

    /**
     * Retrieves the metadata associated with the result of an AI model.
     * @return the metadata associated with the result
     */
//    ResultMetadata getMetadata();

}
