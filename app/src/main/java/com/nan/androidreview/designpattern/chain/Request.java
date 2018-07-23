package com.nan.androidreview.designpattern.chain;

/**
 * 请求体
 */
public class Request {

    //请求等级
    private int mRequestLevel;
    //请求参数
    private String mRequest;

    public Request(int requestLevel, String request) {
        this.mRequestLevel = requestLevel;
        this.mRequest = request;
    }

    public int getRequestLevel() {
        return this.mRequestLevel;
    }

    public String getRequest() {
        return this.mRequest;
    }
}
