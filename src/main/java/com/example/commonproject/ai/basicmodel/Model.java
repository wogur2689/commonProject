package com.example.commonproject.ai.basicmodel;

/**
 * Ai Model을 호출하기 위한 일반 API제공.
 * 요청을 보내고 응답을 받는 프로세스를 추상화하여 다양한 유형의 AI모델과의 상호작용을 처리하도록 설계됨.
 * @param <TReq>
 * @param <TRes>
 */
public interface Model<TReq extends ModelRequest<?>, TRes extends ModelResponse<?>> {
    /**
     * Executes a method call to the AI model.
     * @param request the request object to be sent to the AI model
     * @return the response from the AI model
     */
    TRes call(TReq request);
}
