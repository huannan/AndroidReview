package com.nan.androidreview.designpattern.chain;

public class Request {

    private int mRequestLevel;
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
