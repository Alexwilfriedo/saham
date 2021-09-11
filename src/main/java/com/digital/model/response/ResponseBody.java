package com.digital.model.response;

public class ResponseBody<T> {

    private T data;
    private RestResponse response;

    public ResponseBody(T data, RestResponse response) {
        this.data = data;
        this.response = response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RestResponse getResponse() {
        return response;
    }

    public void setResponse(RestResponse response) {
        this.response = response;
    }
}

