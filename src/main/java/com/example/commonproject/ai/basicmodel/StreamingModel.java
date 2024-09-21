package com.example.commonproject.ai.basicmodel;

import reactor.core.publisher.Flux;

/**
 * 스트리밍 응답이 있는 AI 모델을 호출하기 위한 일반 API 제공
 * @param <TReq>
 * @param <TResChunk>
 */
public interface StreamingModel<TReq extends ModelRequest<?>, TResChunk extends ModelResponse<?>> {
    /**
     * Executes a method call to the AI model.
     * @param request the request object to be sent to the AI model
     * @return the streaming response from the AI model
     */
    Flux<TResChunk> stream(TReq request);
}
